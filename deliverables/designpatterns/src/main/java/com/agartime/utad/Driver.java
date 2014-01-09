/**
 * Created by antoniogonzalezartime on 27/12/13.
 */
package com.agartime.utad;

import com.agartime.utad.syslog.jobs.LogJob;
import com.agartime.utad.salaries.inmapper.jobs.InMapperSalariesJob;
import com.agartime.utad.salaries.maponly.jobs.MapOnlySalariesJob;

import org.apache.hadoop.util.ProgramDriver;

public class Driver extends ProgramDriver {

    public Driver() throws Throwable {
        super();
        addClass("logjob", LogJob.class, "Analizes syslog");
        addClass("salaries_inmapper", InMapperSalariesJob.class, "Gets Salaries Distribution using In Mapper Combining.");
        addClass("salaries_maponly", MapOnlySalariesJob.class, "Gets Salaries Distribution using Map Only.");

        //addClass("friends", FriendsOfMyFriendsJob.class, "Gets the friends of my friends");
    }

    public static void main(String[] args) throws Throwable {
        Driver driver = new Driver();
        driver.driver(args);
        System.exit(0);
    }
}
