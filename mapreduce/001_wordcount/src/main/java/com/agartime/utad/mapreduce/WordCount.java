package com.agartime.utad.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Splits input lines in words using one as value
 */
public class WordCount extends Configured implements Tool {

	public static class SplitWordsAsKeyMap extends Mapper<LongWritable, Text, Text, VIntWritable> {
		private final static VIntWritable one = new VIntWritable(1);
		private Text word = new Text();

		@Override
		protected void map(LongWritable offset, Text line, Context context) 
				throws IOException ,InterruptedException {
			StringTokenizer itr = new StringTokenizer(line.toString());
			while(itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				// Counter Example
				//if (word.length() > 0 && word.charAt(0)=='a') {
				//	context.getCounter("empiezaPor","a").increment(1);	
				//}		
				context.write(word, one);
			}
		};

	}
 
  /**
   * Counts every one received as value
   */
  public static class ValueCounterReducer extends Reducer<Text, VIntWritable, Text, VIntWritable> {

  	protected void reduce(Text line, Iterable<VIntWritable> ones, Context context) 
  			throws IOException ,InterruptedException {
  		int total=0;
  		for(VIntWritable one: ones) {
  			total+=one.get();
  		}  		
  		context.write(line, new VIntWritable(total));	

  	};
  			
	}
		
	@Override
  public int run(String[] args) throws Exception {
		if(args.length != 2) {
			System.out.println("Invalid number of arguments\n\n" +
					"Usage: sort-hadoop <input_path> <output_path>\n\n");
			return -1;
		}
		String input = args[0];
		String output = args[1];
		
		Path oPath = new Path(output);
		FileSystem.get(oPath.toUri(), getConf()).delete(oPath, true);
		
		Job job = new Job(getConf(), "WordCount Hadoop");
		job.setJarByClass(WordCount.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(VIntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(VIntWritable.class);
		
		job.setMapperClass(SplitWordsAsKeyMap.class);
		job.setReducerClass(ValueCounterReducer.class); 
		job.setCombinerClass(ValueCounterReducer.class);
		
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		job.waitForCompletion(true);
		
		return 0;
  }

	public static void main(String args[]) throws Exception {
		ToolRunner.run(new WordCount(), args);
	}
}
