
# EJEMPLO: Componente de estacionalidad

# Cargar datos
ganancias <- read.table("./data/ganancias.csv", header=F, sep=",", quote="",
                       col.names=c("trimestre", "ganancias"), 
                       colClasses=c("numeric","numeric"))

# Generar gráfico línea
plot(ganancias$trimestre, ganancias$ganancia, type="l")

# Generar gráfico más bonito
plot(ganancias$trimestre, ganancias$ganancia, type="l",
     main = "Ganancias: Ejemplo de estacionalidad", # título
     xlab = "Trimestre",                            # etiqueta eje x
     ylab = "Ganancias",                            # etiqueta eje y
     ylim = c(0,3),                                 # límites del eje y
     xaxp = c(1,32,31),                             # min, max y num de ticks eje x
     col = "red",                                   # color del plot
     lwd = 2,                                       # grosor línea (default 1)
     las = 1                                        # estilo de ejes (1: siempre horizontal)
     )
# añadir líneas grises verticales en primer trimestre de cada año
abline(v=seq(1,32,4), col="grey")

# Tratarlo como serie temporal
ganancias_ts <- ts(ganancias$ganancias, start=c(1,1), end=c(8,4), frequency=4)

plot(ganancias_ts,
     main = "Ganancias: Ejemplo de estacionalidad", # título
     xlab = "Año",                            # etiqueta eje x
     ylab = "Ganancias",                            # etiqueta eje y
     ylim = c(0,3),                                 # límites del eje y
     xaxp = c(1,8,8-1),                             # min, max y num de ticks eje x
     col = "red",                                   # color del plot
     lwd = 2,                                       # grosor línea (default 1)
     las = 1                                        # estilo de ejes (1: siempre horizontal)
)
# añadir líneas grises verticales en primer trimestre de cada año
abline(v=seq(1,8,1), col="grey")
