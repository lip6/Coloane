#! /bin/sh

if [ $# -ne 2 ]; then
	echo "FAILURE: This script needs 2 arguments : <version of project> and <public directory>"
	exit 0
fi

version=$1
directory=$2

if [ ! -f resources/site.xml ]; then 
	echo "FAILURE: site.xml file does not exist !"
	exit 0 
fi

# Calcul des chemins en fonction SNAPSHOT / RELEASE
if [ `echo $version | grep "SNAPSHOT"` ]; then
	branch=`echo night-updates`
else
	branch=`echo updates`
fi

cp resources/site.xml $directory/$branch/site.xml
