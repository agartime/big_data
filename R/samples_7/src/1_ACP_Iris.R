
#EJEMPLO: Análisis de componentes principales para datos IRIS

# Cargar datos
data(iris)

#==============================================================================
# PASO 0: Conocer los datos, ver correlaciones

# En general
str(iris);  # data frame, 150 observaciones, 5 variables

# Media, cuartiles...
summary(iris) 

# Diagramas de dispersión
plot(iris[1:4],
     main = "Datos Iris",             # título
     pch = 4,                         # los puntos son cruces
     las = 1,                         # estilo de ejes (1: siempre horizontal)
     col = as.numeric(iris$Species)   # un color para cada tipo de flor
)
mtext("Tipos de lirios: negro-> setosa; rojo-> versicolor; azul-> virginica",
      side = 1,      # en qué lado del plot (1=abajo, 2=izda, 3=arriba, 4=dcha)
      line = 3.7,    # margen inferior
      cex=.8)        # expansión de los caracteres

# Correlación entre variables numéricas
cor(iris[1:4])

#==============================================================================
# PASO 1: Decidir si normalizar variables y hacerlo

# Examinar variabilidad de las variables numéricas
sapply(iris[1:4],var)

# Decidimos normalizar ((x-media)/desviación)
iris.stand <- as.data.frame(scale(iris[,1:4]))

# Podemos comprobar cómo han quedado las variables normalizadas
sapply(iris.stand,sd)  # obviamente la desviación es 1 para todas

hist(iris$Petal.Length,
     100,                                        # 100 intervalos
     main = "Histograma antes de normalizar",    # título
     xlab = "Petal Length")                      # etiqueta eje x

hist(iris.stand$Petal.Length,
     100,                                        # 100 intervalos
     main = "Histograma después de normalizar",  # título
     xlab = "Petal Length")                      # etiqueta eje x

#==============================================================================
# PASO 2: Cálculo de factores

# PCA sin variables normalizadas. No recomendado
irispca <- prcomp(iris[1:4], center=FALSE, scale=FALSE)

# PCA con variables normalizadas
# opción a) Usar las variables que ya habíamos normalizado
irispca_stand <- prcomp(iris.stand, center=FALSE, scale=FALSE)
# opción b) Normalizarlas dentro de la propia llamada a prcomp()
irispca_stand_b <- prcomp(iris[1:4], center=TRUE, scale=TRUE)
# el resultado es el mismo

# Veamos los resultados
# Factores de los componentes principales
irispca_stand$rotation
# Observaciones según nuevas variables, primeras filas
irispca_stand$x[1:5,]
# Centro de cada variable en la normalización (media)
irispca_stand$center
# Factor de escalado en la normalización (desviación típica)
irispca_stand$scale
# Desviación típica de cada nueva variable
irispca_stand$sdev

# Comprobamos que el módulo del vector de factores de cada componente es 1
sum((irispca_stand$rotation[,3])^2)   # Ejemplo: PC 3

# Diagrama de dispersión componente 1 vs componente 2
plot(irispca_stand$x[,1],      # PC 1 en eje x
     irispca_stand$x[,2],      # PC 2 en eje y
     main = "Diagrama de dispersión PC1 PC2", 
     xlab = "PC1",
     ylab = "PC2",
     col = as.numeric(iris$Species))
mtext("Tipos de lirios: negro-> setosa; rojo-> versicolor; azul-> virginica",
      side = 1,      # en qué lado del plot (1=abajo, 2=izda, 3=arriba, 4=dcha)
      line = 4,    # margen inferior
      cex=.8)        # expansión de los caracteres

#==============================================================================
# PASO 3: Selección de componentes principales

# Varianza total del problema recogida por las componentes principales
sum((irispca_stand$sdev)^2) # es 4, igual quel número de variables normalizadas 

# Conocer la varianza que recoge cada componente
plot(irispca_stand,
     main = "Varianza de cada componente")

# Gráfico útil para ver el codo
screeplot(irispca_stand,
          main = "Varianza de cada componente",
          type="lines",
          col=3)

# Proporción de varianza del total y proporción acumulada
summary(irispca_stand)

#==============================================================================
