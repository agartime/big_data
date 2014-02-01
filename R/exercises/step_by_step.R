# Dado un vector x<-c(2,5,120,5,8,11), crear un vector i que contenga los indices de x.
#
# Solucion:
i <- 1:length(x)
x<-c(2,5,120,5,8,11)
i <- 1:length(x)
i
#
#
# 2. Dado un vector x, crear un vector inv_i que contenga los indices de x ordenados de mayor a menor.
#
# Solucion: sort ordena de manera creciente, pero tiene una opcion decreasing.
inv_i <- sort(i, decreasing=T)
inv_i
# Otra opcion:
inv_i <- length(x):1
inv_i
# Otra opcion:
inv_i <- seq(length(x), 1, by=-1)
inv_i
#
# 3. Dado un vector x, crear un vector inv_x que contenga los valores de x empezando por el ultimo, luego el penultimo, etc...
#
# Solucion
# Se podria utilizar reverse...
rev(x)
# Pero la opcion que buscamos seria mediante indexacion.
x[inv_i]
inv_x <- x[inv_i]
inv_x
#
# 4. Supongamos que me he equivocado al crear el vector x y he olvidado el numero 7 entre el 5 y el 8 (indices 4 y 5 resp.). ¿Como arreglarlo? Tambien he olvidado el ultimo numero. Despues del 11, viene un 3. Finalmente, resulta que el valor 120 es un error tipografico, en realidad es un 12. Llevar a cabo las correcciones pertinentes.
#
# Solucion:
# Una opcion sencilla . 
x <- c(x[1:4], 7, x[5:length(x)])
x
# Segunda parte.
# Esto funcionaria aunque no es muy intuitivo.
x[length(x) + 1] <- 3
x
# Lo suyo seria un: x <- c(x,3)
# Tercera parte.
# Si no supiesemos el indice de 120, podriamos hacer.
x[x==120] <- 12
x
# Podriamos ver el indice del 120 utilizando which: which(x==120)
#
#
# 5. Generar dos vectores x e y tal que x contenga los valores de 0 a 5 con un incremento de 0.01, y tal que y sea igual a sin(x). A continuacion se puede realizar la grafica de y en funcion de x con la instruccion plot(x,y)
#
#
x <- seq(0,5,0.01)
y <- sin(x)
plot(x,y)
#
# 6. Generar un vector que contenga 12 valores, los primeros 4 que sean igual a 0.5, los 4 siguientes a 1.5, y los 4 ultimos a 3.
#
# Solucion:
##########
#
# Antes de nada, unas funciones interesantes del plot.
plot(x,y,pch="*")
plot(x,y,pch=".")
# Si queremos que conecte los puntos, type=l
plot(x,y,type="l")
# Podemos ponerle el grafico.
plot(x,y,type="l",main="Gráfico de ejercicio\npara ver el uso de \"plot\"")
# Podemos cambiar el nombre de los ejes
plot(x,y,type="l",main="Gráfico de ejercicio\npara ver el uso de \"plot\"", xlab="Valores entre 0 y 5")
plot(x,y,type="l",main="Gráfico de ejercicio\npara ver el uso de \"plot\"", xlab="Valores entre 0 y 5", ylab="Seno de x")
# Si quisiesemos poner la misma escala en x e y cambiamos la relacion de aspecto
plot(x,y,type="l",main="Gráfico de ejercicio\npara ver el uso de \"plot\"", xlab="Valores entre 0 y 5", ylab="Seno de x", asp=1)
# Podemos tambien añadir un grid.
grid()
# Una leyenda, añadimos el coseno
z <- cos(x)
points(x, z, type="l", col="blue")
legend("topright", legend=c("sin(x)","cos(x)"), col=c("black","blue"), lty=1)
# Podriamos poner un cuadradito en cada valor de la leyenda utilizando legend(......, fill=T)
x <- seq(0, 5, 0.25)
y <- sin(x)
# ALgunas veces queremos unir los puntos de una grafica pero queremos resaltar algunos.
plot(x, y, type="b", pch=3)
plot(x, y, type="b", pch=4)
plot(x, y, type="b", pch=5)
# etc.
plot(x, y, lty=2, type="l", pch=5)
plot(x, y, lty=5, type="l", pch=5)
plot(x, y, lty=5, type="l")
#
#
#
# Volvemos al ejercicio 6.
# 6. Generar un vector que contenga 12 valores, los primeros 4 que sean igual a 0.5, los 4 siguientes a 1.5, y los 4 ultimos a 3.
#
# Solucion:
v <- c(rep(0.5,4), rep(1.5,4), rep(3,4))
#
# 7. Introduzca los siguientes vectores:
x <- c(1,3,5,7,9)
y <- c(2,3,5,7,11,13)
# Adivinar resultados de las instrucciones:
x+1
y*2
c(length(x), length(y))
x + y
# Ojo ahi, que para sumar con tamaños distintos, vuelve a empezar desde el principio con los valores "sobrantes".
#
sum(x>5)
# Por lo tanto, suma la cantidad de elementos (no su contenido). Es decir,
x>5
sum(x>5)
sum(x[x>5])
sum(x[x>5|x>3])
y[y>7]
#
#
# 8. Encontrar como calcular la media de un vector x sin utilizar la funcion mean(). Calcular, sin utilizar la funcion var(), la varianza de un vector x, utilizando la formula de la varianza muestra corregida (<formula de la var muestral corregida>). Compara los resultados con los dados por las funciones mean() y var().
# Media sin mean:
sum(x)/length(x)
mean(x)
meanx <- sum(x) / length(x)
sum((x - meanx)^2/(n-1)
var(x)
sum((x - meanx)^2/(n-1))
n <- length(x)
sum((x - meanx)^2/(n-1))
var(x)
sum((x - mean(x))^2/(length(x) - 1)
)
#
# 9. A lo largo de un año, los importes de las facturas mensuales de vuestro movil ha sido : 23, 33, 25, 45, 10, 28, 39, 27, 15, 38, 34, 29. ¿Cuantos habeis gastado en total en el año?¿Cuál ha sido el gasto mínimo?¿Y el máximo?¿Qué meses han supuesto un gasto mayor que el gasto medio?¿Qué porcentaje de meses han supuesto un gasto mayor que el gasto promedio?
#
# Solucion:
gasto <- c(23,33,25,45,10,28,39,27,15,38,34,29)
# Gasto total:
sum(gasto)
# Gasto minimo:
min(gasto)
# Gasto maximo:
max(gasto)
# Esto anterior lo podriamos haber hecho con summary.
which(gasto > mean(gasto))
meses <- c("Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic")
meses[which(gasto > mean(gasto))]
sum(gasto > mean(gasto)) / length(gasto
)
(sum(gasto > mean(gasto)) / length(gasto)) * 100
# O de otra forma:
length(which(gasto > mean(gasto>)) * 100
length(which(gasto > mean(gasto))) * 100
#
# 10. Mira con un editor de texto los datos que se encuentran en el fichero "anscombe.txt". No tiene cabecera para el nombre de las variables. Usa el caracter punto y coma como separador de columnas y el caracter coma como separador decimal. Tampoco contiene nombres para cada fila. Vamos a leer los datos en un dataframe llamado "ans". A las columnas les daremos los nombres x1, y1, x2, y2, x3, y3, x4, y4. Mira la ayuda de read.table() para entender el comando siguiente que hace la lectura:
# ans <- read.table(file="anscombe.txt", sep";", dec=",", header=F, col.names=c("x1","y1","x2","y2","x3","y3","x4","y4"))
# Usa la funcion write.table() para volver el data frame que has leido a un fichero de texto. Mira la ayuda para conocer las opciones. ¿Puedes adivinar lo que hara el siguiente comando?
# write.table(ans, file="ans.txt", sep=";", dec=".")
help(read.table)
#
# Solucion:
# Conviene indicarle siempre el tipo de los datos que vamos a cargar para que no se haga lio con los puntos, comas, etc.  Esto se puede hacer con colClasses=c("character"). Tambien podemos poner varios tipos ( colClasses=c("character","NULL","numeric")). El NULL hace que ese campo no se cargue en nuestro dataframe.
ans <- read.table(file="anscombe.txt", sep=";", dec=",", header=F, col.names=c("x1","y1","x2","y2","x3","y3","x4","y4"), colClasses=c("NULL", rep("numeric")

write.table(ans, file="ans.txt", sep=";", dec=".")
#
# 11. Usa write.table() para crear un fichero "ans13.txt" que solo contenga los datos de las variables x1, y1, x3, y3. Realiza graficas individuales de (x1,y1),(x2,y2),(x3,y3),(x4,y4).
#
# Nota. Con write.tables podemos escribir solamente ciertas filas asi:
# write.tables(ans[1:10,], file="ans.txt",sep="....."....
# O solo los que cumplan cierta condicion.... write.table(ans[ans$x1 > 10,], ....
write.table(ans[,c("x1","y1","x3","y3")], file="ans13.txt", sep=";", dec=".")
q()
