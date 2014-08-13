#!/bin/sh
ORDERS_FILE="./input/orders.data"
DESTINY_FOLDER="output"
DESTINY_ORDERS_FILE=$DESTINY_FOLDER"/orders.data"
PIG_SCRIPT_FILE="json_parse.pig"

if [ "$1" != "local" -a "$1" != "hdfs" ]
then
       echo "usage: ./ejercicio2.sh <mode>"
       echo "where mode is \"hdfs\" or \"local\""
       echo "eg. ./ejercicio2.sh hdfs"
       echo "NOTE: hdfs mode may not work if additional json libraries are not correctly set."
       exit -1
fi

if [ "$1" == "hdfs" ]
then
	echo "Checking if hdfs command is installed."
	which hdfs > /dev/null
	if [ $? -ne 0 ]
	then
		echo "Sorry. You need hdfs command installed in your system. Aborting."
		exit -1
	else
		echo "hdfs is properly configured. Continuing..."
		echo "creating destiny folder in HDFS: "$DESTINY_FOLDER
		hdfs dfs -mkdir $DESTINY_FOLDER
		echo "Checking sample files existance..."
		if [ -f $ORDERS_FILE ]
		then
			echo "Input file: $ORDERS_FILE exists. Putting it into HDFS."
			hdfs dfs -put $ORDERS_FILE $DESTINY_ORDERS_FILE
		else
			echo "Input file doesn't exists. Aborting..."
			exit -3
		fi
		echo "Done..."
	fi
fi

echo "Checking if pig is installed..."
which pig > /dev/null
if [ $? -ne 0 ]
then
	echo "Sorry. Pig is not installed. Aborting."
	exit -1
else
	if [ "$1" == "hdfs" ]
	then
		echo "Executing pig using HDFS..."
		pig -f $PIG_SCRIPT_FILE
	else
		echo "Executing pig in local mode..."
		pig -x local -f $PIG_SCRIPT_FILE
	fi
fi
