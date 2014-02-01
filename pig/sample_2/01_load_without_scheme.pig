-- Load Data into Pig Without Scheme
plantilla = LOAD 'plantillas_futbol.csv' using PigStorage(';') AS 
(TEMPORADA,LIGA,EQUIPO,JUGADOR,P_JUGADOS,P_COMPLETOS,P_TITULAR,
P_SUPLENTE,MINUTOS,LESIONES,TARJETAS,EXPULSIONES,GOLES,PENALTIES_FALLADOS);

-- Describe fields
DESCRIBE plantilla;
--Print screen
DUMP plantilla;


