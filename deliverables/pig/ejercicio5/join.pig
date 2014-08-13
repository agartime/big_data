-- Antonio Gonzalez Artime - Pig deliverable - Exercise 5
-- some variables
%default ELEPHANT_BIRD_PATH 'lib/elephant-bird-pig-3.0.7.jar';
%default UDF_FILE 'formatter.py';
%default SESSIONS_FILE 'input/sessions.data';
%default OUTPUT_FILE 'join.out';

-- previous configuration
set io.sort.mb 10;

-- elephant bird library register and JsonStringToMap define.
register '$ELEPHANT_BIRD_PATH';
define JsonStringToMap com.twitter.elephantbird.pig.piggybank.JsonStringToMap();

-- registering custom UDF for output format
register '$UDF_FILE' using jython as formatter;

-- input file loading
orders = load '$SESSIONS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,product_visits:chararray);

-- json parsing using foreach
parsed = foreach orders generate id_tienda, id_usuario, JsonStringToMap(product_visits);
schemed = foreach parsed generate id_tienda, id_usuario, $2#'actionName' as action_name;

-- filter by actionName type
page_navigations = filter schemed by (action_name == 'pageNav');
product_navigations = filter schemed by (action_name == 'productNav');

-- group by id_tienda
grouped_page_navigations = group page_navigations by id_tienda;
grouped_product_navigations = group product_navigations by id_tienda;

count_page_navigations = foreach grouped_page_navigations generate group, COUNT(page_navigations) as page_count;
count_product_navigations = foreach grouped_product_navigations generate group, COUNT(product_navigations) as product_count;

-- join + group
joined = join count_page_navigations by group, count_product_navigations by group;
grouped = foreach joined generate count_page_navigations::group as id_tienda, count_page_navigations::page_count as page_count, count_product_navigations::product_count as product_count;

-- custom format
formatted = foreach grouped generate formatter.custom_output(id_tienda,page_count,product_count);

--output
store formatted into '$OUTPUT_FILE';
