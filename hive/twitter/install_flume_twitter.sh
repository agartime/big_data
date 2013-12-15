#!/bin/sh
GIT_PATH=/home/cloudera/twitter
FLUME_NG_LIBPATH=/usr/lib/flume-nf/lib
FLUME_CONF_PATH=/etc/flume-ng/conf/

mkdir ${GIT_PATH}
cd ${GIT_PATH}
git clone https://github.com/cloudera/cdh-twitter-example
cd cdh-twitter-example/flume-sources
mvn package
sudo cp target/flume-sources-1.0-SNAPSHOT.jar /usr/lib/flume-ng/lib/

cd ${FLUME_NG_LIBPATH}
sudo mv search-contrib-0.9.3-cdh4.3.0-SNAPSHOT-jar-with-dependencies.jar search-contrib-0.9.3-cdh4.3.0-SNAPSHOT-jar-with-dependencies.jar-old

cd ${GIT_PATH}/flume-sources/
sudo cp flume.conf ${FLUME_CONF_PATH}
