package com.agartime.utad.salaries.inmapper.reducers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class CounterReducer  extends Reducer<Text, VIntWritable, Text, VIntWritable> {
    @Override
    protected void reduce(Text key, Iterable<VIntWritable> values, Context context)
            throws IOException, InterruptedException {
        int total=0;
        for (VIntWritable count : values) {
            total+=count.get();
        }
        context.write(key,new VIntWritable(total));
    }
}
