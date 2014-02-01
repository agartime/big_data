Twitter->Flume->Hive 
====================

Flume, Twitter + Hive integration example.

* install_flume_twitter.sh - Installation script. Clones cloudera repo and configures flume.
* flume.conf - Flume configuration. Put your keys in the required fields and think in some nice keywords.
* compile_serde.sh - Serde compile.
* create_external_table.sh - Creates a table for reading tweets from HDFS. Partitioned by date and hour. Uses JSONSerde.

