#!/bin/sh

if [ $# -ne 2 ]; then
	echo "FAILURE: This script needs the build number and the project name"
	exit 1
fi

number=$1
projectname=$2

echo "Project : $projectname" | grep "SNAPSHOT"
if [ $? -ne 0 ]; then
	echo "This project is a release version. No MANIFEST rewriting"
	exit 0
fi


if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

# Nettoyage
if [ -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi

echo "-----------------------------"
echo "Updating Manifest File..."
echo "Current Manifest File"
cat META-INF/MANIFEST.MF
echo "-----------------------------"

# # Modifie la version du projet
echo "Writing a new Manifest file !"
echo "-----------------------------"
perl -i -pe 's/^Bundle-Version: ([^\s]*)/Bundle-Version: $1.r'$number'/' META-INF/MANIFEST.MF
echo "Updated Manifest File"
cat META-INF/MANIFEST.MF
echo "-----------------------------"

echo "Updating Manifest File complete..."
