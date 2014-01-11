#!/bin/sh
set -x
hdfs dfs -rm -r palabras
HDP_CONTRIB=/usr/lib/hadoop-0.20-mapreduce/contrib/
HDP_STRM=${HDP_CONTRIB}/streaming
STREAMING_JAR=${HDP_STRM}/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar
hadoop jar ${STREAMING_JAR} \
  -input texto.txt \
  -output palabras \
  -file mapper.rb \
  -file reducer.rb \
  -mapper mapper.rb \
  -reducer reducer.rb

hdfs dfs -cat palabras/part*
