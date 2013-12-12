package com.agartime.utad.histogram;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 07/12/13.
 */
public class RangeWritable extends Configured implements WritableComparable <RangeWritable> {
    private LongWritable min;
    private LongWritable max;

    public RangeWritable(LongWritable min, LongWritable max) {
        this.min = new LongWritable(min.get());
        this.max = new LongWritable(max.get());
    }

    public RangeWritable() {
       this.min = new LongWritable(Long.MIN_VALUE);
       this.max = new LongWritable(Long.MAX_VALUE);
    }

    public LongWritable getMin() {
        return min;
    }

    public void setMin(LongWritable min) {
        this.min = min;
    }

    public LongWritable getMax() {
        return max;
    }

    public void setMax(LongWritable max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "RangeWritable{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RangeWritable that = (RangeWritable) o;

        if (max != null ? !max.equals(that.max) : that.max != null) return false;
        if (min != null ? !min.equals(that.min) : that.min != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(RangeWritable rangeWritable) {
        return this.max.compareTo(rangeWritable.max);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        this.min.write(dataOutput);
        this.max.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.min.readFields(dataInput);
        this.max.readFields(dataInput);
    }
}
