package com.agartime.utad;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

import com.agartime.utad.histogram.*;

/**
 * Created by antoniogonzalezartime on 07/12/13.
 */
public class Driver {

    public static void main (String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: Histogram <input_path> <output_path>\n\n");
            return;
        }

        Path inputFile = new Path(args[0]);
        Path outputFile = new Path(args[1]);


        Job job = Job.getInstance();
        job.setJobName("Histogram Sample");

        job.setJarByClass(Driver.class);
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

        //FileInputFormat.addInputPath(job, inputFile);
        //FileOutputFormat.setOutputPath(job, outputFile);

        job.waitForCompletion(true);
    }
}
