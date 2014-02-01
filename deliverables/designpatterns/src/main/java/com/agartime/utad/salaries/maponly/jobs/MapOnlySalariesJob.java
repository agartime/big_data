package com.agartime.utad.salaries.maponly.jobs;

import com.agartime.utad.salaries.SalaryRange;
import com.agartime.utad.salaries.maponly.mappers.SalaryRangeCounterMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class MapOnlySalariesJob extends Configured implements Tool {
    // Maybe in the future we would like to get > 59999 USD....
    private static String MIN_SALARY="30000";
    private static String MAX_SALARY="59999";

    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Invalid number of arguments\n\n" +
                    "Usage: salaries_inmapper <input_path> <output_path>\n\n");
            return -1;
        }

        Path inputDir = new Path(args[0]);
        Path outputDir = new Path(args[1]);
        FileSystem.get(outputDir.toUri(), getConf()).delete(outputDir, true);

        Configuration conf = new Configuration();
        conf.set("com.agartime.utad.salaries.min", MIN_SALARY);
        conf.set("com.agartime.utad.salaries.max", MAX_SALARY);


        Job job = Job.getInstance(conf);
        job.setJobName("Gets Salaries Distribution using a Map Only Job.");
        job.setJarByClass(MapOnlySalariesJob.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, inputDir);
        FileOutputFormat.setOutputPath(job, outputDir);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(VIntWritable.class);

        job.setMapperClass(SalaryRangeCounterMapper.class);
        job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? 0 : 1;

        /*
 	//We could get the counters directly in Driver using:
        String descriptionRange30=SalaryRange.getDescription(SalaryRange.rangeForSalary(30000));
        String descriptionRange40=SalaryRange.getDescription(SalaryRange.rangeForSalary(40000));
        String descriptionRange50=SalaryRange.getDescription(SalaryRange.rangeForSalary(50000));
        long total30 = job.getCounters().findCounter("salaries", descriptionRange30).getValue();
        long total40 = job.getCounters().findCounter("salaries", descriptionRange40).getValue();
        long total50 = job.getCounters().findCounter("salaries", descriptionRange50).getValue();

        System.out.println("descriptionRange30"+" - "+total30);
        System.out.println("descriptionRange30"+" - "+total40);
        System.out.println("descriptionRange30"+" - "+total50);
        return 0;
        */
    }

    public static void main(String args[]) throws Exception {
        ToolRunner.run(new MapOnlySalariesJob(), args);
    }
}
