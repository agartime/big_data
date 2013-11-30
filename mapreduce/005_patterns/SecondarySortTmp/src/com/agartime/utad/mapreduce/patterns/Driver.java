package com.agartime.utad.mapreduce.patterns;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.printf("Usage: SolSecondarySortDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		Job job = Job.getInstance();
		job.setJarByClass(Driver.class);
		job.setJobName("Secondary Sort");
	
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapOutputKeyClass(CkIdNum.class);
		job.setMapOutputValueClass(SongNum.class);
		job.setOutputKeyClass(IntWritable.class);
		
		job.setPartitionerClass(IdPartitioner.class);
		job.setSortComparatorClass(IdNumComparator.class);
		job.setGroupingComparatorClass(GroupIdComparator.class);
		
		job.setMapperClass(SecondarySortMapper.class);
		job.setReducerClass(SecondarySortReducer.class);

		
		job.setNumReduceTasks(2);
		
		
		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
