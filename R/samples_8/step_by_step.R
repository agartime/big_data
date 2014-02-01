#install.packages("HSAUR")

#Cargamos el paquete de coches
#data("heptathlon", package="HSAUR")
# Cargamos arrestos
data(USArrests)

#Declaramos la variable normalizada
#La normalizamos para que tenga media 0 y varianza 1.
USArrests.stand <- as.data.frame(scale(USArrests))

#Vemos cuál es la desviacion tipica para ver si lo ha hecho bien (que seguro que si)
sapply(USArrests.stand, "sd")

#Para la distancia euclidea utilizaremos dist. Asi obtenemos la matriz de distancias.
help(dist)
mat_euclidea <- dist(USArrests.stand, "euclidean")
mat_euclidea

#Obtenemos la matriz de correlaciones. cor siempre espera que las entradas
# lleguen en columnas. De ahí que hagamos la traspuesta.
#Nos g
mat_cor <- cor(t(USArrests.stand), method ="spearman")
#La funcion as.dist nos transforma distancias al formato que necesitamos
mat_cor <- as.dist(mat_cor)

#Clustering jerárquico - hclust (libreria stats)
help(hclust)
mihclust <- hclust (mat_euclidea, method="complete", members = NULL)

plot(mihclust, hang=-1)
unclass(mihclust)

#Cómo quedarse con un cluster que esté cortado según un límite que está cortado
# por una distancia máxima -> cutree(tree, k, h)
# Devuelve un vector con los clusteres. En este caso 6 (tomamos de corte la mitad de la altura máxima).
cortado <- cutree(mihclust, h=max(mihclust$height)/2)
cortado



