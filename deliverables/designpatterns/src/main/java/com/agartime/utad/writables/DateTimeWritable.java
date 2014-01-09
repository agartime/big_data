package com.agartime.utad.writables;

import com.agartime.utad.util.DateUtils;

import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by antoniogonzalezartime on 30/12/13.
 */
public class DateTimeWritable implements WritableComparable<DateTimeWritable> {

    private VIntWritable day;
    private VIntWritable month;
    private VIntWritable year;
    private VIntWritable hour;
    private VIntWritable minute;
    private VIntWritable second;

    public DateTimeWritable() {
        this.day = new VIntWritable();
        this.month = new VIntWritable();
        this.year = new VIntWritable();
        this.hour = new VIntWritable();
        this.minute = new VIntWritable();
        this.second = new VIntWritable();
    }

    public DateTimeWritable(short year, byte month, byte day, byte hour, byte minute, byte second) {
        this.day = new VIntWritable(day);
        this.month = new VIntWritable(month);
        this.year = new VIntWritable(year);
        this.hour = new VIntWritable(hour);
        this.minute = new VIntWritable(minute);
        this.second = new VIntWritable(second);
    }


    public VIntWritable getDay() {
        return day;
    }

    public void setDay(VIntWritable day) {
        this.day = day;
    }

    public VIntWritable getMonth() {
        return month;
    }

    public void setMonth(VIntWritable month) {
        this.month = month;
    }

    public VIntWritable getYear() {
        return year;
    }

    public void setYear(VIntWritable year) {
        this.year = year;
    }

    public VIntWritable getHour() {
        return hour;
    }

    public void setHour(VIntWritable hour) {
        this.hour = hour;
    }

    public VIntWritable getMinute() {
        return minute;
    }

    public void setMinute(VIntWritable minute) {
        this.minute = minute;
    }

    public VIntWritable getSecond() {
        return second;
    }

    public void setSecond(VIntWritable second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateTimeWritable that = (DateTimeWritable) o;

        if (!day.equals(that.day)) return false;
        if (!hour.equals(that.hour)) return false;
        if (!minute.equals(that.minute)) return false;
        if (!month.equals(that.month)) return false;
        if (!second.equals(that.second)) return false;
        if (!year.equals(that.year)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + month.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + hour.hashCode();
        result = 31 * result + minute.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }

    @Override
    public int compareTo(DateTimeWritable dateTimeWritable) {
        int cmp = this.year.compareTo(dateTimeWritable.getYear());
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.month.compareTo(dateTimeWritable.getMonth());
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.day.compareTo(dateTimeWritable.getDay());
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.hour.compareTo(dateTimeWritable.getHour());
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.minute.compareTo(dateTimeWritable.getMinute());
        if (cmp != 0) {
            return cmp;
        }

        return this.second.compareTo(dateTimeWritable.getSecond());

    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        this.year.write(dataOutput);
        this.month.write(dataOutput);
        this.day.write(dataOutput);
        this.hour.write(dataOutput);
        this.minute.write(dataOutput);
        this.second.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year.readFields(dataInput);
        this.month.readFields(dataInput);
        this.day.readFields(dataInput);
        this.hour.readFields(dataInput);
        this.minute.readFields(dataInput);
        this.second.readFields(dataInput);
    }

    @Override
    public String toString() {
        Date date = DateUtils.getDate(this.month.get(), this.day.get(), this.hour.get(), this.minute.get(), this.second.get());
        SimpleDateFormat formattedDate = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]");
        return formattedDate.format(date);
    }

}
