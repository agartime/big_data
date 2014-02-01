# Library rpart installation
install.packages("rpart")
library(rpart)
data(iris)

# Tenemos que elegir cual va a ser nuestra variable de decisión
# y cuales son las variables que querremos para el arbol. Podemos verlo
# en los ejemplos de la ayuda.

mytree <- rpart(Species ~ ., #El punto indica todas menos la misma (que es nuestra variable de decisión)
      data = iris,
      method = "class") # Matriz donde están los datos

#Vemos el detalle. Interesa sobre todo las variables que se quedan como separadores (en $frame).
# Si queremos ver cómo es el árbol en sí, podríamos mirar el campo en sí
unclass(mytree)
mytree

#El plot con arboles no sale muy allá, es mejor utilizar
plot(mytree)
text(mytree)
#Podemos utilizar rpart.plot para esto
install.packages("rpart.plot")
library(rpart.plot)
#And the way to plot it is :
prp(mytree)

#One more detailed would be rattle, this one requieres RColorBrewer
install.packages("rattle")
install.packages("RColorBrewer")
library(rattle)
fancyRpartPlot(mytree)

#We would like to use the tree. Here comes "predict"
predict(mytree,iris[1:4]) #Instead of iris we should use other data to train the tree
