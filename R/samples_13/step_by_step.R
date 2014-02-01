onelist <- list("Fred","Mary",3,c(4,7,9))
#onelist es ahora una lista. Las listas en R nos permiten agrupar distintos tipos en la misma colección. Se accede con doble corchete.
onelist[[2]]
#Si vamos al 4 elemento de la lista, será la colección.
onelist[[4]]
# Si queremos acceder al 2 elemento del vector almacenado en la 4 posicion de la lista,
onelist[[4][2]]
onelist[4][2]
onelist[[4]][2] # Asi si
onelist <- list(name="Fred", wife="Mary", no.children=3, child.ages=c(4, 7, 9))
#Asi podemos acceder por el nombre del indices
onelist$wife
onelist$child.ages
onelist$child.ages[2]
#Podemos almacenar los nombres de los indices para obtenerlos posteriormente
x <- "name"
#Ahora utilizamos la variable para acceder a dicho campo
onelist[[x]]
#
# Si ponemos un solo corchete para acceder a un elemento de la lista, lo que estamos haciendo es seleccionar un subconjunto de la lista,
# que, a su vez, es una lista
onelist[2]
class(onelist)
class(onelist[2])
class(onelist[[2]])
onelist[1:3]
onelist[1:3][[2]]
onelist[1:3][2]
#Entre dos corchetes nunca puede haber un rango, solamente un indice
#
#
#
# PARALELISTMO ENTRE MERGEs. R ES ESPECIALMENTE BUENO EN ESTO.
#
# Seria lo equivalente a un join de SQL.
#
data1 <- data.frame(id=c(1, 3, 4, 5, 7), 
                    firstname=c("Juan", "Pedro", "Ana", "David", "Leire"))
data1
#Nos creamos otro conjunto que tambien tiene un identificador y un apellido en este caso
data2 <- data.frame(id=c(1, 2, 3, 4), lastname=c("Pérez", "Bueno", "Montoya", "Vidal"))
#Hagamos un primer merge entre las dos tablas
data <- merge(data1, data2, by="id") # Esto nos hace un join por campo id. Si los campos se llamasen distintos se haría by.x="nombre campo tabla1" by.y="nombre campo tabla2"
data
# Esto sería un INNER JOIN
#
#
# Otros joins
#
# Le indicamos que se apor el campo ID pero queremos que saque todos los X (all X  = TRUE).
data <- merge(data2, data2, by="id", all.x=T)
data
# En este caso tendriamos un LEFT JOIN
#
#
# El right join sería el siguiente
data <- merge(data1, data2, by="id", all.y=T)
data
# Ahí tendríamos el RIGHT JOIN
#
# Correccion en el LEFT JOIN (typo)
data <- merge(data1, data2, by="id", all.x=T)
data
#
#
# Para sacar todas las posibilidades. OUTER JOIN:
data <- merge(data1, data2, by="id", all=T)
# Todo esto se le da muy bien a R; lo que no se le daria tan bien podrían ser los Group BY de SQL
#
#
# Ahora le cambiaremos el nombre al campo "id" para que no coincida con la otra tabla
name(data2)[1] <- "id2"
names(data2)[1] <- "id2"
#
data <- merge(data1, data2, by.x="id", by.y="id2", all.y=T)
data
# Tambien se podrian hacer MERGEs por mas de un campo, pasando una coleccion en lugar de un item en los indices
#
#
#
# AGREGACIONES DE DATOS. Paquete plyr
library(plyr)
#Nos creamos un data_frame.
datos <- data.frame(color=c(rep("blanco",3), rep("rojo",2), rep("verde", 5)),
                    valor=c(1, 3, 2, 5, 2, 1, 2, 4, 5, 6),
                    stringAsFactors=F)
datos <- datos[order(datos$valor)
datos
datos <- datos[order(datos$valor)]
datos <- datos[order(datos$valor),]
datos
#
result <- ddply(datos, "color", function(x) data.frame(ncasos=neow(X), sumvalor=sum(X$valor)))
# A cada subconjunto del dataframe le aplica una funcion (en este caso crea un data frame que tiene el numero de casos del agumento X. 
result <- ddply(datos, "color", function(x) data.frame(ncasos=new(X), sumvalor=sum(X$valor)))
result <- ddply(datos, "color", function(x) data.frame(ncasos=new(x), sumvalor=sum(X$valor)))
result <- ddply(datos, "color", function(x) data.frame(ncasos=nrow(x), sumvalor=sum(X$valor)))
result <- ddply(datos, "color", function(x) data.frame(ncasos=nrow(X), sumvalor=sum(X$valor)))
result <- ddply(datos, "color", function(x) data.frame(ncasos=nrow(x), sumvalor=sum(x$valor)))
result
#Algunos casos habituales de agregación
#
# La función summarize nos permite "sumarizar" todos los campos por color, y obtiene para cada campo su longitud y su suma de valor
result <- ddply(datos, "color", summarize, ncasos=length(valor), sumvalor=sum(valor))
# Seria lo mismo que la function que nos creamos antes, pero de una maneras más "fácil".
result
#Otra funcion muy habitual que nos puede interesar, si tuviesemos varios argumentos;
# es transform.
# Algunas veces queremos tener el mismo dataframe original añadiendole un nuevo campo. (Un group by + un merge en SQL). 
# Preserva los casos originales y ademas les mezcla el sumario que sale de hacer lo que sea.
result <- ddply(datos, "color", transform, sumvalor=sum(valor))
result
# Esta parte le cuesta mas a R que los JOINs. 
#
#
#
# DATA TABLES IN ACTION
#
#
#
# Data.table en R es muy potente, aunque su sintaxis no es muy "bonita".
library(data.table)
library(plyr)
# En este punto deberiamos tener los datos en el directorio actual (cell2grid)
load("cell2grid")
load("data/cell2grid")
# A ver cuantas filas tienes
nrows(cell2grid)
nrows("cell2grid")
nrow(cell2grid)
#5 millones.
#
# Un ejemplo de las celdas (el grid)
cell2grid[1:10,]
# ci serian las celdas.
# gridid serian casillas de la superficie del terreno.
# area seria el area de interseccion entre esa celda y esa casilla
# lu_area seria una especie de area ponderada (por tipo de uso del terreno, a lo mejor para descartar un lago)
# factor seria la importancia de esa area de interseccion respecto al total de la celda.
# factor_lu lo mismo pero con el area ponderada
#
#
# De momento nos quedaremos con el area y el area ponderada
cell2grid <- cell2grid[c, c("ci", "gridid", "area", "lu_area")]
cell2grid <- cell2grid[, c("ci", "gridid", "area", "lu_area")]
# Descartamos de momento los factores
#
# Seleccionamos registros.
cell2gid_dt <- data.table(cell2grid, key="ci") # Indexamos por ci para hacerlo mas veloz
cell2grid_dt <- data.table(cell2grid, key="ci") # Indexamos por ci para hacerlo mas veloz
#
#
# Si lo visualizamos
cell2grid_dt
# Veremos que el datatable no es muy distinto a un dataframe.
# Simplemente añade algun campo más.
# No perdemos nada por haberlo convertido a data table.
# Data Table permite, ademas de realizar nuevas cosas, hacer todo lo que podríamos con data table.
# Comprobaremos los tiempos en encontrar los registros con valor de celda X, tanto con data frame como con data table
system.time(out <- cell2grid[cell2grid$ci == '065534003281",])
)
)
system.time(out1_dt <- cell2grid_dt[cell2grid_dt$ci == '065534003281",])
system.time(out <- cell2grid[cell2grid$ci == "065534003281",])
#Con el datatable indexado tardamos en cambio
system.time(out1_dt <- cell2grid_dt[cell2grid_dt$ci == "065534003281",])
#
#
# Podemos tambien indexarlo en lugar de por dato, con otro datable que tenga la misma clave
system.time(out2_dt <- cell2grid_dt[data.table(ci="065534003281", key="ci"),])
# El tiempo indexando asi es muy superior.
#
#
# Seleccionamos ahora registros y columnas.
cell2grid[cell2grid$ci == "065534003281", c("ci", "gridid", "lu_area")]
# Con un data table podriamos intentar hacerlo como si fuese un data_frame, pero veremos que el resultado no es el esperado.
cell2grid_dt[cell2grid_dt$ci == "065534003281", c("ci", "gridid", "lu_area")]
# El problema es que el segundo indice no funciona igual que en el data frame. 
# Para que funcione igual hay dos alternativas, la primera: decirle que no utilice:
cell2grid_dt[cell2grid_dt$ci == "065534003281", c("ci", "gridid", "lu_area"), with=F]
#Tambien podemos hacer lo siguiente:
# Le pondremos una lista con los nombres de las variables que queremos seleccionar
cell2grid_dt[cell2grid_dt$ci == "065534003281", list(ci, gridid, lu_area)]
# Conclusion. o le decimos with=F o le pasamos una lista con los nombres de las variables.
# La ventaja de la lista, es que ademas de selccionar las variables que queremos, podriamos cambiarles el nombre
cell2grid_dt[cell2grid_dt$ci == "065534003281", list(newci=ci, newgridid=gridid, newlu_area=lu_area)]
# Tambien podriamos cambiar el orden en el que aparecen
cell2grid_dt[cell2grid_dt$ci == "065534003281", list(kk=gridid, newci=ci, newgridid=gridid, newlu_area=lu_area)]
# Parte bonita:
# Los data table nos permiten ejecutar cosas en diferentes partes de los datos. MAP REDUCE.
# Para agilizar, nos quedamos con un millon de elementos
part_cell2grid <- cell2grid[1:1000000,]
# Ahora nos construimos un data table con clave el cell_id
part_cell2grid_dt <- data.table(part_cell2grid, key="ci")
#
# Ahora la agrupamos con ddply
system.time(out <- ddply(part_cell2grid, "ci", summarise, ci=unique(ci), ngrid=length(gridid)))
# En el caso anterior, utilizamos summarise para ver con cuantas celdas intersecta un data grid.
out[1:10,]
# Ahi vemos que la primera celda intersecta con 36 grids, otra con 41 etc.
# Hagamos lo mismo con el data table
# Veremos como seleccionamos en by el campo que es la clave (para que vaya bien)
system.time(out_dt <- part_cell2grid_dt[, list(ngrid=length(gridid)), by="ci"])
#Vemos que la salida es la misma.
out_dt
# Vemos que con data table ganamos mas de dos ordenes de magnitud en velocidad, que no es ninguna tonteria!
system.time(out_dt <- part_cell2grid_dt[, list(ngrid=length(gridid)), by="ci"])
part_cell2grid <- cell2grid[1:100000,]
part_cell2grid_dt <- data.table(part_cell2grid, key="ci")
#Agrupamos por cell id (ci); le haremos un transform y le añadiremos a cada dato el factor que habiamos quitado en el primer paso (area/sum(area)
system.time(out <- ddply(part_cell2grid, "ci", transform, factor=area / sum(area), lu_factor=lu_area / sum(lu_area)))
#
# Lo mismo, con data table, seria:
system.time(out_dt1 <- part_cell2grid_dt[, list(gridid=gridid, ngrid=length(gridid), area=area, lu_area=lu_area, factor=area / sum(area), lu_factor=lu_area / sum(lu_area)), by=ci])
system.time(out_dt2 <- cell2grid_dt[, list(gridid=gridid, ngrid=length(gridid), area=area, lu_area=lu_area, factor=area / sum(area), lu_factor=lu_area / sum(lu_area)), by=ci])
#
#
#
# Merge
# Es importante notar que con el data_table obtenemos mucha ganancia sin meter overhead sobre el dataframe, puesto que se construye "sobre" este.
#
#
# MERGEs
# Cogemos el datatable que teniamos, y le cambiamos la clave 
cell2grid_dt <- setkey(cell2grid_dt, gridid)
# Ahora haremos lo mismo que antes, pero en lugar de por celda por grid.
system.time(out_dt3 <- cell2grid_dt[, list(ci=ci, nci=length(ci), invfactor=area / sum(area)), by=gridid])
out_dt2 <- setkey(out_dt2, ci, gridid)
# Ahi creamos una clave doble, con celda y gridid
out_dt3 <- setkey(out_dt3, ci, gridid)
# Ahora cruzaremos estas dos tablas de 5 millones de celdas cada una.
system.time(cell2grid_lookup <- out_dt3[out_dt2,])
system.time(cell2grid_lookup <- out_dt3[out_dt2,][, list(ci, gridid, ngrid, nci, area, lu_area, factor, lu_factor, invfactor)])
# Esto es como si hubiesemos hecho un right join, sacamos tantos registros como diga dt2, y se le añaden los datos que cruzan de las que tenga dt3. (5 millones x 5 millones).
# Ademas del merge, al resultado que salga le indicamos el orden de los campos
q()
