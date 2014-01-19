source(eda.shape.r)
source("eda.shape.r")
library(rpart)
eda.shape(car.test.frame$Mileage)
# Vamos a intentar una transformacion para verlos mas normales. Probemos con el inverso.
eda.shape(1/car.test.frame$Mileage) # seria como en lugar de kilmetros/hora, hora/kilometro
# Vamos a intentar una transformacion para verlos mas normales. Probemos con el logaritmo.
eda.shape(log10(car.test.frame$Mileage)) # seria como en lugar de kilmetros/hora, hora/kilometro
# Parece peor que con el inverso....
# Nos quedamos con el inverso.
# Veamos el peso de los coches.
eda.shape(car.test.frame$Weight)
# Parece una distribucion bastante normal, podriamos dejarlo como está.
# Podriamos probar a ajustar un modelo entre las variables originales.
# Veamos su correlacion.
cor(car.test.frame$Mileage, car.test.frame$Weight)
# Correlacion inversa de 0.84... No está tan mal. Pintemoslo para ver si tiene una pinta lineal. Implica que a mayor peso, menos millas por galón.
plot(y=car.test.frame$Mileage ~ x=car.test.frame$Weight)
par(mfrow=c(1,1))
plot(y=car.test.frame$Mileage, x=car.test.frame$Weight)
# Veamoslo con la variable transformada
plot(y=1/car.test.frame$Mileage, x=car.test.frame$Weight)
# Ahora si que parece algo un poco mas lineal que antes. veamos los valores de correlacion.
cor(1/car.test.frame$Mileage, car.test.frame$Weight)
# Ahora es directa y un poco más alto (mejor)
# Además se ve mejor en el scatterplot.
# Lo pintamos.
mod1 <- lm(1/car.test.frame$Mileage ~ car.test.frame.Weight)
abline(mod1)
plot(mod1)
q()
