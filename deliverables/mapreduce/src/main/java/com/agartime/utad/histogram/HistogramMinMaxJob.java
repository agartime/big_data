package com.agartime.utad.histogram;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class HistogramMinMaxJob extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: Histogram <input_path> <output_path>\n\n");
            return -1;
        }

        Path inputFile = new Path(args[0]);
        Path outputFile = new Path(args[1]);


        Job job = Job.getInstance();
        job.setJobName("Histogram - Min And Max Value Calculation Job");

        job.setJarByClass(HistogramMinMaxJob.class);
        FileInputFormat.addInputPath(job, inputFile);
        FileOutputFormat.setOutputPath(job, outputFile);

        job.setMapOutputKeyClass(CkIdRange.class);
        job.setMapOutputValueClass(RangeWritable.class);
        job.setOutputKeyClass(CkIdRange.class);
        job.setOutputValueClass(RangeWritable.class);

        job.setMapperClass(NumToRangeMapper.class);
        job.setCombinerClass(MinMaxRangeReducer.class);
        job.setReducerClass(MinMaxRangeReducer.class);

        job.setPartitionerClass(IdPartitioner.class);
        job.setSortComparatorClass(IdRangeComparator.class);
        job.setGroupingComparatorClass(GroupIdComparator.class);

        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new HistogramMinMaxJob(), args);
    }

}
