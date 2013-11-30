package com.agartime.utad.mapreduce.graphs;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class GraphMapper  extends Mapper<IntWritable, Text, IntWritable, NodeWritable> {

	private IntWritable nodeId;
	private IntWritable nodeDistance;
	private Text friendNodes;
	
	@Override
	public void map(IntWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		this.nodeId=key;
		
		StringTokenizer tokenizer = new StringTokenizer(value.toString(),",()");
		if (tokenizer.countTokens()<2) 
			return;
		
		String distance = tokenizer.nextToken();
		this.nodeDistance=(distance.equals("inf") ? new IntWritable(-1) : new IntWritable(Integer.parseInt(distance)));
		
		this.friendNodes = new Text("");
		int counter = 0;
		while (tokenizer.hasMoreTokens()) {
			this.friendNodes=new Text(this.friendNodes+","+tokenizer.nextToken());//=new IntWritable(Integer.parseInt(tokenizer.nextToken()));
		}
		//Review
		context.write(this.nodeId,new NodeWritable(this.nodeId, this.nodeDistance, this.friendNodes));
		
		if (this.nodeDistance.get() >= 0) {
			for (IntWritable friendId : this.friendNodes) {
				context.write(friendId, new NodeWritable(this.nodeId));
			}
		}
		
		
	}
	
	public static void main(String [] args) {
		//Just for testing
		String text="4,(1,2,3,4,5)";
		StringTokenizer tokenizer = new StringTokenizer(text,",()");
		
		
		while (tokenizer.hasMoreTokens()) {
			System.out.println(tokenizer.nextToken());
		}

	}
}
