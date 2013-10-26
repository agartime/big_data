#!/bin/sh
flume-ng agent -n agente -c conf -f ./001_flume.conf -Dflume.root.logger=INFO,console
