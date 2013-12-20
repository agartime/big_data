plantilla = LOAD 'plantillas_futbol.csv' using PigStorage(';');
GRP = GROUP plantilla BY $2;
GRP1 = LIMIT GRP 1;
DUMP GRP1;

