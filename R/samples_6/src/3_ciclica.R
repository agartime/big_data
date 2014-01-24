
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
