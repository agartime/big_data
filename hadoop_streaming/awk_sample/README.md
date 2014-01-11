Hadoop Streaming Wordcount Sample - AWK
=======================================

* texto.txt - Input file
* streaming.sh - Script that launches the MR Job
* reducer.awk - WC Reducer in awk
* mapper.awk - WC Mapper in awk
* 001_put_in_hdfs.sh - Automated script for copying input files to HDFS.

Usage:
    
        ./001_put_in_hdfs.sh
        ./streaming.sh

