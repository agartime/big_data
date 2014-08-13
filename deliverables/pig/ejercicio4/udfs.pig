-- Antonio Gonzalez Artime - Pig deliverable - Exercise 3
-- some variables
%default ELEPHANT_BIRD_PATH 'lib/elephant-bird-pig-3.0.7.jar';
%default UDF_FILE 'counter.py';
%default ORDERS_FILE 'input/orders.data';
%default OUTPUT_FILE 'countProducts.out';

-- elephant bird library register and JsonStringToMap define.
register '$ELEPHANT_BIRD_PATH';
define JsonStringToMap com.twitter.elephantbird.pig.piggybank.JsonStringToMap();

-- registering our custom udf counter written in python.
register '$UDF_FILE' using jython as counter;

-- input file loading
orders = load '$ORDERS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,datos:chararray);

-- json parsing using foreach
parsed = foreach orders generate id_tienda, id_usuario, JsonStringToMap(datos);
schemed = foreach parsed generate id_tienda, id_usuario, counter.count_bag_items($2#'products') as product_count;
store schemed into '$OUTPUT_FILE';
