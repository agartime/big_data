package com.agartime.utad.histogram;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class BarSummatorReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {


    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> ones, Context context) throws IOException, InterruptedException {
        int total = 0;
        for (IntWritable one : ones) {
            total+=one.get();
        }
        context.write(key,new IntWritable(total));
    }
}
