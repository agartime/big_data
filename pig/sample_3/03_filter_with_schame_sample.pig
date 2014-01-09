plantilla = LOAD 'plantillas_futbol.csv' USING PigStorage(';') AS
(TEMPORADA:chararray,LIGA:bytearray,EQUIPO:chararray,JUGADOR:chararray,P_JUGADOS:bytearray,
P_COMPLETOS:bytearray,P_TITULAR:bytearray,P_SUPLENTE:bytearray,MINUTOS:bytearray,LESIONES:bytearray,TARJETAS:bytearray,EXPULSIONES:bytearray,
GOLES:int,PENALTIES_FALLADOS:bytearray);

FILTER_3 = FILTER plantilla BY (P_JUGADOS==15);
DUMP FILTER_3;

FILTER_4 = FILTER plantilla BY (P_JUGADOS==15) AND (GOLES > 6);
DUMP FILTER_4;
