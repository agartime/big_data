x <- rnorm(100)
y <- rnorm(100)
x <- sort(x)
y <- sort(y)
x[1:10]
y[1:10]
plot(x,y)
source("eda.shape.r")
eda.shape
# Llamamos a la funcion y vemos el plot que nos genera.
eda.shape(x)
# Ahi podemos ver:
# Histograma. Cierta simetria, no centrado aunque parece que hay mas valores por un lado que por otr...
# ... Todo esto depende de nuestros datos aleatorios.
eda.shape(y)
# Veamos la correlacion que tienen entre ellas
cor(x,y)
# Correlacion fuerte y positiva.
#
# Si ahora generamos una variable z en base a las otras 2.
z <- y*28 - 10
cor(x,z)
# Es exactamente igual. Esto es porque este coeficiente no cambia en las transformaciones (dicho así grosso modod)
#
# Creamos un modelo lineal mod1
mod1 <- lm(y ~ x)
mod1
# Si lo hacemos con z
mod1 <- lm(z ~ x)
mod
mod1
# El 26 es casi 28 veces (la a queutilizamos al crear z). Eso indica que habria que mover o girar la recta.
plot(x,y)
par(mfrow(c(1,1))
mod1 <- lm(y ~ x)
par(mfrow=c(1,1))
mod1 <- lm(y ~ x)
abline(mod1)
mod2 <- lm(z ~ x)
plot(x,z)
plot(x,y)
points(x, z, col=2)
plot(x,z)
points(x, y, col=2)
abline(mod1, col=3) #Recta que ajusta los puntos x y
abline(mod2, col=4) # Recta que ajustaba los puntos x z
# Dibujamos uno de los modelos
plot(mod1)
xmod <- x[-c(1,100)] # Le quitamos los puntos 1 y 100
ymod <- y[-c(1,100)] # Idem con y. A ver ahora la correlacion
cor(xmod,ymod)
# Hemos mejorado la correlacion.
#
mod1mod <- lm(ymod ~ xmod)
# Este es el modelo 1 modificado
# A ver que tal la pendiente (xmod) ahora
mod1mod
# Ordenada en el origen es 0 y pendiente 1 implica que son iguales. Cuanto mas se aproxime mejor...
plot(mod1mod)
q()
