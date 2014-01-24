
# EJEMPLO: Tratamiento series temporales con R

# Cargar datos
ganancias <- read.table("./data/ganancias.csv", header=F, sep=",", quote="",
                        col.names=c("trimestre", "ganancias"), 
                        colClasses=c("numeric","numeric"))

# Tratarlo como serie temporal
ganancias_ts <- ts(ganancias$ganancias, start=c(1,1), frequency=4)

# Ver valores serie
ganancias_ts

# Ver valor posición 5
ganancias_ts[5]

# ¿Estamos seguros de que es una serie temporal?
is.ts(ganancias_ts)
is.ts(ganancias)

# Sacar el timeline (fechas de las observaciones)
time(ganancias_ts)

# Fecha de inicio de la serie
start(ganancias_ts)

# Fecha de fin de la serie
end(ganancias_ts)

# Número de observaciones por año
frequency(ganancias_ts)

# Diferencia de tiempos entre dos observaciones (inversa de la frecuencia)
deltat(ganancias_ts)

# Posición de cada observación en el ciclo anual
cycle(ganancias_ts)

# Posición de la observación 18 en el ciclo anual
cycle(ganancias_ts)[18]

# Subconjunto de la serie (del año 3 trimestre 4 al año 6 trimestre 3)
window(ganancias_ts, start=c(3, 4), end=c(6, 3))
