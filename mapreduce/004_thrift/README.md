Thrift  Sample - Temperature Changes
====================================

Java program. Top Temperature Changes by City Sample using Pangool and Thrift.

Requirements:
-------------
* [maven](http://maven.apache.org)
* [pangool](https://github.com/datasalt/pangool)
* [thrift](http://thrift.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/com.agartime.utad.mapreduce.thrift.*.jar


You may create an Eclipse Project executing:

      $ mvn eclipse:clean eclipse:eclipse

Usage:
------

      hadoop jar ./target/com.agartime.utad.mapreduce.thrift-0.1-job.jar thrift_flow -Dmapred.reduce.tasks=<n_reducer_tasks> <hdfs_input_file> <hdfs_output_file>

where:
* n_reducer_tasks - Number of concurrent reducer jobs.
* hdfs_input_file - HDFS input file path.
* hdfs_output_file - HDFS output file path.

i.e:

      hadoop jar ./target/com.agartime.utad.mapreduce.thrift-0.1-job.jar thrift_flow -Dmapred.reduce.tasks=4 data/max_temp_2011.txt data/thrift_output
