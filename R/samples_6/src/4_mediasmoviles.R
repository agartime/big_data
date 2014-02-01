
# EJEMPLO: Medias móviles centradas simples de (2m+1) puntos

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
