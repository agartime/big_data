package com.agartime.utad.mapreduce.graphs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Driver {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out
					.printf("Usage: Graph <input dir> <output dir>\n");
			System.exit(-1);
		}
		Job job = Job.getInstance();
		
		job.setJarByClass(Driver.class);
		job.setJobName("Graph");
		
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setMapperClass(GraphMapper.class);
		job.setReducerClass(GraphReducer.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// TODO completar

		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
