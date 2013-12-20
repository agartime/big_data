plantilla = LOAD 'plantillas_futbol.csv' USING PigStorage(';') AS
(TEMPORADA:chararray,LIGA:bytearray,EQUIPO:chararray,JUGADOR:chararray,P_JUGADOS:bytearray,
P_COMPLETOS:bytearray,P_TITULAR:bytearray,P_SUPLENTE:bytearray,MINUTOS:bytearray,LESIONES:bytearray,TARJETAS:bytearray,EXPULSIONES:bytearray,
GOLES:int,PENALTIES_FALLADOS:bytearray);

--without schema
GRP_2 = GROUP plantilla BY EQUIPO;
FOREACH_1= FOREACH GRP_2 GENERATE group as equipo, COUNT(plantilla) as counter;
DUMP FOREACH_1;

--with schema
--FOREACH_2= FOREACH GRP_2 GENERATE group as group:chararray, COUNT(plantilla) AS counter:long;
--DUMP FOREACH_2;
