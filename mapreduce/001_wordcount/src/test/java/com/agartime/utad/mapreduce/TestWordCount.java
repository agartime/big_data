package com.agartime.utad.mapreduce;

import com.agartime.utad.mapreduce.WordCount.SplitWordsAsKeyMap;
import com.agartime.utad.mapreduce.WordCount.ValueCounterReducer;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TestWordCount {

  private MapReduceDriver driver;
	
  @Before 
	public void setUp() {
		driver = new MapReduceDriver(new SplitWordsAsKeyMap(), new ValueCounterReducer());
	}
	
  @Test
	public void test() throws IOException {
		driver
		.withInput(new LongWritable(1), new Text("Hello World"))
		.withInput(new LongWritable(2), new Text("World Bye"))
		.withOutput(new Text("Bye"), new VIntWritable(1))
		.withOutput(new Text("Hello"), new VIntWritable(1))
		.withOutput(new Text("World"), new VIntWritable(2))
		.runTest();		
	}	
}
