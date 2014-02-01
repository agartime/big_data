#Instalamos el paquete de arules
install.packages("arules")
library("arules")

#Las reglas de asociación en R trabajan con un tipo de dato nuevo hasta ahora: transacciones
#Cargamos los datos de Groceries
data(Groceries)

summary(Groceries)

#element (itemset/transaction) length distribution:
#sizes
#1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18 
#2159 1643 1299 1005  855  645  545  438  350  246  182  117   78   77   55   46   29   14 
#19   20   21   22   23   24   26   27   28   29   32 
#14    9   11    4    6    1    1    1    1    3    1 

#Eso indica que hay 1005 transacciones de 4 elementos, 855 de 5 elementos, etc.

#Esto nos da las transacciones
inspect(Groceries)

#No es común que los datos no lleguen en este formato. Podrian llegar más comunmente así: 
trans0 <- data.frame(ID = c(1,1,2,2,2,3), # Id de transaccion (transaccion 1, transaccion2, etc.)
                     item = c("a","b","c","a","b","c")) # Elementos comprados
trans0

#Para transformar esto a tipo transaccion:
#Split divide en grupos y hace la reparticion de los elementos.
split(trans0$item, trans0$ID)

#Si le pasamos esto a "as", convertimos a tipo transactions.
trans1 <- as(split(trans0$item, trans0$ID), "transactions")
trans1
inspect(trans1)

#lo hacemos con groceries que es más lógico (hay pocos datos en el ejemplo)
rules <- apriori(Groceries, # transacciones
                 parameter=list(support=0.001, conf=0.5, target="rules")) #parametros (indica que los antecendetes de mis 
                 #reglas aparezcan el al menos el % indicado de nuestras transacctiones).
                 #conf es la confianza mínima.
                 
sort(rules, by="lift")
#Asi sacamos las 3 reglas mas importantes ordenadas por el lift
inspect(head(sort(rules, by="lift")),3)

#Con la funcion quality vemos los tres valores de las reglas.
head(quality(rules))

#Visualizaciones.
install.packages("arulesViz")
library("arulesViz")
plot(rules)
plot(rules,measure=c("support","lift"),shading="confidence") # Escala de grises respecto a la confianza
plot(rules,method="grouped") 
plot(rules,method="grouped", interactive=TRUE) 

#Un subconjunto de las reglas (con confianza > 0.95)
subrules <- rules[quality(rules)$confidence > 0.95]


#Otra manera de recibir los datos podría ser
a <- list( c("a","b","c"), c("a","d"))
#A esto se le daria un identificador de linea, algo así
names(a) <- c(1:2) # (El identificador de transaccion 1 o 2)
a_trans <- as(a,"transactions")

# Otras funciones utiles:
as.factor(c(1,2,3,1,1,1)) # Crea un vector y nos dice los posibles valores que contiene (le indicamos que son categóricos). Esto es útil para transacciones.
#Un vector de enteros no nos serviria para transaccioens, de ahi que tengamos que hacer el as.factor.


