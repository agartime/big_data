package com.agartime.utad.mapreduce;

import org.apache.hadoop.util.ProgramDriver;

/**
 * Created with IntelliJ IDEA.
 * User: antoniogonzalezartime
 * Date: 26/11/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class Driver extends ProgramDriver {

    public Driver() throws Throwable {
        super();
        addClass("temperature-changes", TopTemperatureChanges.class, "Calculates the top X temperature changes by city. Built with pangool");
        addClass("flow", TemperatureChangesFlow.class, "Executes both jobs in a flow");
    }

    public static void main(String[] args) throws Throwable {
        Driver driver = new Driver();
        driver.driver(args);
        System.exit(0);
    }
}
