package com.agartime.utad.syslog.partitioners;

import com.agartime.utad.writables.DateTimeWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by antoniogonzalezartime on 04/01/14.
 */
public class DatePartitioner extends Partitioner<DateTimeWritable, Text> {
    @Override
    public int getPartition(DateTimeWritable key, Text value, int numPartitions) {
        return (key.getMonth().get() * 12 + key.getDay().get() * 31 * key.getYear().get()) % numPartitions;
    }

}
