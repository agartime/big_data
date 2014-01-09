package com.agartime.utad.salaries.maponly.mappers;

import com.agartime.utad.salaries.EmployeeSalary;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class SalaryRangeCounterMapper extends Mapper<LongWritable, Text, Text, VIntWritable> {
    private static int minSalary;
    private static int maxSalary;

    protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
        EmployeeSalary employee = new EmployeeSalary(text.toString());
        if (employee.isCurrentSalary() && employee.earnsBetween(minSalary,maxSalary)) {
                context.getCounter("salaries", employee.getRangeDescription()).increment( 1);
        }
    }

    protected void setup(Context context) throws IOException,InterruptedException
    {
        Configuration conf = context.getConfiguration();
        minSalary = Integer.parseInt(conf.get("com.agartime.utad.salaries.min"));
        maxSalary = Integer.parseInt(conf.get("com.agartime.utad.salaries.max"));
    }
}

