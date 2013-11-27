#!/bin/sh
command -v thrift >/dev/null 2>&1 || { echo >&2 "You need to install thrift before!"; exit 1;}

echo "`date` - Generating Thrift Class..."
thrift --gen java structs.thrift
echo "`date` - Copying to com.agartime.utad.mapreduce package/folder..."
cp gen-java/com/agartime/utad/mapreduce/* ./src/main/java/com/agartime/utad/mapreduce/
echo "`date` - Erasing temp files..."
rm -f ./gen-javs
echo "Done."
