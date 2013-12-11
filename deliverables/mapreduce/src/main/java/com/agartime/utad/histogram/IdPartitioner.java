package com.agartime.utad.histogram;

import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class IdPartitioner extends Partitioner<CkIdRange, RangeWritable> {
    @Override
    public int getPartition(CkIdRange key, RangeWritable value, int numPartitions) {
        // multiply by 127 to perform some mixing
        return Math.abs(key.getId().get() * 127) % numPartitions;
    }
}
