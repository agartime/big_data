-- Load Data bytearrayo Pig Without Scheme
plantilla = LOAD 'plantillas_futbol.csv' USING PigStorage(';') AS
(TEMPORADA:chararray,LIGA:bytearray,EQUIPO:chararray,JUGADOR:chararray,P_JUGADOS:bytearray,
P_COMPLETOS:bytearray,P_TITULAR:bytearray,P_SUPLENTE:bytearray,MINUTOS:bytearray,LESIONES:bytearray,TARJETAS:bytearray,EXPULSIONES:bytearray,
GOLES:int,PENALTIES_FALLADOS:bytearray);

-- Describe fields
DESCRIBE plantilla;
--Print screen
DUMP plantilla;


