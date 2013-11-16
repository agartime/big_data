Compactador HDFS
================

Java program to compact a folder in HDFS into a unique file in the same HDFS.

Requirements:
-------------
* [maven](http://maven.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/bigdata.hdfs-1.0-SNAPSHOT-jar-with-dependencies.jar


Usage:
------

You don't need HDFS to execute the program. You can easily test it by using:

bigdata.hdfs.Compactador <glob-origin-files> <destiny-file> (debug)

where: 

* glob-origin-files - [GLOB](http://en.wikipedia.org/wiki/Glob_(programming)) containing the FileSystem Path/s to the candidate files to be compressed.
* destiny-file - Destination file.
* debug - After compression, the file is opened to review its contents in a human-readable format (printed on the screen).

i.e:
      java -jar ./target/bigdata.hdfs-1.0-SNAPSHOT-jar-with-dependencies.jar "*xml" destination_file debug

or:

      hadoop jar ./target/bigdata.hdfs-1.0-SNAPSHOT-jar-with-dependencies.jar bigdata.hdfs.Compactador "*xml" destination_file debug
