# parallel es una libreria para paralelizacion de procesos en R
library(parallel)
datos <- data.frame(v1=rnorm(200000), v2=runif(200000, min=-1, max=1))
# Ahi creamos una distribucion normal v1 con 200000 numeros, y una v2 con 200000 uniforme entre -1 y 1
#
#Lista con 20000 elementos, cada uno con el resultado de ejecutar la funcion. La funcion toma la posicion X del dataframe, imprime por la salida de errores el indice y a continuacion suma estos dos datos.Para paralelizar, en lugar de lapply, utilizaremos mclapply (multi core lapply), y le indicamos el numero de cores.
datos <- data.frame(v1=rnorm(200000), v2=runif(200000, min=-1, max=1))
system.time(result <- lapply(1:nrow(datos),
                             function(X) {
                               datloc <- datos[X,]
                               cat(file=stderr(), X, "\n", sep="")
                               datloc$v1 + datloc$v2
                             }))
# mclapply por defecto te devuelvel a salida en el mismo orden de la entrada pese a ser multiproceso. Se puede indicar que no te improta el orden y es un poco mas rapido.
system.time(result <- mclapply(1:nrow(datos),
                               function(X) {
                                 datloc <- datos[X,]
                                 cat(file=stderr(), X, "\n", sep="")
                                 datloc$v1 + datloc$v2
                               },
                               mc.cores=2))
q()
small.ints <- 1:1000
#Cargamos la libreria rmr2
library(rmr2)
small.ints <- to.dfs(small.ints) #Si no le indicamos el nombre del fichero, genera una referencia temporal a HDFS. Si cerramos R, se pierde tambien del HDFS.
small.ints
small.ints <- to.dfs(small.ints) #Si no le indicamos el nombre del fichero, genera una referencia temporal a HDFS. Si cerramos R, se pierde tambien del HDFS.
small.ints
small.ints <- to.dfs(small.ints) #Si no le indicamos el nombre del fichero, genera una referencia temporal a HDFS. Si cerramos R, se pierde tambien del HDFS.
small.ints = to.dfs(small.ints) #Si no le indicamos el nombre del fichero, genera una referencia temporal a HDFS. Si cerramos R, se pierde tambien del HDFS.
small.ints
small.ints = to.dfs(small.ints, output="pepe") # Ahora crearia el fichero en HDFS
# Para traernos datos del HDFS.
qq <- from.dfs("pepe")
small.ints = to.dfs(small.ints, output="pepe") # Ahora crearia el fichero en HDFS
small.ints = to.dfs(small.ints, output="pepe") # Ahora crearia el fichero en HDFS
q()
#El fichero de prueba contiene, el ID del usuario, la longitud y latitud en la cual el usuario tuvo un evento en la red telefonica, despues año mes y dia, despues el instante de tiempo (hora), despues, identificador de la celda en la que se levio, y despues otros datos del evento
options(digits=15)
options(scipen=999)
library(rmr2)
# Ejemplo. cuantas personas distintas se han conectado a una celda.
keystring <- ""
days_location <- "ulersample2.txt"
days_location
# el fichero ulersample2.txt estaria en nuestro HDFS
dedup_ulers_hour.map <- function(k, v) {
  proc_one_line <- function(index) {
    char_vec <- unlist(strsplit(v[index], split="|", fixed=T))[c(1, 4, 5, 6)]
    char_vec[3] <- substring(char_vec[3], 1, 2)
    paste(char_vec[1], char_vec[2], char_vec[3], char_vec[4], sep="|")
  }
  cat(file=stderr(), "dedup_ulers_hour.map:", length(k), length(v), "\n")
  outputkey <- sapply(1:length(v), proc_one_line)
  keyval(outputkey, 1)
}
# Explication anterior, en el tercero de los campos de char_vec (la hora), nos quedamos solo con los dos primeros caracteres (la hora).
# Posteriormente, lo juntamos en una sola cadena con el separador vertical.
# En el cat, imprimimos por la salida de error un mensaje que nos diga al llamar a la funcion, cuantas claves y cuantos valores tiene.
# En sapply, recorremos todos los valores y procesamos una linea con proc_one_line, almacenandolo en outputkey
# outputkey sera usuario, fecha, hora y celda
# por cada uno de ellos, ponemos un uno. Esto es el map.
#
# El reduce seria:
dedup_ulers_hour.red <- function(k, vv) {
  cat(file=stderr(), "dedup_ulers_hour.red", length(k), length(vv), "\n")
  keyval(k[1], 1)
}
# Esto lo que hace es, si hay alguno que comparte misma hora, celda y usuario, solamente le ponemos un 1
# Ojo con la salida estandar. Recordar que este paquete trabaja enviandole a hadoop todo por ahi. Si imprimimos algo, a la de errores.
result
dedup_ulers_hour.result
edup_ulers_hour.result <- 
  mapreduce(input = input_name,
            input.format = "text",
            map=dedup_ulers_hour.map,
            reduce=dedup_ulers_hour.red,
            combine=F)
dedup_ulers_day.result <- 
  mapreduce(input = dedup_ulers_hour.result,
            map=dedup_ulers_day.map,
            reduce=dedup_ulers_day.red,
            combine=F)
kk$val
#Resumen hasta ahora:
dedup_ulers_hour.map <- function(k, v) {
  proc_one_line <- function(index) {
    char_vec <- unlist(strsplit(v[index], split="|", fixed=T))[c(1, 4, 5, 6)]
    char_vec[3] <- substring(char_vec[3], 1, 2)
    paste(char_vec[1], char_vec[2], char_vec[3], char_vec[4], sep="|")
  }
  cat(file=stderr(), "dedup_ulers_hour.map:", length(k), length(v), "\n")
  outputkey <- sapply(1:length(v), proc_one_line)
  keyval(outputkey, 1)
}
dedup_ulers_hour.red <- function(k, vv) {
  cat(file=stderr(), "dedup_ulers_hour.red", length(k), length(vv), "\n")
  keyval(k[1], 1)
}
dedup_ulers_day.map <- function(k, v) {
  proc_one_record <- function(index) {
    char_vec <- unlist(strsplit(k[index], split="|", fixed=T))
    char_vec[3] <- "day"
    paste(char_vec[1], char_vec[2], char_vec[3], char_vec[4], sep="|")
  }
 
  cat(file=stderr(), "dedup_ulers_day.map", length(k), length(v), "\n")
  outputkey <- sapply(1:length(v), proc_one_record)
  keyval(outputkey, 1)
}
dedup_ulers_day.red <- function(k, vv) {
  cat(file=stderr(), "dedup_ulers_day.red", length(k), length(vv), "\n")
  keyval(k[1], 1)
}
footfall_ulers.map <- function(k, v) {
  proc_one_record <- function(index) {
    char_vec <- unlist(strsplit(k[index], split="|", fixed=T))
    char_vec <- char_vec[c(2, 3, 4)]
    paste(char_vec[1], char_vec[2], char_vec[3], sep="|")
  }
  cat(file=stderr(), "footfall_ulers.map", length(k), length(v), "\n")
  outputkey <- sapply(1:length(v), proc_one_record)
  keyval(outputkey, 1)
}
footfall_ulers.red <- function(k, vv) {
  cat(file=stderr(), "footfall_ulers.red", length(k), length(vv), "\n")
  keyval(k[1], sum(vv))
}
input_name <- paste(keystring, days_location, sep="")
#input_name <- "hdfs:/user/miguel/ulersample2.txt"
dedup_ulers_hour.result <- 
  mapreduce(input = input_name,
            input.format = "text",
            map=dedup_ulers_hour.map,
            reduce=dedup_ulers_hour.red,
            combine=F)
# Ahora o hacemos por dia. Transformaremos la hora por una cadena unica que sera "day" y, como output key, a todas las lineas las procesamos con esa funcion y sacamos esa nueva clave y un valor 1.
# El reduce, de todo lo que le llegue, se queda con una sola (si varios coinciden , cogemos solo uno; igual que hours).
dedup_ulers_day.result <- 
  mapreduce(input = dedup_ulers_hour.result,
            map=dedup_ulers_day.map,
            reduce=dedup_ulers_day.red,
            combine=F)
#Vemos el resultado del DFS
kk <- from.dfs(dedup_ulers_day.result)
kk$key # Vemos las claves
kk$val # Tendremos los unos
# Ahora lo contaremos
footfall_ulers.map <- function(k, v) {
  proc_one_record <- function(index) {
    char_vec <- unlist(strsplit(k[index], split="|", fixed=T))
    char_vec <- char_vec[c(2, 3, 4)]
    paste(char_vec[1], char_vec[2], char_vec[3], sep="|")
  }
  cat(file=stderr(), "footfall_ulers.map", length(k), length(v), "\n")
  outputkey <- sapply(1:length(v), proc_one_record)
  keyval(outputkey, 1)
}
footfall_ulers.red <- function(k, vv) {
  cat(file=stderr(), "footfall_ulers.red", length(k), length(vv), "\n")
  keyval(k[1], sum(vv))
}
#En el mapper partimos la cadena y nos quedamos con los campos 2,3,4 (no nos importa el usuario, solo la fecha, la hora/day y la celda).
# Como valor, la suma de todos los unos que hemos puesto antes.
footfall_ulers.result <-
  mapreduce(input = c(dedup_ulers_hour.result, dedup_ulers_day.result),
            map=footfall_ulers.map,
            reduce=footfall_ulers.red,
            combine=F)
# Ahi lo que hicimos es procesar las dos entradas anteriores juntas en secuencia.
# Cuando reciba "day", el significado será "de todo el día". Asi, podremos calcular a la vez tanto "todo el dia/day" como una hora concreta
kk <- from.dfs(footfall_ulers.result)
# Y vemos el resultado que nos hemos traido de HDFS
kk$key
kk$val
q()
