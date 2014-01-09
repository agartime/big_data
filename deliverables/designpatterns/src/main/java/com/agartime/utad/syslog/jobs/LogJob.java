package com.agartime.utad.syslog.jobs;

import com.agartime.utad.syslog.combiners.IdentityCombiner;
import com.agartime.utad.syslog.mappers.DateAndEventMapper;
import com.agartime.utad.syslog.partitioners.DatePartitioner;
import com.agartime.utad.syslog.reducers.IdentityReducer;
import com.agartime.utad.writables.DateTimeWritable;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by antoniogonzalezartime on 30/12/13.
 */
public class LogJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: logjob <input_path> <output_path>\n\n");
            return -1;
        }

        Path inputDir = new Path(args[0]);
        Path outputDir = new Path(args[1]);
        FileSystem.get(outputDir.toUri(), getConf()).delete(outputDir, true);

        Job job = Job.getInstance(getConf());
        job.setJobName("Syslog Analyzer");
        job.setJarByClass(LogJob.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        //job.setNumReduceTasks(8);
        FileInputFormat.setInputPaths(job, inputDir);
        FileOutputFormat.setOutputPath(job, outputDir);

        job.setOutputKeyClass(DateTimeWritable.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(DateAndEventMapper.class);
        job.setCombinerClass(IdentityCombiner.class);
        job.setReducerClass(IdentityReducer.class);
        job.setPartitionerClass(DatePartitioner.class);

        return (job.waitForCompletion(true) ? 0 : 1);

    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new LogJob(), args);
    }
}
