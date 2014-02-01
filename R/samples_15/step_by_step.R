#Ahora lo vemos con un data frame
options(digits=15)
options(scipen=999)
library(rmr2)
keystring <- ""
#keystring <- "s3n://"
days_location <- "ulersample2.txt"
#days_location <- "bucket.data.science/miguel/test_rmr/ulersample2.txt"
dedup_ulers_hour.map <- function(k, v) {
  cat(file=stderr(), "dedup_ulers_hour.map:", length(k), nrow(v), "\n")
  v$ts <- as.integer(paste(v$date, substr(v$time, 1, 2), sep=""))
  outputkey <- v[, c("uid", "ts", "ci")]
  keyval(outputkey, 1)
}
dedup_ulers_hour.red <- function(k, vv) {
  cat(file=stderr(), "dedup_ulers_hour.red", nrow(k), length(vv), "\n")
  keyval(k[1,], 1)
}
# En el mapper juntamos la fecha y la hora y lo guardamos en una variable (ts) timestamp
# Como clave, sacamos uid, timestamp y  celda.
#
# n el reducer, al ser un data frame, usamos coma y corchete
dedup_ulers_day.map <- function(k, v) {
  cat(file=stderr(), "dedup_ulers_day.map", nrow(k), length(v), "\n")
  k$ts <- round(k$ts * 0.01)
  keyval(k, 1)
}
dedup_ulers_day.red <- function(k, vv) {
  cat(file=stderr(), "dedup_ulers_day.red", nrow(k), length(vv), "\n")
  keyval(k[1,], 1)
}
footfall_ulers.map <- function(k, v) {
  cat(file=stderr(), "footfall_ulers.map", nrow(k), length(v), "\n")
  keyval(k[, which(names(k) != "uid")], 1)
}
footfall_ulers.red <- function(k, vv) {
  cat(file=stderr(), "footfall_ulers.red", nrow(k), length(vv), "\n")
  k$footfall <- length(vv)
  keyval(NULL, k[1,])
}
input_name <- paste(keystring, days_location, sep="")
#input_name <- "hdfs:/user/miguel/ulersample2.txt"
dedup_ulers_hour.input_format <-
  make.input.format(format="csv", sep="|", quote="", comment.char="",
                    col.names=c("uid", "lat", "lon", "date", "time", "ci",
                                "ig1", "ig2", "ig3", "ig4", "ig5", "ig6",
                                "ig7"),
                    colClasses=c("character", "NULL", "NULL", "integer",
                                 "character", "character", "NULL", "NULL",
                                 "NULL", "NULL", "NULL", "NULL", "NULL"))
dedup_ulers_hour.result <- 
  mapreduce(input = input_name,
            input.format = dedup_ulers_hour.input_format,
            map=dedup_ulers_hour.map,
            reduce=dedup_ulers_hour.red,
            combine=F)
kk <- from.dfs(footfall_ulers.result)
dedup_ulers_day.result <- 
  mapreduce(input = dedup_ulers_hour.result,
            map=dedup_ulers_day.map,
            reduce=dedup_ulers_day.red,
            combine=F)
footfall_ulers.result <-
  mapreduce(input = c(dedup_ulers_hour.result, dedup_ulers_day.result),
            map=footfall_ulers.map,
            reduce=footfall_ulers.red,
            combine=F)
kk <- from.dfs(footfall_ulers.result)
kk$val[kk$val$footfall > 1, ]
#Aqui esta la potencia
# el 2 de octubre de 2012, en tal celda hubo 2 personas. Ademas, a las 18horas
q()
