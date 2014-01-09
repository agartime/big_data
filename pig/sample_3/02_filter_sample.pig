plantilla = LOAD 'plantillas_futbol.csv' using PigStorage(';');
FILTER_1 = FILTER plantilla BY ($4== 15);
DUMP FILTER_1;
FILTER_2 = FILTER plantilla BY ($4==15) AND (NOT ($12 > 6));
DUMP FILTER_2;

