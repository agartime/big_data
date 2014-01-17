nperpiso <- scan("ej_1_2_nperspiso.txt")
table(nperpiso)
# table(nperpiso) / sum(table(nperpiso))
table(nperpiso) / sum(table(nperpiso))
table(nperpiso) * 100 / length(nperpiso)
barplot(table(nperspiso)
)
barplot(table(nperpiso)
)
# DIagrama barras
barplot(table(nperpiso)*100/length(nperpiso))
pie(table(nperpiso))
mean(nperpiso)
median(nperpiso)
max(table(naperpiso))
max(table(nperpiso))
which.max(table(nperpiso))
names(table(nperpiso))
# Valores de la tabla,
as.integer(table(nperpiso))
# Las etiquetas:
names(table(nperpiso))[which.max(table(nperpiso))]
# Indexamos los nombres qudandnos con aquel para el cual el valor de la table es maximo ))
# el max anterior daba el nombre, con el which el valor y el nombre
# 
rango1 <- rnorm(100, mean=2, sd=0.5)
# Eso nos crea una distrubucion de 100 valores con media 2 y distributcion tipica 0.5
hist(rango1)
# Hist nos pinta el histograma.
rango2 <- rnorm(100, mean=2, sd=2)
hist(rango2)
# Ahora en lugar de una distribucion normal (gaussiana), nos crearemos una uniforme.
rango3 <- runif(100, min=-4, max=4)
hist(rango3)
#Podemos dividir el plot con varios raficos
par(mfrow=c(1,2)) #Una file y dos columnas
hist(rango2)
hist(rango3)
var(nperpiso)
#Varianza 
var(nperpiso) * length(nperpiso) -1) / length(nperpiso)
(var(nperpiso) * length(nperpiso) -1) / length(nperpiso)
## Esa es la diferencia con la version corregida (lo del n y el n-1 en el calculo)
#SD (Desviacion tipica)
sd(nperpiso)
sqrt(var(nperpiso))
# DEsviacion tipica = raiz cuadrada varianza
#Percentiles:
# (quantiles)
quantile(nperpiso)
quartile(rango1)
quantile(rango1)
#POdemos pedirselo de 10 al 10 %
quantile(rango1, seq(0,1,0.1))
summary(rango1)
 
#rango intercuartilico
quantile(rango1)[2]
quantile(rango1)[4]
quantile(rango1)[4] - quantile(rango1)[2]
 # que seria el rango intercuartilico
#Diagrama de cahas
boxplot(rango1)
help
help()
boxplot(rango1)
options(mfrow=c(1,1))
par(mfrow=c(1,1))
options(mfrow=c(1,1))
par(mfrow=c(1,1))
boxplot(rango1)
#Con colorinos
boxplot(rango1, col="red")
#Distancias de Cadiz al resto 
distcadiz <- scan("ej_1_21_distcadiz.txt")
boxplot(distcadiz)
#Si quisiesemos ponerlo en horizontal...
boxplot(distcadiz, horizontal=T)
#Añadir rallas verticales
abline(v=seq(0,1500,100), lty=2) # De 0 a 1500 de 100 en 100, tipo de linea 2
boxplot(distcadiz, horizontal=T)
abline(v=seq(0,1500,100), lty=2, col="lightgrey") # De color gris claro
#Un titulo
boxplot(distcadiz, horizontal=T, main="Distancias desde Cadiz")
# DATOS DEL IPC
ipc <- scan("ipc.txt")
par(mfrow=c(1,2))
boxplot(ipc)
#Transformamos con el logaritmo decimal en base 10 (log10)
boxplot(log10(ipc))
q()
#Creamos un PDF
pdf("migraf.pdf")
boxplot(log10(ipc))
dev.off()
quartz
q()
