package com.agartime.utad.mapreduce;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created with IntelliJ IDEA.
 * User: antoniogonzalezartime
 * Date: 27/11/13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class TemperatureChangesFlow extends Configured implements Tool{
    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 3) {
            System.out.println("Invalid number of arguments\n\n" + "Usage: temperatures-flow <input_path> <output_path> <top size>\n\n");
            return -1;
        }
        String input = args[0];
        String output = args[1];
        String topSize = args[2];

        //Here we define a folder for each job output
        String firstJobOutput = output + "/changes";
        String secondJobOutput = output + "/top-changes";

        ToolRunner.run(new TemperatureChanges(), new String[]{input, firstJobOutput});
        ToolRunner.run(new TopTemperatureChanges(), new String[]{firstJobOutput, secondJobOutput, topSize});

        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new TemperatureChangesFlow(), args);
    }
}
