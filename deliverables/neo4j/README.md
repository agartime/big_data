Deliverable - neo4j
===================

Questions:
==========

Pregunta 1

Escriba las consultas de Cypher necesarias para responder las siguientes preguntas:
¿Cuántos nodos de actores y películas separan, por el camino más corto del grafo, a Harvey Keitel de Lucy Liu?
¿Cuáles son los géneros de las películas en las que ha participado John Travolta? ¿En cuántas películas de cada género?

Pregunta 2

La siguiente consulta de Cypher

      MATCH ({name:'Henry Fonda'})-[:ACTS_IN]-(m) RETURN m.title

devuelve los títulos de las películas en las que ha actuado Henry Fonda. 

A pesar de ser solamente 19 resultados, esta consulta tarda varios segundos en ejecutarse.
¿Qué modificaciones sería necesario hacer en el esquema y la consulta para obtener los resultados en menos de un segundo?
Escriba las consultas en Cypher para lograr esta mejora de rendimiento.


Answers
=======

1.1. ¿Cuántos nodos de actores y películas separan, por el camino más corto del grafo, a Harvey Keitel de Lucy Liu?
[solucion](./question1.1.neo)

distance
4
Returned 1 row in 734 ms


1.2.1- ¿Cuáles son los géneros de las películas en las que ha participado John Travolta?
[solucion](./question1.2.1.neo)

SALIDA:
generos
Action
Animation
Comedy
Crime
Documentary
Drama
Returned 6 rows in 672 ms


1.2.2. ¿En cuántas películas de cada género?
[solucion](./question1.2.2.neo)

SALIDA:
m.genre	numberOfFilms
Action	14
Crime	3
Documentary	1
Drama	9
Comedy	8
Animation	1
Returned 6 rows in 614 ms


2. ¿Qué modificaciones sería necesario hacer en el esquema y la consulta para obtener los resultados en menos de un segundo?

Una de las mejoras posibles empezaría por definir el sentido de la relación. En este caso, puesto que Henry Fonda actúa en m, podríamos añadir dicho sentido. Así:
[solucion](./question2.1.neo)

De esta manera, la query tarda en ejecutarse una tercera parte. (501 ms)

También podemos etiquetar los nodos de Personas y los nodos de Películas. Podríamos hacerlo así puesto que la relación de ACTS_IN o DIRECTED siempre parte de un nodo de Actor hacia uno de Película.
[solucion](./question2.2.neo)

Con estas etiquetas, podríamos mejorar la búsqueda si además creamos unos índices que ataquen al nombre de los actores por un lado (lo que facilitará la búsqueda del actor); y al título de las películas.

[solucion](./question2.3.neo)

Si volvemos a ejecutar la query ahora:
[solucion](./question2.4.neo)

veremos que tarda unas diez veces menos que en el primer intento (128 ms). 
