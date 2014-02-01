create external table employees (
       idemp string,
       year string,
       month string,
       day string,
       name string,
       lastname string,
       gender string,
       finc_year string,
       finc_month string,
       finc_day string) ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.RegexSerDe'
       WITH SERDEPROPERTIES("input.regex" = "(\\d{5})(\\d{4})(\\d{2})(\\d{2})([A-Z]\\w+)([A-Z]\\w+)([A-Z])(\\d{4})(\\d{2})(\\d{2})")
       LOCATION '/user/cloudera/external_employee';

