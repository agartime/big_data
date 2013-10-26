#!/bin/sh
flume-ng agent -n agente -c conf -f ./flume.conf -Dflume.root.logger=INFO,console
