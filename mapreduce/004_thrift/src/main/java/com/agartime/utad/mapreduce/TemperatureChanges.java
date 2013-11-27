package com.agartime.utad.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.datasalt.pangool.io.ITuple;
import com.datasalt.pangool.io.Schema;
import com.datasalt.pangool.io.Schema.Field;
import com.datasalt.pangool.io.Schema.Field.Type;
import com.datasalt.pangool.io.Tuple;
import com.datasalt.pangool.tuplemr.Criteria.Order;
import com.datasalt.pangool.tuplemr.OrderBy;
import com.datasalt.pangool.tuplemr.TupleMRBuilder;
import com.datasalt.pangool.tuplemr.TupleMRException;
import com.datasalt.pangool.tuplemr.TupleMapper;
import com.datasalt.pangool.tuplemr.TupleReducer;
import com.datasalt.pangool.tuplemr.mapred.lib.input.HadoopInputFormat;
import com.datasalt.pangool.tuplemr.mapred.lib.output.HadoopOutputFormat;

/**
 * Calculates the temperature changes between days. 
 * The accepted input is text files with following format:<br>
 * <code>
 * [location] [daySinceEpoch] [temperature]
 * <code>
 * <br>
 * daySinceEpoch expected format is integer. 
 * temperature expected format is float.
 * <br>
 * The output is a text file with rows containing:
 * <code>
 * [location] [daySinceEpoch] [tempDifference]
 * </code>  
 */
public class TemperatureChanges extends Configured implements Tool {

    public static Schema getSchema() {
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(Field.create("location", Type.STRING));
        fields.add(Field.create("day", Type.INT));
        fields.add(Field.create("temperature", Type.FLOAT));
        return new Schema("Temperature", fields);
    }

    @SuppressWarnings("serial")
    public static class ParseMap extends TupleMapper<LongWritable, Text> {

        Tuple tuple = new Tuple(getSchema());

        @Override
        public void map(LongWritable offset, Text line, TupleMRContext context,
                        Collector collector) throws IOException, InterruptedException {
            String[] fields = line.toString().split("[ \\t]");

            if (fields.length != 3) {
                throw new IOException("Wrong line: " + line.toString());
            }

            tuple.set("location", fields[0]);
            tuple.set("day", new Integer(fields[1]));
            tuple.set("temperature", new Float(fields[2]));

            collector.write(tuple);
        }

    }

    @SuppressWarnings("serial")
    public static class DifferencesReducer extends TupleReducer<Text, NullWritable> {

        public void reduce(ITuple group, Iterable<ITuple> tuples, TupleMRContext context,
                           Collector collector) throws IOException, InterruptedException, TupleMRException {

            Iterator<ITuple> iterator = tuples.iterator();
            ITuple tuple = iterator.next();
            Integer lastDay = (Integer) tuple.get("day");
            Float lastTemp = (Float) tuple.get("temperature");

            while (iterator.hasNext()) {
                tuple = iterator.next();
                Integer currentDay = (Integer) tuple.get("day");
                Float currentTemp = (Float) tuple.get("temperature");

                if ( (currentDay - 1) == lastDay) {
                    String out = tuple.get("location") + " " + currentDay + " " + (currentTemp - lastTemp);
                    collector.write(new Text(out), NullWritable.get());
                }

                lastDay = currentDay;
                lastTemp = currentTemp;
            }
        };
    }

    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: temperature-changes <input_path> <output_path>\n\n");
            return -1;
        }
        String input = args[0];
        String output = args[1];

        Path oPath = new Path(output);
        FileSystem.get(oPath.toUri(), getConf()).delete(oPath, true);

        TupleMRBuilder mr = new TupleMRBuilder(getConf(), "Temperature changes");
        mr.setJarByClass(TemperatureChanges.class);
        mr.addIntermediateSchema(getSchema());
        mr.setGroupByFields("location");
        mr.setOrderBy(new OrderBy().add("location", Order.ASC).add("day", Order.ASC));
        mr.setTupleReducer(new DifferencesReducer());
        mr.addInput(new Path(input), new HadoopInputFormat(TextInputFormat.class), new ParseMap());
        mr.setOutput(new Path(output), new HadoopOutputFormat(TextOutputFormat.class), Text.class, NullWritable.class);
        mr.createJob().waitForCompletion(true);

        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new TemperatureChanges(), args);
    }
}
