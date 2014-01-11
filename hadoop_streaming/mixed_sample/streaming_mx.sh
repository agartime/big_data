#!/bin/sh
set -x
hdfs dfs -rm -r palabras
HDP_CONTRIB=/usr/lib/hadoop-0.20-mapreduce/contrib/
HDP_STRM=${HDP_CONTRIB}/streaming
STREAMING_JAR=${HDP_STRM}/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar
INPUT_MAPPER=../ruby_sample/mapper.rb
INPUT_REDUCER=../awk_sample/reducer.awk
hadoop jar ${STREAMING_JAR} \
  -input texto.txt \
  -output palabras \
  -file $INPUT_MAPPER \
  -file $INPUT_REDUCER \
  -mapper mapper.rb \
  -reducer "gawk -f reducer.awk"

hdfs dfs -cat palabras/part*
