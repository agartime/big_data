
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
