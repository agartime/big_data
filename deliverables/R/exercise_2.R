# Ejercicio 2 - Antonio González Artime

#
# Carga de datos
#
load("./data/mamifero")

#
# Exploración inicial
#
mamifero
summary(mamifero)

par(mfrow=c(1,2))
hist(mamifero$cuerpo)
hist(mamifero$cerebro)

par(mfrow=c(1,1))
boxplot(mamifero, horizontal=TRUE)

par(mfrow=c(1,2))
qqnorm(mamifero$cuerpo, main="Q-Q cuerpo", col="blue")
qqline(mamifero$cuerpo)
qqnorm(mamifero$cerebro, main="Q-Q cerebro", col="orange")
qqline(mamifero$cerebro)

#Correlación fuerte y positiva
cor(mamifero)


#Transformacion 
lmamifero <- log(mamifero)
par(mfrow=c(1,1))
boxplot(lmamifero, horizontal=TRUE)

par(mfrow=c(1,2))
qqnorm(lmamifero$cuerpo, main="Q-Q log(cuerpo)", col="blue")
qqline(lmamifero$cuerpo)
qqnorm(lmamifero$cerebro, main="Q-Q log(cerebro)", col="orange")
qqline(lmamifero$cerebro)
cor(lmamifero)

#
# Identificación "hombre"
#
mamifero
possible_humans <- mamifero[mamifero$cuerpo > 50 & mamifero$cuerpo <90,]
possible_humans


cor(lmamifero)

par(mfrow=c(2,2))
lm_lmamifero <- lm(lmamifero$cerebro ~ lmamifero$cuerpo)
summary(lm_lmamifero)
plot(lm_lmamifero)

trunc_lmamifero <- lmamifero
cor(trunc_lmamifero)
trunc_lmamifero <- lmamifero[c(-32,-34,-35),]
cor(trunc_lmamifero)

