#!/bin/sh
GIT_PATH=/home/cloudera/twitter/cdh-twitter-example/hive-serdes
cd ${GIT_PATH}
mvn package
sudo cp target/hive-serdes-1.0-SNAPSHOT.jar /usr/local/lib
cd -
