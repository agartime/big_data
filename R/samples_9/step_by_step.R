data(USArrests)
USArrests.stand <- as.data.frame(scale(USArrests))
#Kmeans
mikmean <- kmeans(USArrests.stand, 3)
unclass(mikmean)

#Pam
library(cluster)
mipam <- pam(USArrests.stand, 3)
unclass(mipam)
