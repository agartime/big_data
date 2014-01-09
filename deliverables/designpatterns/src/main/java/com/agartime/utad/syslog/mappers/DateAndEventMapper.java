package com.agartime.utad.syslog.mappers;

import com.agartime.utad.syslog.events.SyslogEvent;
import com.agartime.utad.syslog.events.SyslogEventFactory;
import com.agartime.utad.writables.DateTimeWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 30/12/13.
 */
public class DateAndEventMapper extends Mapper<LongWritable, Text, DateTimeWritable, Text> {
    protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
        SyslogEvent event = SyslogEventFactory.getEvent(text);
        if (event != null) {
            context.write(event.getDateTimeWritable(),new Text(event.getInfo()));
        }
    }
}
