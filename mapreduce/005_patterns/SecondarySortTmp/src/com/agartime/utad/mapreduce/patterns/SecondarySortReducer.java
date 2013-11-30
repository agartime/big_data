package com.agartime.utad.mapreduce.patterns;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class SecondarySortReducer extends Reducer<CkIdNum, SongNum, IntWritable, Text>  {
	Text outputValue = new Text();

	@Override
	protected void reduce(CkIdNum key, Iterable<SongNum> values, Context context)
			throws IOException, InterruptedException {
		
		StringBuilder sb = new StringBuilder("");
		for (SongNum sn: values){
			sb = sb.append( sn.toString());
		}
		outputValue.set( sb.toString());
		context.write(key.getId(), outputValue);

	}

}
