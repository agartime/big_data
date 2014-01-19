# load("ratas")
# table("ratas")
load("ratas")
table("ratas")
table(ratas)
# Cargamos el fichero de ratas
history()
#
#
table(ratas$tint, ratas$veneno)  #El tiempo lo queremos hacer depender del veneno. La primera ratas$t seria la variable que se quiere explicar
#Seria la tabla de las frecuencias condicionadas. Tiempo en funcion del veneno.
# Pintamos el diagrama de cajas, utilizamos la virgulilla par aindicar "en función del". Tiempo en funcion del veneno.
boxplot(ratas$t ~ ratas$veneno)
boxplot(ratas$t ~ ratas$veneno)
# TASAS PAISES
#
#
#
load("tasas_paises"=
load("tasas_paises")
#
#
#Sacamos la matriz de varianzas
var(tasas_paises)
var(tasas_paises)
#Creamos una seleccion y nos quedamos con las columnas numericas.
miseltasas <- tasas_paises[, c("talfab", "habmed", "habcam")]
miseltasas
plot(miseltasas)
plot(miseltasas)
plot(miseltasas)
#
#
# Matriz de covarianzas
cov(miseltasas)
#
#
# Matriz de correlacion (entre -1 y 1)
cor(miseltasas)
#
#
#
#
####### PESO/ESTATURA ###########
load("peso_estatura")
ls()
peso_estatura
`plot(peso_estatura)
plot(peso_estatura)
# 
#
# Correlación
cor(peso_estatura)
# A la correlacion le podemos pasar directamente las variables)
cor(peso_estatura$peso, peso_estatura$estatura)
#
#
# La mejor recta que ajusta unos puntos. Recta de regresion.
a <- cov(peso_estatura$peso, peso_estatura$estatura) / var(peso_estatura$peso)
a
#
# Su contrario, estatura en funcion del peso
a <- cov(peso_estatura$estatura, peso_estatura$peso) / var(peso_estatura$estatura)
a
#
#
#En el eje X se suele poner la variable "predictora"
# a seria la pentiente
#
#Tambien teniamos b.
b <- mean(peso_estatura$peso) - a * mean(peso_estatura$estatura)
b
#
# b suele ser la ordenada en el origin y a la pendiente, pero R lo interpreta al reves, de ahi el a=b y b=a
plot(x=peso_estatura$estatura, y=peso_estatura$peso)
abline(a=b, b=a, col="green")
abline(a=b, b=a, col="green")
abline(a=b, b=a, col="green", lwd=2)
#
# Todo es to normalmente no se hace, para eso estan los modelos predictivos de R
#Creamos un modelo lineal y le indicamos que lo queremos predecir en funcion de peso_estatura$estatura.
modl <- lm(peso_estatura$peso ~ peso_estatura$estatura)
modl
# Ahi ya me dice que la estatura la tengo que multiplicar por 57.89 y que la ordenada en el origen seria -38.
# Esto nos lo detalla con summary
summary(mod1)
summary(modl)
#Los residuals indican los residuos/errores de cada punto
# intercept seria la ordenada en el origen
# peso_estatura$estatura, por lo que hay que multiplicar para obtener el peso
#Puesto que los valores de Pr(>|t|) son bajos, indican que no está tan mal
# EL multiple R-squared viene a decir que el 63% de la variabilidad de los datos queda explicada por el modelo.
# Queda por tanto un 37% que el modelo no es capaz de explicar.
# Cuanto mas bajo sea el p-value el ajuste seria mejor.
# Seria la bondad del ajuste.
# Si hacemos el plot:
plot(y=peso_estatura$peso, x=peso_estatura$estatura)
# el abline tambien puede recibir un modelo, y lo pintaria.
abline(modl)
# Al plot tambien le puedes pasar un modelo e ira mostrando distintas cosas.
# 1. Los residuos frente a los valores ajustados. Si viesemos los residuos en forma de trompeta, podria indicar que en realidad el modelo no sigue un patron lineal.
# 2. Vemos un trazado de los cuartiles de los residuos estandarizados (variable - media / varianza). Indica si los residuos tienen una pinta gaussiana (seria bueno) o no.
# 3. Raiz cuadrada de los residuos frente a los valores ajustados... no muy interesante.
# 4. Distancia de q con dos umbrales y dentro los residuos estandarizados. Viene a representar la influencia de los residuos en el modelo ajustado. Asi como vimos que la media era muy
#    sensible a puntos atipicos; esto indica a ver si hay algun valor que influye mas que los otros en el ajuste del modelo. Si existe y ademas es atipico, es candidato a ser error y
#    convendria quitarselo de en medio.
plot(modl)
q()
