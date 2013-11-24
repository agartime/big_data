package com.agartime.utad.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * This class is a copy of {@link IntWritable} that
 * can be used as reference with exercises writing your
 * own writables.
 */
public class LocationYearWritable implements WritableComparable<LocationYearWritable> {
  private String location;
  private int year;

  public LocationYearWritable() {}

  public LocationYearWritable(String location, int year) { set(location,year); }

  public void set(String location, int year) {
	  this.location = location;
	  this.year = year;
  }
  
  public int getYear() { return this.year; }
  public String getLocation() { return this.location; }

  @Override
  public String toString() {
    return Integer.toString(this.year)+"|"+this.location;
  }
  
  // ********************************************
  // * Important methods to override from Object
  // ********************************************

  /**
   * HashCode must be implemented properly. Otherwise, Hadoop
   * MapReduce won't work properly, as the {@link Partition}
   * won't route properly tuples to the corresponding reducer. 
   */
  @Override
  public int hashCode() {
    return year+location.length();
  }
  
  /**
   * Convenient to implement
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LocationYearWritable))
      return false;
    LocationYearWritable other = (LocationYearWritable)o;
    return (this.year == other.year && this.location.equals(other.location));
  }

  
  // *********************
  // * Writable interface
  // *********************
  public void readFields(DataInput in) throws IOException {
    this.year = in.readInt();
    this.location = Text.readString(in);
  }

  public void write(DataOutput out) throws IOException {
    out.writeInt(year);
    Text.writeString(out, location);
  }

  // *********************
  // * Comparable interface
  // *********************  
  
  /**
   * Hadoop would use this method if no {@link WritableComparator}
   * is registered with the method {@link WritableComparator#define(Class, WritableComparator)}
   * or no custom {@link RawComparator} is provided in Job configuration.
   * 
   * It is not recommended this method of comparison as it is
   * highly inefficient. It is recommended to use {@link WritableComparator}
   * or {@link RawComparator}
   * 
   * Anyway, it is interesting to have this method implemented as
   * can be useful for testing, or other Java code.
   */
  @Override
  public int compareTo(LocationYearWritable o) {
    int thisYear = this.year;
    int thatYear = o.year;
    String thisLocation = this.location;
    String thatLocation = o.location;
    
    return ((thisYear < thatYear) ? -1 : ((thisYear == thatYear && thisLocation.equals(thatLocation)) ? 0 : thisLocation.compareTo(thatLocation)));
  }
  
  // ***********************************
  // * More efficient way of comparison 
  // ***********************************  
 /* 
  /* A Comparator optimized for LocationYearWritable. */ 
  /*
   * public static class Comparator extends WritableComparator {
    public Comparator() {
      super(LocationYearWritable.class);
    }
    
    @Override
    public int compare(byte[] b1, int start1, int length1,
                       byte[] b2, int start2, int length2) {
      int thisValue = readInt(b1, start1);
      int thatValue = readInt(b2, start2);
      return (thisValue<thatValue ? -1 : (thisValue==thatValue ? 0 : 1));
    }
  }

  static {                                
    WritableComparator.define(LocationYearWritable.class, new Comparator());
  }*/
}
