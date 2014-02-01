#Instalamos la libreria neuralnet
install.packages("neuralnet")
library("neuralnet")

#Cargamos los datos infert
data(infert)

#Para ver los datos en formato tabla
View(infert)

#neuralnet por defecto crea una red neuronal con backpropagation
#Guardamos el modelo de la red neuronal
#Lo suyo seria primero normalizar la muestra, aqui no lo haremos porque ya lo controlamos :D

#Podemos crearnos un subconjunto del modelo original.
#Hacemos el random para coger solo ciertas variables.
infert_train = infert
nn <- neuralnet(case~age+parity+induced+spontaneous, # a partir de que variables queremos obtener nuestra var. de salida
                infert_train,#observaciones de entrenamiento
                #hidden = c(3,2), #3 capas ocultas con 2 neuronas
                hidden = 2, #1 capa oculta con dos neuronas
                #algorithm = "backprop",
                learningrate = 0.5, # Grado aprendizaje
                threshold = 0.02,
                err.fct="sse", #Funcion del error
                )
#Vemos la red
nn
#Podemos ver el plot de la red
plot(nn)

#y su estructura
nn$response
nn$net.result

# Ejemplo de test:
test1 <- infert[1:100,]
test1 <- subset(test1, select = c("age", "parity", "induced", "spontaneous"))

# Para ver un resultado
testresults <- compute(nn, #modelo
                       test1) # conjunto de test

#Grafica lift (grafica de ganancias). Hay librerias, pero para verla manualmente:
#Ordenar conjunto de menor a mayor, pegarle la salida real correspondiente y calcular.

