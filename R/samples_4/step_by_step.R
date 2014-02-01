help(dnorm)
plot(seq(-4,4,0.1)
)
myx <- seq(-4,4,0.1)
myx
plot(x=myx, y=dnorm(myx,mean=3))
plot(x=myx, y=dnorm(myx,mean=0), type="l")
# Esa es la densidad
#
# Ahora la probabilidad acumulada. Ver porcentaje de datos menores que 1.
pnorm(1)
# El 84% de los datos son menores que 1
pnorm(1, mean=1)
#qnorm entrar por eje y y ver cual es el eje X
qnorm(1, mean=1)
qnorm(seq(0,1,0.1)
)
plot(x=myx, y=norm(myx,mean=0), type="l")
plot(x=myx, y=pnorm(myx,mean=0), type="l")
#
# Media 10 y desviacion tipica 2. A sabemos que es mayor que el 90% de los miembros de la poblacion. Encontrar A.
qnorm(0.9, mean=10, sd=2) #Percentil 90
# Ahora queremos saber. Entre a y b (a=90%) se encuentra el 60% de la población. (numero por debajo de a que entre el 90 y ese valor esta el 60).
# 90 - 60 = 30 (valor por el cual por debajo de a esta el 30)
qnorm(0.3, mean=10, sd=2)
#
# C es menor que el 80% de la población. 
# Si es menor que el 80%, sera mayor que el 20%
qnorm(0.2, mean=10, sd=2)
#
# Media 6 y desviacion tipica 2. Si elegimos un individuo al azar, cual es la probabilidad de que se encuentre en el intervalo 6,7.
pnorm(6, mean=6, sd=2) # Esto nos diria la probabilidad de ser menor que 6.
pnorm(7, mean=6, sd=2) # Y la de ser menor que 7
# Luego:
pnorm(7, mean=6, sd=2) - pnorm(6, mean=6, sd=2)
# Si fuese "probabilidad de ser mayor...", hariamos la inversa
1 - pnorm(7, mean=6, sd=2) - pnorm(6, mean=6, sd=2)
1 - (pnorm(7, mean=6, sd=2) - pnorm(6, mean=6, sd=2))
# eso si
q()
