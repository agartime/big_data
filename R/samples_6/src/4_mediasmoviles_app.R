
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
