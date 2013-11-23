package com.agartime.utad.mapreduce;

import org.apache.hadoop.util.ProgramDriver;

public class Driver extends ProgramDriver {

	public Driver() throws Throwable {
		super();
		addClass("wordcount", WordCount.class, "Calculates the number of repetitions of every word in the file");
	}

	public static void main(String[] args) throws Throwable {
		Driver driver = new Driver();
		driver.driver(args);
		System.exit(0);
	}
}
