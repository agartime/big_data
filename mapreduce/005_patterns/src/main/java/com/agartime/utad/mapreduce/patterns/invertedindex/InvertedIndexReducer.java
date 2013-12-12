package com.agartime.utad.mapreduce.patterns.invertedindex;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		String out = "";
		for (Text str : values) {
			out=out.concat(str.toString()+",");
		}
		
		//TODO Delete last quote
		context.write(key, new Text(out));
	}
}
