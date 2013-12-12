package com.agartime.utad.histogram;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by antoniogonzalezartime on 08/12/13.
 */
public class MinMaxRangeReducer extends Reducer<CkIdRange, RangeWritable, CkIdRange, RangeWritable> {
    private final static VIntWritable one = new VIntWritable(1);
    LongWritable max = new LongWritable(Long.MIN_VALUE);
    LongWritable min = new LongWritable(Long.MAX_VALUE);

    @Override
    protected void reduce(CkIdRange key, Iterable<RangeWritable> ranges, Context context) throws IOException, InterruptedException {

        Iterator<RangeWritable> iterator = ranges.iterator();

        if (!iterator.hasNext())
            return;
        else {
            long min, max;
            RangeWritable range = iterator.next();
            min = range.getMin().get();
            max = range.getMax().get();
            while (iterator.hasNext()) max = iterator.next().getMax().get();
            RangeWritable output = new RangeWritable(new LongWritable(min), new LongWritable(max)) ;
            context.write(new CkIdRange(one,output), output);
        }
    }
}
