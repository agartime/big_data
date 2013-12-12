MapReduce - Deliverable
=======================

This is a set of deliverable exercises regarding mapreduce.

* Histogram (com.agartime.utad.mapreduce.histogram) - Gets the Distribution Histogram of N-Bars given N float numbers.
* FriendsOfMyFriends (com.agartime.utad.mapreduce.friendsofmyfriends) - Gets the Friend Of My Friends in a Social Graph of pairs (FriendOrigin, FriendDestiny)

Requirements:
-------------
* [maven](http://maven.apache.org)

Compilation:
------------

From the folder containing pom.xml file:

      $ mvn clean install

After a successful build, you will find a .jar file into the target directory (or in your local repository):

      ./target/mapreduce*.jar

You can execute the exercises in Hadoop, typing:

       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver

Histogram: 
 
       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver friends input_file output_file n_bars

   Example:

       hdfs dfs -put ./src/main/resources/histogram_input_sample.txt                               
       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver histogram histogram_input_sample.txt histogram.out 30


Friends Of My Friends:

       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver friends input_file output_file

   Example:

       hdfs dfs -put ./src/main/resources/mutualfriends_input_sample.txt 
       hadoop jar ./target/mapreduce-1.0-SNAPSHOT.jar com.agartime.utad.Driver friends mutualfriends_input_sample.txt friends.out

You may create an Eclipse Project executing:

      $ mvn eclipse:clean eclipse:eclipse

