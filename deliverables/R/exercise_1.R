# Ejercicio 1 - Antonio González Artime

#
# Carga de datos
#
t_ray <- scan("./data/newcomb.txt", what=integer())
summary(t_ray)

#
# Análisis exploratorio
#
hist(t_ray,12,
     main="Histogram of t_ray",
     xlab="Time",
     ylab="Frequency")
median(t_ray)
mean(t_ray)
quantile(t_ray)

# Chebycheff
low_atyp_limit <- mean(t_ray)-3*sd(t_ray)
high_atyp_limit <- mean(t_ray)+3*sd(t_ray)

t_ray_pos <- t_ray[t_ray > low_atyp_limit & t_ray < high_atyp_limit]
hist(t_ray_pos,20,
     main="Histogram of t_ray",
     xlab="Time",
     ylab="Frequency")
boxplot(t_ray_pos, horizontal=TRUE, main="BoxPlot of t_ray_pos")

# Cuartiles
low_atyp_q=quantile(t_ray)[2] - 1.5*(quantile(t_ray)[4]-quantile(t_ray)[2])
high_atyp_q=quantile(t_ray)[4] + 1.5*(quantile(t_ray)[4]-quantile(t_ray)[2])
low_atyp_q
high_atyp_q

# Muestra final
t_ray_pos <- t_ray[t_ray > low_atyp_q & t_ray < high_atyp_q]
t_ray_pos

hist(t_ray_pos,15,
     main="Histogram of t_ray",
     xlab="Time",
     ylab="Frequency",
     col="orange")
abline(h=seq(0,10,10), lty=2, col="lightgrey") # De color gris claro
abline(v=seq(0,40,10), lty=2, col="lightgrey") # De color gris claro

boxplot(t_ray_pos, horizontal=TRUE, main="BoxPlot of t_ray_pos", col="orange")
abline(v=seq(0,40,5), lty=2, col="lightgrey") # De color gris claro

median(t_ray_pos)
sd(t_ray_pos)

text(hist.a$mids, hist.a$counts, labels=ifelse(hist.a$counts==0,"",hist.a$counts), pos=3) 

t_ray_to_nano <- function(x) x+24800
t_ray_nanoed <- t_ray_to_nano(t_ray_pos)

plot(t_ray_nanoed, col="orange")
abline(h=mean(t_ray_nanoed), lty=2, col="red")

dist_km = 7400/1000 
speed=dist_km/(mean(t_ray_nanoed)*10^-9)
speed
