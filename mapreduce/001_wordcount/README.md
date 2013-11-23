WordCount
================

Java program. WordCount Problem.

Requirements:
-------------
* [maven](http://maven.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/bigdata.hdfs-1.0-SNAPSHOT-jar-with-dependencies.jar


You may create an Eclipse Project executing:

      $ mvn eclipse:clean eclipse:eclipse

Usage:
------

      hadoop jar ./target/hadoop-java-api-mapreduce-001-wordcount-0.1-job.jar wordcount -Dmapred.reduce.tasks=<n_reducer_tasks> <hdfs_input_file> <hdfs_output_file>

where:
* n_reducer_tasks - Number of concurrent reducer jobs.
* hdfs_input_file - HDFS input file path.
* hdfs_output_file - HDFS output file path.

i.e:

      hadoop jar ./target/hadoop-java-api-mapreduce-001-wordcount-0.1-job.jar wordcount -Dmapred.reduce.tasks=4 data/oliver-twist.txt data/wordcount_output.txt
