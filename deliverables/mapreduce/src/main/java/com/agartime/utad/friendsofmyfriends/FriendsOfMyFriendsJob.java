package com.agartime.utad.friendsofmyfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by antoniogonzalezartime on 12/12/13.
 */
public class FriendsOfMyFriendsJob extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: friends <input_path> <output_path>\n\n");
            return -1;
        }

        Path inputFile = new Path(args[0]);
        Path outputFile = new Path(args[1]);

        FileSystem.get(outputFile.toUri(), getConf()).delete(outputFile, true);

        Configuration conf = new Configuration();
        conf.set("key.value.separator.in.input.line"," ");

        Job job = new Job(conf);
        job.setJobName("Friends Of My Friends Job");


        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setJarByClass(FriendsOfMyFriendsJob.class);
        FileInputFormat.addInputPath(job, inputFile);
        FileOutputFormat.setOutputPath(job, outputFile);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DirectionalRelationshipWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(FriendAndReversalMapper.class);
        job.setReducerClass(MutualRelationshipFinderReducer.class);

        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new FriendsOfMyFriendsJob(), args);
    }

}