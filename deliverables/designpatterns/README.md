Design Patterns - Deliverable
=======================

This is a set of deliverable exercises regarding mapreduce design patterns.

* Syslog Analyzer (com.agartime.utad.syslog) - Analizes Syslog and retrieves known events (DHCPREQUEST's from dhclient).

Requirements:
-------------
* [maven](http://maven.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/designpattern*.jar

You can execute the exercises in Hadoop, typing:

       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver

Syslog Analyzer: 
---------- 

   * Package com.agartime.utad:
       
        * Driver.java : Main class. 

   * Package com.agartime.utad.util:

        * DateUtils.java : Utility functions for Data Conversion.

   * Package com.agartime.utad.writables:

        * DateTimeWritable.java - Custom Writable Storing Date and Time.

   * Package com.agartime.utad.syslog.combiners:

        * IdentityCombiner.java -  Identity Combiner.

   * Package com.agartime.utad.syslog.events:

        * SyslogEvent.java - Standard Syslog Event.
    
        * SyslogEventFactory.java - Factory. Parses syslog lines and creates known events.

        * SyslogMatcher.java - Syslog Regex Matcher.

   * Package com.agartime.utad.syslog.events.dhclient:
  
        * DHCPRequestEvent.java - dhclient DHCPRequest Event.
  
        * DHCPRequestMatcher.java - Matches a DHCPREQUEST regex event in syslog.

   * Package com.agartime.utad.syslog.jobs:

        * LogJob.java - Hadoop Job (called from Driver).

   * Package com.agartime.utad.syslog.mappers:
    
        * DateAndEventMapper.java - Mapper. Checks for known events and retrieves its useful info and event date.

   * Package com.agartime.utad.syslog.partitioners:

        * DatePartitioner.java - Partitioner. Parts by Date.

   * Package com.agartime.utad.syslog.reducers:
  
        * IdentityReducer.java - Identity Reducer. 


   Usage: 

      hadoop jar ./target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver logjob input_file output_file n_bars

   Example:

      hdfs dfs -mkdir syslog_in

      hdfs dfs -put ./src/main/resources/syslog* syslog_in

      hadoop jar ./target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver logjob syslog_in syslog_out


You may create your Eclipse project using: 

      $ mvn eclipse:clean eclipse:eclipse

Or, if you're fortunate, your IntelliJ Project using:
  
      $ mvn idea:clean idea:idea

