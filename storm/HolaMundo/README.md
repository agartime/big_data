Storm Sample 1 - Hello World
============================

Packaging:

      mvn package


Execution:

      mvn compile exec:java -Dexec.classpathScope=compile -Dexec.mainClass=org.agartime.utad.storm.HolaMundoTopology

Cluster:

      storm jar hola-mundo-0.0.1-SNAPSHOT.jar org.agartime.utad.storm.HolaMundoTopology

