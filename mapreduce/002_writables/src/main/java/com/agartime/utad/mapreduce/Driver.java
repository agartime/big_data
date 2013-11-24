package com.agartime.utad.mapreduce;

import com.agartime.utad.mapreduce.HottestDayPerYearWritables;
import org.apache.hadoop.util.ProgramDriver;

public class Driver extends ProgramDriver {

	public Driver() throws Throwable {
		super();
		addClass("hottest", HottestDayPerYearWritables.class, "Calculates the average temperature in the hottest day in each year for each location. Using writables");
	}

	public static void main(String[] args) throws Throwable {
		Driver driver = new Driver();
		driver.driver(args);
		System.exit(0);
	}
}
