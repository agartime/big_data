package com.agartime.utad.syslog.combiners;

import com.agartime.utad.writables.DateTimeWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 04/01/14.
 */
public class IdentityCombiner extends Reducer<DateTimeWritable, Text, DateTimeWritable, Text> {

    @Override
    protected void reduce(DateTimeWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        for (Text ip : values) {
            context.write(key, ip);
        }
    }

}
