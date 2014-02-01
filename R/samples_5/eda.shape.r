eda.shape <- function(x) {
  par(mfrow=c(2, 2))
  hist(x)
  boxplot(x)
  iqd <- summary(x)[5] - summary(x)[2]
  plot(density(x, width=2*iqd), xlab="x", ylab="", type="l")
  qqnorm(x)
  qqline(x)
}
