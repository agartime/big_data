#ganancias.csv contiene 8 años de información de ganancias de una empresa. Tenemos 4 puntos por año.
#Cargamos la tabla en un dataframe:
ganancias <- read.table("./data/ganancias.csv", header=F, sep=",", quote="",
                        col.names=c("trimestre", "ganancias"), 
                        colClasses=c("numeric","numeric"))
#Ahora lo tratamos como una serie temporal
#Le indicamos el vector de datos que estamos cargando, y parámetros relacionados con los times de nuestros datos.
#Ej: Empieza en el punto 1,1 y cada año estará dividido en 4 partes (los cuartos). De ahí el frequency=4.
# El punto 1,3 sería el tercer trimestre; el 2,4; el 4º trimestre del 2º año, etc.
# Lo visualizamos
ganancias_ts <- ts(ganancias$ganancias, start=c(1,1), frequency=4)
ganancias_ts
#Obtenemos el 5º valor:
ganancias_ts[5]
#Comprobar si es una serie temporal o no:
is.ts(ganancias_ts)
# Sacar el time interno que R considera para cada observación.
# timeline (fecha de las observaciones)
time(ganancias_ts)
#Ver la fecha de inicio de una serie.
start(ganancias_ts)
#El fin
end(ganancias_ts)

#La frecuencia:
frequency(ganancias_ts)

# Diferencia de tiempos entre dos observaciones (inversa de la frecuencia)
deltat(ganancias_ts)

# Posición de cada observación en el ciclo anual
cycle(ganancias_ts)

# Posición de la observación 18 en el ciclo anual
cycle(ganancias_ts)[18]

#Con window obtenemos una prte de la serie temporal.
# Subconjunto de la serie (del año 3 trimestre 4 al año 6 trimestre 3)
window(ganancias_ts, start=c(3, 4), end=c(6, 3))

# EJEMPLO: Componente de tendencia

# Cargar datos
creditos <- read.table("./data/creditos.csv", header=F, sep=",", quote="",
                       col.names=c("agno", "credito"), 
                       colClasses=c("numeric","numeric"))

# Generar gráfico puntos y línea
plot(creditos$agno, creditos$credito, type="b")

# Generar gráfico más bonito
plot(creditos$agno, creditos$credito, type="b",
     main = "Créditos: Ejemplo de tendencia", # título
     xlab = "Año",                            # etiqueta eje x
     ylab = "Crédito",                        # etiqueta eje y
     ylim = c(100,350),                       # límites del eje y
     xaxp = c(1,11,11-1),                     # min, max y num de ticks eje x
     col = "red",                             # color del plot
     las = 1                                  # estilo de ejes (1: siempre horizontal)
)

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

# EJEMPLO: Componente cíclica

# Cargar datos
pinkham <- read.table("./data/pinkham.csv", header=F, sep="\t", quote="",
                      col.names=c("agno", "ventas"), 
                      colClasses=c("numeric","numeric"))

# Generar gráfico línea
plot(pinkham$agno, pinkham$ventas, type="l")

# Generar gráfico más bonito
plot(pinkham$agno, pinkham$ventas, type="l",
     main = "Ventas de Lydia E. Pinkham: Ejemplo de cíclica",   # título
     xlab = "Año",                         # etiqueta eje x
     ylab = "Ventas (miles de dólares)",   # etiqueta eje y
     xlim = c(1931,1960),                  # límites del eje x
     ylim = c(1100,2700),                  # límites del eje y
     xaxp = c(1930,1960,10),               # min, max y num de ticks eje x
     yaxp = c(1100,2700,4),                # min, max y num de ticks eje x
     col = "red",                          # color del plot
     lwd = 2,                              # grosor línea (default 1)
     las = 1                               # estilo de ejes (1: siempre horizontal)
)

# Tratarlo como serie temporal
pinkham_ts <- ts(pinkham$ventas, start=c(1931), end=c(1960))

plot(pinkham_ts,
     main = "Ventas de Lydia E. Pinkham: Ejemplo de cíclica",   # título
     xlab = "Año",                         # etiqueta eje x
     ylab = "Ventas (miles de dólares)",   # etiqueta eje y
     xlim = c(1931,1960),                  # límites del eje x
     ylim = c(1100,2700),                  # límites del eje y
     xaxp = c(1930,1960,10),               # min, max y num de ticks eje x
     yaxp = c(1100,2700,4),                # min, max y num de ticks eje x
     col = "red",                          # color del plot
     lwd = 2,                              # grosor línea (default 1)
     las = 1                               # estilo de ejes (1: siempre horizontal)
)


# EJEMPLO: Medias móviles centradas simples de (2m+1) puntos
#install.packages("zoo") #Nos instala el paquete si no lo tenemos
#install.packages("forecast") # Idem

library(zoo)
library(forecast)

# Cargar datos
pinkham <- read.table("./data/pinkham.csv", header=F, sep="\t", quote="",
                      col.names=c("agno", "ventas"), 
                      colClasses=c("numeric","numeric"))

# Tratarlo como serie temporal
pinkham_ts <- ts(pinkham$ventas, start=c(1931), end=c(1960))

#==============================================================================
# OPCIÓN 1: Calcular manualmente medias móviles centradas simples de 5 puntos
pinkham_suave <- c()   # vector vacío
m <- 2
for (i in 1+m:length(pinkham_ts)-m) {
  pinkham_suave[i] <- (pinkham_ts[i-2] + pinkham_ts[i-1] + pinkham_ts[i] + 
                         pinkham_ts[i+1] + pinkham_ts[i+2]) / (2*m+1)
}
# Tratar la curva suavizada como serie temporal
pinkham_suave_ts <- ts(pinkham_suave, start=c(1931), end=c(1960))
rm(pinkham_suave)

# OPCIÓN 2: Calcular con función
# opción 2a) filter (librería stats)
pinkham_suave_a_ts <- filter(pinkham_ts, 
                             rep(1/5,5),     # peso de cada valor (todos 0.2)
                             method = "convolution", 
                             sides = 2)      # vecinos por los dos lados
# opción 2b) rollmean (librería zoo)
pinkham_suave_b_ts <- rollmean(pinkham_ts, 
                               5,                 # ancho de ventana
                               fill = NA,         # rellenar los extremos con NA
                               align = "center")  # media centrada

# opción 2c) ma (librería forecast)
pinkham_suave_c_ts <- ma(pinkham_ts,
                         order = 5,               # ancho de ventana
                         centre = TRUE)           # media centrada

#==============================================================================
# Mostrar curva original junto con curva suavizada
plot(pinkham_ts,
     main = "Pinkham: Medias móviles centradas simples de 5 puntos",   # título
     xlab = "Año",                         # etiqueta eje x
     ylab = "Ventas (miles de dólares)",   # etiqueta eje y
     xlim = c(1931,1960),                  # límites del eje x
     ylim = c(1100,2700),                  # límites del eje y
     xaxp = c(1930,1960,10),               # min, max y num de ticks eje x
     yaxp = c(1100,2700,4),                # min, max y num de ticks eje x
     col = "red",                          # color del plot
     lwd = 2,                              # grosor línea (default 1)
     las = 1                               # estilo de ejes (1: siempre horizontal)
)
par(new = TRUE)
plot(pinkham_suave_ts, 
     axes = FALSE, 
     xlab = "", 
     ylab = "",
     xlim = c(1931,1960),                  # importante: mismos límites del eje x
     ylim = c(1100,2700),                  # importante: mismos límites del eje y
     lwd = 2,                              # grosor línea (default 1)
     lty = 2                               # linea con guiones
)
legend("topright",c("serie original", "serie suavizada"),
       col=c("red","black"),lty=c(1,2))

###### medias_moviles_app.R ############
## Medias móviles centradas de 4 puntos.



# EJEMPLO: Aplicación de medias móviles: eliminar componente estacional

library(zoo)
library(forecast)

# Cargar datos
ganancias <- read.table("./data/ganancias.csv", header=F, sep=",", quote="",
                        col.names=c("trimestre", "ganancias"), 
                        colClasses=c("numeric","numeric"))

# Tratarlo como serie temporal
ganancias_ts <- ts(ganancias$ganancias, start=c(1,1), end=c(8,4), frequency=4)

#==============================================================================
# OPCIÓN 1: Calcular manualmente medias móviles centradas simples de 4 puntos
ganancias_mm_paso1 <- c()   # vector vacío
ganancias_mm_paso2 <- c()   # vector vacío
puntos <- 4
# medias móviles de 4 puntos (no centradas) (los índices x.5 se sustituyen por x)
for (i in 2:(length(ganancias_ts)-2)) {
  ganancias_mm_paso1[i] <- (ganancias_ts[i-1] + ganancias_ts[i] + ganancias_ts[i+1] + 
                              ganancias_ts[i+2]) / puntos
}
# medias móviles centradas de 4 puntos
for (i in (puntos-1):length(ganancias_mm_paso1)) {
  ganancias_mm_paso2[i] <- (ganancias_mm_paso1[i-1] + ganancias_mm_paso1[i]) / 2
}
# Tratar la curva suavizada como serie temporal
ganancias_mm_paso2_ts <- ts(ganancias_mm_paso2, start=c(1,1), frequency=4)
rm(ganancias_mm_paso1)
rm(ganancias_mm_paso2)

# OPCIÓN 2: Calcular con función
# opción 2a) filter (librería stats)
ganancias_mm_a_paso1_ts <- filter(ganancias_ts, 
                                  rep(1/4,4),     # peso de cada valor (todos 0.25)
                                  method = "convolution", 
                                  sides = 2)      # vecinos por los dos lados
ganancias_mm_a_paso2_ts <- filter(ganancias_mm_a_paso1_ts, 
                                  rep(1/2,2),     # peso de cada valor (todos 0.5)
                                  method = "convolution", 
                                  sides = 1)      # vecinos sólo del pasado
# opción 2b) rollmean/rollapply (librería zoo)
ganancias_mm_b_paso1_ts <- rollmean(ganancias_ts, 
                                    4,                 # ancho de ventana
                                    align = "center")  # media centrada
ganancias_mm_b_paso2_ts <- rollmean(ganancias_mm_b_paso1_ts, #rollmean no admite recibir NAs
                                    2,                 # ancho de ventana
                                    fill = NA,         # rellenar los extremos con NA
                                    align = "right")    # vecinos sólo del pasado
# también se podría haber usado (admite recibir NAs):
# rollapply(ganancias_mm_b_paso1_ts, 2, mean, align="right")
# opción 2c) ma (librería forecast) Sólo un paso!
ganancias_mm_c_ts <- ma(ganancias_ts,
                        order = 4,               # ancho de ventana
                        centre = TRUE)           # media centrada

#==============================================================================
# Mostrar curva original junto con curva suavizada
plot(ganancias_ts,
     main = "Ganancias: Eliminación de componente estacional", # título
     xlab = "Año",                                  # etiqueta eje x
     ylab = "Ganancias",                            # etiqueta eje y
     xlim = c(1,9),                                 # límites del eje x
     ylim = c(0,3),                                 # límites del eje y
     xaxp = c(1,8,8-1),                             # min, max y num de ticks eje x
     col = "red",                                   # color del plot
     lwd = 2,                                       # grosor línea (default 1)
     las = 1                                        # estilo de ejes (1: siempre horizontal)
)
# añadir líneas grises verticales en primer trimestre de cada año
abline(v=seq(1,8,1), col="grey")
par(new = TRUE)
plot(ganancias_mm_paso2_ts, 
     axes = FALSE, 
     xlab = "", 
     ylab = "",
     xlim = c(1,9),                        # importante: mismos límites del eje x
     ylim = c(0,3),                        # importante: mismos límites del eje y
     lwd = 2,                              # grosor línea (default 1)
     lty = 2                               # linea con guiones
)
legend("topleft",c("serie original", "serie no estacional"),
       col=c("red","black"),lty=c(1,2))


# EJEMPLO: Alisado exponencial simple

library(forecast)

# Cargar datos
pinkham <- read.table("./data/pinkham.csv", header=F, sep="\t", quote="",
                      col.names=c("agno", "ventas"), 
                      colClasses=c("numeric","numeric"))

# Tratarlo como serie temporal
pinkham_ts <- ts(pinkham$ventas, start=c(1931), end=c(1960))

#==============================================================================
# OPCIÓN 1: Calcular manualmente
pinkham_suave <- c()   # vector vacío
alpha = 0.45

alisado <- function(param1,param2) alpha*param1+(1-alpha)*param2
pinkham_suave[1] = pinkham$ventas[1]
for ( i in 2:length(pinkham_ts))
  pinkham_suave[i] = alisado(pinkham_suave[i-1],pinkham$ventas[i])

pinkham_suave[30]
#pinkham_suave]30 ---> 1342.652

# Mi intento recursivo:
#valor_t <- function(serie, t, alpha) {
#  if (equal(t,0)) {
#    return(serie[t])
#  } else {
#    return(valor_t(serie, t-1, alpha) + serie[t]*alpha)  
#  }
#}
#alpha <- 0.5
#prueba <- valor_t(c(1,2,3,4,5),3,alpha)

### RELLENAR POR ALUMNO#####################################

# OPCIÓN 2: Calcular con función
# ses (librería forecast)
ses(pinkham_ts, h=5, alpha=0.4)

#==============================================================================
# Mostrar curva original junto con curva suavizada y predicción
plot(pinkham_ts,
     main = "Pinkham: Medias móviles centradas simples de 5 puntos",   # título
     xlab = "Año",                         # etiqueta eje x
     ylab = "Ventas (miles de dólares)",   # etiqueta eje y
     xlim = c(1931,1960),                  # límites del eje x
     ylim = c(1100,2700),                  # límites del eje y
     xaxp = c(1930,1960,10),               # min, max y num de ticks eje x
     yaxp = c(1100,2700,4),                # min, max y num de ticks eje x
     col = "red",                          # color del plot
     lwd = 2,                              # grosor línea (default 1)
     las = 1                               # estilo de ejes (1: siempre horizontal)
)
par(new = TRUE)
plot(pinkham_suave_ts, 
     axes = FALSE, 
     xlab = "", 
     ylab = "",
     xlim = c(1931,1960),                  # importante: mismos límites del eje x
     ylim = c(1100,2700),                  # importante: mismos límites del eje y
     lwd = 2,                              # grosor línea (default 1)
     lty = 2                               # linea con guiones
)
legend("topright",c("serie original", "serie suavizada y predicción"),
       col=c("red","black"),lty=c(1,2))




