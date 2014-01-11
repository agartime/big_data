#!/bin/sh
DIR_NAME=streaming
FILE_NAME=texto.txt
hdfs dfs -mkdir $DIR_NAME
hdfs dfs -put $FILE_NAME 
