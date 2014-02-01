package com.agartime.utad.syslog.reducers;

import com.agartime.utad.writables.DateTimeWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 01/01/14.
 */
public class IdentityReducer extends Reducer<DateTimeWritable, Text, DateTimeWritable, Text> {


    private static MultipleOutputs<DateTimeWritable, Text> multipleOutputs;

    @Override
    public void setup(Context context) {
        multipleOutputs = new MultipleOutputs<DateTimeWritable, Text>(context);
    }

    @Override
    protected void reduce(DateTimeWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        for (Text ip : values) {
            multipleOutputs.write(key, ip, "part-dia"+key.getDay());
            //context.write(key, ip);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException,
            InterruptedException {
        multipleOutputs.close();
    }

}
