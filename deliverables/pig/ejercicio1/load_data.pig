-- Antonio Gonzalez Artime - Pig deliverable - Exercise 1
-- some variable
%default ORDERS_FILE 'input/orders.data';
%default SESSIONS_FILE 'input/sessions.data';
%default ORDERS_OUT 'output/ejercicio1a.out';
%default SESSIONS_OUT 'output/ejercicios1b.out';

-- trying to load orders and sessions using schema (id_tienda, id_usuario, datos)
orders = load '$ORDERS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,datos:chararray);
sessions = load '$SESSIONS_FILE' using PigStorage('@') as (id_tienda:chararray,id_usuario:chararray,datos:chararray);
store orders into '$ORDERS_OUT';
store sessions into '$SESSIONS_OUT';
