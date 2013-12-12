package com.agartime.utad.mapreduce.patterns.invertedindex;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InvertedIndexDriver {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: InvertedIndex <input dir> <output dir>\n");
			System.exit(-1);
		}
		Job job = new Job(); //job.getInstance();
		job.setJarByClass(InvertedIndexDriver.class);
		job.setJobName("Inverted Index");
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setMapperClass(InvertedIndexMapper.class);
		job.setReducerClass(InvertedIndexReducer.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// TODO completar

		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
