#!/bin/sh
HDP_CONTRIB=/usr/lib/hadoop-0.20-mapreduce/contrib/
HDP_STRM=${HDP_CONTRIB}/streaming
STREAMING_JAR=${HDP_STRM}/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar
set -x
hadoop jar ${STREAMING_JAR} \
  -input texto.txt \
  -output palabras \
  -file mapper.awk \
  -file reducer.awk \
  -mapper "gawk -f mapper.awk" \
  -reducer "gawk -f reducer.awk"

