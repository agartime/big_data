package com.agartime.utad.histogram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 10/12/13.
 */
public class BarDistributorMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private static long min;
    private static long max;
    private static int numberOfBars;


    protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
        if (offset.get() == max) {
            context.write(new IntWritable(numberOfBars-1),one);
        } else {
            int bar = (int)Math.floor((offset.get()-min)/((max-min)/numberOfBars));
            context.write(new IntWritable(bar),one);
        }
    }

    protected void setup(Context context) throws IOException,InterruptedException
    {
        Configuration conf = context.getConfiguration();
        min = Long.parseLong(conf.get("min"));
        max = Long.parseLong(conf.get("max"));
        numberOfBars = Integer.parseInt(conf.get("numberOfBars"));
    }
}
