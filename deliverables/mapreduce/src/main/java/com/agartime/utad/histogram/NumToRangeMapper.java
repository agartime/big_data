package com.agartime.utad.histogram;


import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 06/12/13.
 */
public class NumToRangeMapper extends Mapper<LongWritable, Text, CkIdRange, RangeWritable> {
    private final static VIntWritable one = new VIntWritable(1);
    protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
        RangeWritable out = new RangeWritable(offset,offset);
        context.write(new CkIdRange(one,out), out);
    }
}
