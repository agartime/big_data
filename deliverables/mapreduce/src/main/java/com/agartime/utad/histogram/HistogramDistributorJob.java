package com.agartime.utad.histogram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class HistogramDistributorJob extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 5) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: HistogramDistributor <input_path> <output_path> <bar_number>\n\n");
            return -1;
        }

        Path inputFile = new Path(args[0]);
        Path outputFile = new Path(args[1]);

        Configuration conf = new Configuration();

        conf.set("numberOfBars", args[2]);
        conf.set("min", args[3]);
        conf.set("max", args[4]);

        Job job = new Job(conf); // Job.getInstance();
        job.setJobName("Histogram - Distributor Job");
        job.setJarByClass(HistogramDistributorJob.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(BarDistributorMapper.class);
        job.setCombinerClass(BarSummatorReducer.class);
        job.setReducerClass(BarSummatorReducer.class);

        FileInputFormat.addInputPath(job, inputFile);
        FileOutputFormat.setOutputPath(job, outputFile);


        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new HistogramDistributorJob(), args);
    }
}

