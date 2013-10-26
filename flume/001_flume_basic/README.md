FLUME Basic Echo Sample 
=======================

Basic Echo Sample.  
Source listens at localhost:9999. 
Sink prints to screen. 

Files:
------

flume.conf => Configuration file.  
flume_server.sh => Starts an agent listening in localhost:9999.  
flume.txt => Just a text file.  
flume_writer.sh => Sends the contents of flume.txt to localhost:9999.  

Usage: 
------

     ./flume_server.sh
     ./flume_writer.sh


