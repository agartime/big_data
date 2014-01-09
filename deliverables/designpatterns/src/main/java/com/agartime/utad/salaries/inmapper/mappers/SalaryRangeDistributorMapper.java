package com.agartime.utad.salaries.inmapper.mappers;

import com.agartime.utad.salaries.EmployeeSalary;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class SalaryRangeDistributorMapper extends Mapper<LongWritable, Text, Text, VIntWritable> {
    private Map<String, Integer> salaries;
    private static int minSalary;
    private static int maxSalary;

    protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
        Map<String, Integer> map = getSalariesMap();
        EmployeeSalary employee = new EmployeeSalary(text.toString());
        if (employee.isCurrentSalary() && employee.earnsBetween(minSalary,maxSalary)) {
            if(map.containsKey(employee.getRangeDescription())) {
                int total = map.get(employee.getRangeDescription()) + 1;
                map.put(employee.getRangeDescription(),total);
            } else {
                map.put(employee.getRangeDescription(),1);
            }
        }
    }

    protected void setup(Context context) throws IOException,InterruptedException
    {
        Configuration conf = context.getConfiguration();
        minSalary = Integer.parseInt(conf.get("com.agartime.utad.salaries.min"));
        maxSalary = Integer.parseInt(conf.get("com.agartime.utad.salaries.max"));
    }

    protected void cleanup(Context context) throws IOException, InterruptedException {
        Map<String, Integer> map = getSalariesMap();
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            String sKey = entry.getKey();
            int total = entry.getValue().intValue();
            context.write(new Text(sKey), new VIntWritable(total));
        }
    }

    public Map<String, Integer> getSalariesMap() {
        if(null == salaries)
            salaries = new HashMap<String, Integer>();
        return salaries;
    }
}
