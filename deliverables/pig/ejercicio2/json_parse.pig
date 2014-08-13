-- Antonio Gonzalez Artime - Pig deliverable - Exercise 2
-- some variables
%default ELEPHANT_BIRD_PATH 'lib/elephant-bird-pig-3.0.7.jar';
%default ORDERS_FILE 'input/orders.data';
%default OUTPUT_FILE 'amountByDate.out';

-- elephant bird library register and JsonStringToMap define
register '$ELEPHANT_BIRD_PATH';
define JsonStringToMap com.twitter.elephantbird.pig.piggybank.JsonStringToMap();

-- input file loading
orders = load '$ORDERS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,datos:chararray);

-- json parsing using foreach
parsed = foreach orders generate id_tienda, id_usuario, JsonStringToMap(datos);
schemed = foreach parsed generate id_tienda, id_usuario, $2#'date' as date, $2#'amount' as amount;
store schemed into '$OUTPUT_FILE';
