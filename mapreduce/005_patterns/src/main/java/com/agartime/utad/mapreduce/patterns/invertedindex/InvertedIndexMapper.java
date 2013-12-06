package com.agartime.utad.mapreduce.patterns.invertedindex;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

	// TODO write initialization method

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {

		FileSplit fileSplit = (FileSplit) context.getInputSplit(); 
		Path path = fileSplit.getPath(); 
		String fileName = path.getName();
		
		String line = value.toString();
		for (String word : line.split("\\W+")) {
			if (word.length() > 0) {
				context.write(new Text(word), new Text(fileName+"#"+key));
			}
		}
	}
}
