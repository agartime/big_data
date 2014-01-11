Hadoop Streaming Wordcount Sample - Ruby
=======================================

* texto.txt - Input file
* streaming_rb.sh - Script that launches the MR Job
* reducer.rb - WC Reducer in Ruby
* mapper.rb - WC Mapper in Ruby
* 001_put_in_hdfs.sh - Automated script for copying input files to HDFS.

Usage:
    
        ./001_put_in_hdfs.sh
        ./streaming_rb.sh

