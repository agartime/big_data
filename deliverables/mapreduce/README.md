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


You may create an Eclipse Project executing:

      $ mvn eclipse:clean eclipse:eclipse

