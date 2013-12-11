package com.agartime.utad.histogram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class HistogramFlow extends Configured implements Tool {
    //This method is not a best-practice precisely... but in this particular case, it works...
    public static RangeWritable getRangeFromFile(String filePath) throws Exception{
        FileSystem fileSystem = FileSystem.get(new Configuration());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileSystem.open(new Path(filePath+"/part-r-00000"))));
        String line = bufferedReader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"{},= ");
        if (tokenizer.countTokens() == 14) {
            for (int i=0;i<11;i++) tokenizer.nextToken();
            LongWritable min = new LongWritable(Long.parseLong(tokenizer.nextToken()));
            tokenizer.nextToken();
            LongWritable max = new LongWritable(Long.parseLong(tokenizer.nextToken()));
            return new RangeWritable(min,max);
        }
        return null;
    }

    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 3) {
            System.out.println("Invalid number of arguments\n\n" + "Usage: histogram <input_path> <output_path> <n_bars>\n\n");
            return -1;
        }
        String input = args[0];
        String output = args[1];
        String nBars = args[2];

        //Here we define a folder for each job output
        String firstJobOutput = output + "/minmax";
        String secondJobOutput = output + "/histogram";

        ToolRunner.run(new HistogramMinMaxJob(), new String[]{input, firstJobOutput});
        RangeWritable range = getRangeFromFile(firstJobOutput);
        ToolRunner.run(new HistogramDistributorJob(), new String[]{input, secondJobOutput, nBars, range.getMin().toString(), range.getMax().toString()});

        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new HistogramFlow(), args);
    }
}
