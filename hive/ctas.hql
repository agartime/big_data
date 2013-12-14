create table employees_clone 
as
select idemp,
       concat("'",year,"-",month,"-",day,"'") fnac,
       name nombre, 
       lastname apellido,
       gender sexo,
       concat("'",finc_year,"-",finc_month,"-",finc_day,"'") finc 
   from employees;
