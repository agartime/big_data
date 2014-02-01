#!/bin/sh
FILE=fichero-prueba.txt
USERNAME=cloudera
SHORT_HOSTNAME=localhost
API_PORT=50070
NAMENODE_PORT=8020
TMPLOG=curl.tmp
API_BASE_URL="http://$SHORT_HOSTNAME:$API_PORT/webhdfs/v1"
PUT_URL=$API_BASE_URL"/user/$USERNAME/$FILE?op=CREATE&user.name=$USERNAME"
echo "Sending PUT request to "$PUT_URL
echo ""
echo ""
curl -i -X PUT -o $TMPLOG $PUT_URL
cat $TMPLOG
echo ""
echo ""
echo ""
echo "Based on the Location field returned in the 1st request, we use it to PUT our file ($FILE)"
LOCATION=`cat $TMPLOG | grep Location | awk '{print $2}'`
rm $TMPLOG
echo "Location returned was: "$LOCATION
echo ""
echo ""
echo ""
echo "Using location, we send a new PUT request containing the file to: "$LOCATION
curl -i -X PUT -T $FILE $LOCATION
echo ""
echo ""
echo ""
READ_URL=$API_BASE_URL"/user/$USERNAME/$FILE?op=OPEN"
echo "Reading the file sending a GET request to: "$READ_URL
curl -i -L $READ_URL
echo ""
echo ""
echo ""
NEW_DIR=test_dir
MKDIR_URL="$API_BASE_URL/user/$USERNAME/$NEW_DIR?op=MKDIRS&user.name=$USERNAME"
echo "Creating a directory, PUT to: "$MKDIR_URL
curl -i -X PUT $MKDIR_URL
echo ""
echo ""
echo ""
LIST_URL=$API_BASE_URL"/user/"$USERNAME"/"$NEW_DIR"?op=LISTSTATUS"
echo "Listing a directory contents. GET to: "$LIST_URL
curl -i $LIST_URL
echo ""
echo ""
echo ""
DELETE_URL=$API_BASE_URL"/user/"$USERNAME"/"$NEW_DIR"?op=DELETE&user.name="$USERNAME
echo "Deleting directory requesting a DELETE on: "$DELETE_URL
curl -i -X DELETE $DELETE_URL
echo ""
echo ""
echo ""
METADATA_URL=$API_BASE_URL"/user/"$USERNAME"/"$FILE"?op=GETFILESTATUS"
echo "Getting metadata for file ($FILE). GET to: "$METADATA_URL
curl -i $METADATA_URL
echo ""
echo ""
echo ""
CHECKSUM_URL=$API_BASE_URL"/user/"$USERNAME"/"$FILE"?op=GETFILECHECKSUM"
echo "GETting checksum in: "$CHECKSUM_URL
curl -i -L $CHECKSUM_URL
echo ""
echo "End."
