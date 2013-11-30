package com.agartime.utad.mapreduce.patterns;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SecondarySortMapper  extends Mapper<LongWritable, Text, CkIdNum, SongNum> implements WritableComparable<Text>{
	
	private IntWritable id = new IntWritable();
	private Text song = new Text();
	private IntWritable num = new IntWritable();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// input line is :
				// 248,"All my loving",13
				String[] word = value.toString().split(",");

				// first, don't consider the line if the song was written less than 5
				// time
				if (Integer.parseInt(word[2]) < 5)
					return;

				id.set(Integer.parseInt(word[0]));
				song.set(word[1]);
				num.set(Integer.parseInt(word[2]));

				CkIdNum myKey = new CkIdNum(id, num);
				SongNum val = new SongNum(song, num);
				context.write(myKey, val);
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Text o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
