add jar /usr/local/lib/hive-serdes-1.0-SNAPSHOT.jar;

create external table tweets (
  id bigint,
  created_at string,
  source string,
  lang string,
  retweet_count int,
  text string
) PARTITIONED BY (year int, month int, day int, hour int)
  ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe'
  LOCATION '/user/flume/tweets/';

ALTER TABLE tweets ADD IF NOT EXISTS PARTITION (year = '2013', month = '12', day = '12', hour = '14' ) LOCATION '2013/12/12/14';
-- Sourygna
CREATE EXTERNAL TABLE tweets (
 id BIGINT,
 created_at STRING,
 source STRING,
 favorited BOOLEAN,
 retweeted_status STRUCT<
   text:STRING,
   user:STRUCT<screen_name:STRING,name:STRING>,
   retweet_count:INT>,
 entities STRUCT<
   urls:ARRAY<STRUCT<expanded_url:STRING>>,
   user_mentions:ARRAY<STRUCT<screen_name:STRING,name:STRING>>,
 hashtags:ARRAY<STRUCT<text:STRING>>>,
 text STRING,
 user STRUCT<
   screen_name:STRING,
   name:STRING,
   friends_count:INT,
   followers_count:INT,
   statuses_count:INT,
   verified:BOOLEAN,
   utc_offset:INT,
   time_zone:STRING>,
 in_reply_to_screen_name STRING
)
PARTITIONED BY (year INT, month INT, day INT, hour INT)
ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe'
LOCATION '/user/flume/tweets';

