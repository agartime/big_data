package com.agartime.utad;

import org.apache.hadoop.util.ProgramDriver;
import com.agartime.utad.histogram.HistogramFlow;
import com.agartime.utad.friendsofmyfriends.FriendsOfMyFriendsJob;

/**
 * Created by antoniogonzalezartime on 07/12/13.
 */
public class Driver  extends ProgramDriver {

    public Driver() throws Throwable {
        super();
        addClass("histogram", HistogramFlow.class, "Creates the histogram");
        addClass("friends", FriendsOfMyFriendsJob.class, "Gets the friends of my friends");
    }

    public static void main(String[] args) throws Throwable {
        Driver driver = new Driver();
        driver.driver(args);
        System.exit(0);
    }
}
