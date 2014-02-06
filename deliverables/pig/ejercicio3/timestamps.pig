-- Antonio Gonzalez Artime - Pig deliverable - Exercise 3
-- some variables
%default ELEPHANT_BIRD_PATH 'lib/elephant-bird-pig-3.0.7.jar';
%default PIGGY_AMAZON_PATH 'lib/piggybank-0.3-amzn.jar';
%default ORDERS_FILE 'input/orders.data';
%default OUTPUT_FILE 'dateParse.out';
%default DATE_FORMAT 'yyyy-MM-dd';

-- elephant bird library register and JsonStringToMap define.
register '$ELEPHANT_BIRD_PATH';
define JsonStringToMap com.twitter.elephantbird.pig.piggybank.JsonStringToMap();

-- amazon library register and DateTime define.
register '$PIGGY_AMAZON_PATH';
define DATE_TIME org.apache.pig.piggybank.evaluation.datetime.DATE_TIME();
DEFINE FORMAT_DT org.apache.pig.piggybank.evaluation.datetime.FORMAT_DT();


-- input file loading
orders = load '$ORDERS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,datos:chararray);

-- json parsing using foreach
parsed = foreach orders generate id_tienda, id_usuario, JsonStringToMap(datos);
schemed = foreach parsed generate id_tienda, id_usuario, $2#'date' as date, $2#'initTs' as initTs, FORMAT_DT('$DATE_FORMAT',DATE_TIME($2#'initTs')) as parsedTs;
--formatted = foreach schemed generate id_tienda, id_usuario, date, initTs, FORMAT_DT('$DATE_FORMAT',time) as dt:chararray;
store schemed into '$OUTPUT_FILE';
