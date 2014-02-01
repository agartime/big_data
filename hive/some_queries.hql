select count(*) from deptemp where ffin like "'999%";
select min(sueldo), max(sueldo), round(avg(sueldo),2) from salaries where ffin="'9999-01-01'"; 
select count(*) from deptemp  where fini <= "'1992-09-10'" and ffin >= "1992-09-10'";
select * from deptemp dt join departments t on dt.iddep = t.iddep;
select e.idemp from employees e join deptemp dt on e.idemp = dt.idemp and dt.ffin < "'2013-12-14'" limit 10;
select /*+ MAPJOIN(t) */ t.iddep from departments t JOIN deptemp dt on (t.iddep = dt.iddep);
select e.nombre, e.apellido from employees e join deptemp dt on e.idemp = dt.idemp and dt.ffin < "'2013-12-14'" limit 10;
select s.idemp, maxffin, nombre, apellido from ( select idemp, max(ffin) as maxffin from salaries group by idemp having max(ffin) <> "'9999-01-01'" limit 10) s JOIN employees_clone e ON s.idemp = s.idemp;

