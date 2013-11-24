Writables Sample - Hottest Day
==============================

Java program. Hottest Day Of Year By Location Problem.

Requirements:
-------------
* [maven](http://maven.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/agartime.writables.*.jar


You may create an Eclipse Project executing:

      $ mvn eclipse:clean eclipse:eclipse

Usage:
------

      hadoop jar ./target/agartime.writables-0.1-job.jar hottest -Dmapred.reduce.tasks=<n_reducer_tasks> <hdfs_input_file> <hdfs_output_file>

where:
* n_reducer_tasks - Number of concurrent reducer jobs.
* hdfs_input_file - HDFS input file path.
* hdfs_output_file - HDFS output file path.

i.e:

      hadoop jar ./target/agartime.wordcount-0.1-job.jar wordcount -Dmapred.reduce.tasks=4 data/avg_temp_per_day_2011.txt data/hottest_output.txt
