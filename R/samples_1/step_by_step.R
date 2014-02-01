3 * (11.5 + 2.3)
g <- 4931
stem(murder)
murder
ls
ls()
murder <- (15.1, 11.3, 7.8, 10.3)
murder <- c(15.1, 11.3, 7.8, 10.3)
stem(murder)
# test
# Read from file with scan
illit <_ scan("material_clase/illitdata.txt")
illit <- scan("material_clase/illitdata.txt")
illit
# Read States
states <- scan("material_clase/statecodes.txt", character())
states
murder
murder/illit
# Los objetos deberian ser del mismo tamaño
murder <- c(15.1, 11.3, 7.8, 10.1, 10.3, 6.8, 3.1, 6.2, 10.7, 13.9
)
murder
murder/illit
# Con esto le decimos que ponga murder 10 veces en el objeto kk
kk <- rep(murder, 10)
kk
# EL numero entre corchetes indica el indice por el que va representando la linea
#Tomar 2 y elevarlo a la quinta potencia, la octava potencia, o al cubo
2^c(5,8,3)
# SECUENCIAS : Ej 0:10, Secuencia de 0 a 10 en incrementos de 1
0:10
# Se da cuenta en otro tipo de secuencias de cómo tiene que iterar
3:-7
# Solo funciona con unidades. Para indicarle incrementos, utilizamos seq (inicio, fin, incremento)
r1 <- seq(1, 1000, by=2)
r1
# INDEXADO
# Nota. sqldf es un paquete de R que permite utilizar SQL y ejecutarla sobre un tipo de objecto de R.
r2 <- rep(2,5)
r2
murder > 12
# Como vemos , aplica la condicion a cada elemento de murder y nos retorno cada valor
# Esto es muy útil para indexar. Si a un objeto de un vector, le indexamos un valor logico, no sdevolverá los true.
# summary nos hace un resumen
summary(murder > 12)
#Nos indicaria que 8 de los elementos no cumplen la condicion
#Si aplicamos summary sobre el vector directamente, nos da otro tipo de informacion
summary(murder)
#Ahora, con los corchetes, podemos indexarlo. Rango del 1 al 13. Nos devolveria los valores de 1 a 13 del vector murder. como solo tenia 10 valores...
#nos devuelve NAs
murder[1:13]
# Podemos indexar por una secuencia de valores logicos. Valores de murder en los que su valor es mayor que 12.
murder[murder > 12]
 # Que serían los dos únicos TRUEs que vimos antes
# El indice puede actuar para otro objeto, no para el mismo. Ej: Estados en los que murder es > 12.
states[murder > 12]
# Podemos tambien hacer consultas de otro tipo. Tasa de analfabetismo en los estados == "CA"
illit[states == "CA"]
# Indicaria tasa de analfabetismo = 1.1
#Una condición más complicada. Asesinatos > 12 y tasa de analfabetismo <= 2.
cond <- (murder > 12) & (illit <=2)
cond
murder[cond]
#Un ejemplo de OR.
cond <- (murder > 12) | (illit <= 2)
murder[cond]
#Otra forma curiosa de indexar. Seleccionar todas excepto la posicion 1. OJO
murder[-1]
#O todas, menos la 1 y la 7
murder[-c(1,7)]
#Guardar en un objeto (which_m12); Indices donde los asesinatos son mayores que 12.
which_m12 <- (1:10)[murder>12]
which_m12
# Y si no fuesen 10?
# Rango de 1 hasta la longitud de murder
which_m12 <- (1:length(murder))[murder>12]
which_m12
#La funcion which devuelve directamente los indices donde la condicion se cumple. Evitamos el tema del rango y demás.
s
which_m12 <- which(murder > 12)
which_m12
#Asi podemos indexar tambien. en lugar de utilizar un vector logico, utilizamos un conjunto de numeros que son los indices.
murder[which_m12]
#Lo habitual es tener los datos de forma tabular. Por ejemplo, matrices. Todos los valores deben ser el mismo tipo de dato (ej enteros).
#Si son de tipos distintos, utilizariamos los dataframes, que son una estructura muy tipica en R.
#R trae algunos datasets de ejemplo
#Por ejemplo, state.x77
state.x77
# Cada fila viene identificada por un nombre del estado.
state.x77
dimnames(state.x77)
# Esto anterior dimnames nos muestra los nombres de las filas y columnas (si los tiene).
#Vemos que la columna 5 indica la tasa de asesinatos.
murder <- state.x77(,5)
murder <- state.x77[,5]
murder
# El [,5] indica todas las filas y unicamente la 5 columna.
state.x77["Alabama",]
state.x77["Alabama",5]
#Podemos indicar varios.
state.x77[c("Alabama","Virginia"),]
row.names(state.x77)
colnames(state.x77)
#Lo anterior nos devolvia nombre de filas, y colnames el nombre de columnas
illit <- state.x77[,3]
# Ahi guardamos la tabla de analfabetismo.
states <- states.abb
states <- states.abv
states <- states.x77.abb
states <- state.abb
# Ahi guardamos las abreviaturas de los estados.
state.abb
#Un dibujito simple
plot(illit, murder, pch="*")
#Eso pintaria un grafico de puntos
#Tambien se puede hacer de manera interactiva para que nos muestre, al pulsar, el estado que representa.
identify(illit, murder, states)
plot(illit, murder, pch="*")
identify(illit, murder, states)
#Ahora definimos una variable donde los asesinatos sean > 10. Lo aplicaremos sobre el dibujo.
#Cada llamada a plot hace un dibujo nuevo. points sobre una funcion que ya piede existir, añade puntos sobre una grafica existente.
points(illit[nasty], murder[nasty], pch="0")
nasty <- murder > 10
points(illit[nasty], murder[nasty], pch="0")
# Esto resalta los puntos donde los asesinatos son mayores que 10
plot(illit, murder, pch="*")
#Ahora añadimos un texto en ciertos puntos
text(illit[nasty], murder[nasty], states[nasty], adj=-0.25)
datos_estados <- data.frame( murder=murder, illit=illit, ab_state=states, state=row.names(state.x77), row.names=NULL, stringsAsFactors=F)
#Asi creamos un dataFrame!
#Vemos qeu dentro del mismo objeto mezclamos variables numericas con variables ue no lo son.
datos_estados
#Para acceder a las columnas de un dataframe
datos_estados$illit
#De esa forma seleccionamos columnas
names(datos_estados)
#Obtenemos los nombres de las columnas con names
# O tambien podemos mediante indices
datos_estados[,2]
# O tambien por el nombre
datos_estados[,"illit"]
#La mas habitual cuando se conocen los nombres seria la primera: datos_estados$illit
datos_estados[1, c(2, 1)]
#Asi hemos cambiado el orden de las columnas (con un vector 2,1)
#Condiciones para seleccionar
datos_estados[datos_estados$murder > 10, ]
#Seleccionar algunas columnas en un cierto orden.
datos_estados[datos_estados$murder > 10, c("state", "illit", "murder")]
# Cómo leer un dataframe de un fichero!!! ??? read.table !
# Esto generara un data frame con los datos de un fichero my_iris.txt. Le decimos que no tiene cabeceras (si no, la primera fila la cogeria como cabecer
# Esto generara un data frame con los datos de un fichero my_iris.txt. Le decimos que no tiene cabeceras (si no, la primera fila la cogeria como cabecer
#. Elseparados es la barra vertical
#Las cadenas estan delimitadas por comillas dobles
#Los nombres de las columnas seran las siguiente.
#Los tipos de los datos
lirios <-
# Si se conoce el numero de registros del fichero, es buena idea pasarle un argumento que le indica el numero de filas. 
# help(read.table)
help(read.table)
# Tal parametro es nrows
lirios <-
   read.table("material_clase/my_iris.txt", header=F, sep="|", quote="",
      col.names=c("longsep", "anchsep","longpet","anchpet","clase"),
      colClasses=c("numeric","numeric","numeric","numeric","numeric","character"),
      stringsAsFactors=F)
lirios <-
# NO lo hemos creado... pero lo borrariamos asi
rm(lirios)
#Podemos guardar un objeto a fichero.
save(murder, "murder_saved.R"
)
# Y para cargarlo: load("murder_saved.R")
#En general, en R es buena idea crear un directorio para cada proyecto, debido a este salvado de ficheros, y a la posibilidad
# de que R nos guarde toda la sesión a fichero al salir.
# FUNCIONES
alcuadrado <- function(x) x^2
alcuadrado
alcuadrado(3)
# En las funciones, lo que devuelven, seria la ultima linea antes de devolver el control.
a
#Podemos aplicarlo en vectores
alcuadrado(1:5)
is.min <- function(x) x==min(x)
is.min(datos_estados$illit)
datos_estados$state[is.min(datos_estados$illit)]
source("material_clase/nuevoalcuadrado.r")
#Source obtiene como codigo un .r. Esto es util para definir funciones en ficheros por ejemplo.
nuevoalcuadrado
nuevoalcuadrado(1:5)
#Ojo, solo imprime una vez el texto porque la funcion se llama SOLAMENTE una vez
#No una vez por cada elemento del vector como podriamos pensar
# Salimos con q()
q()
