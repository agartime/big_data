Design Patterns - Deliverable
=======================

This is a set of deliverable exercises regarding mapreduce design patterns.

* Syslog Analyzer (com.agartime.utad.syslog) - Analizes Syslog and retrieves known events (DHCPREQUEST's from dhclient).
* Salary Range Distribution using In Mapper Combining (com.agartime.utad.salaries.inmapper).
* Salary Range Distribution using a Map Only Job and counters (com.agartime.utad.salaries.maponly). 

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

       
       hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver logjob <input> <output>


       hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_inmapper <input> <output>


       hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_maponly <input> <output>



Syslog Analyzer: 
----------------

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


      hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver logjob <input> <output>



Example:

      hdfs dfs -mkdir syslog_in

      hdfs dfs -put ./src/main/resources/syslog* syslog_in

      hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver logjob syslog_in syslog_out




Salary Range Distributor: 
-------------------------

* Package com.agartime.utad.salaries:

        * EmployeeSalary.java - Employee Salary POJO.

        * EmployeeSalaryMatcher.java - Regexp Matcher.

        * SalaryRange.java - Salary Ranges.


* Package com.agartime.utad.salaries.inmapper.jobs:

        * InMapperSalariesJob.java - Configures MR job.


* Package com.agartime.utad.salaries.inmapper.mappers:

        * SalaryRangeDistributorMapper.java - In Mapper Combining Mapper.


* Package com.agartime.utad.salaries.inmapper.reducers:

        * CounterReducer.java - Reducer.


Regarding the Map Only solution, there are some extra packages:

* com.agartime.utad.salaries.maponly.jobs:

        * MapOnlySalariesJob.java - Hadoop MR Job.

* com.agartime.utad.salaries.maponly.mappers:

        * SalaryRangeCounterMapper.java - Mapper. Uses several counters to store the result.


Usage: 
       

       hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_inmapper <input> <output>
       
       OR : 
       
       hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_maponly <input> <output>

Example:
      

      hdfs dfs -mkdir salaries_in
      
      hdfs dfs -put ./src/main/resources/load_salaries_dump.clean salaries_in
      
      hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_inmapper salaries_in salaries_out

      OR:
      
      hadoop jar target/designpatterns-1.0-SNAPSHOT.jar com.agartime.utad.Driver salaries_maponly salaries_in salaries_out



Tips
----

You may create your Eclipse project using: 

      $ mvn eclipse:clean eclipse:eclipse

Or, if you're fortunate, your IntelliJ Project using:
  
      $ mvn idea:clean idea:idea


