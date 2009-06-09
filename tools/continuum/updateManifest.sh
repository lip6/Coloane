#!/bin/sh

#
# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr)
#

# Check parameters
if [ $# -ne 2 ]; then
	echo "FAILURE: This script needs the build number and the project name"
	exit 1
fi

projectversion=$1
buildnumber=$2

# Check the type of production
echo "Project Version: $projectversion" | grep "SNAPSHOT"
if [ $? -ne 0 ]; then
	echo "This project is a release version. No MANIFEST rewriting"
	echo "Checking that there is no build revision number"
	exit 0
fi


if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

# Clean up last modifications of MANIFEST.MF
if [ -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi

echo "-----------------------------"
echo "Updating Manifest File..."
echo "-----------------------------"
echo ">>> Current Manifest File"
echo "-----------------------------"
cat META-INF/MANIFEST.MF
echo "-----------------------------"

# Change the project's version
echo ">>> Writing a new Manifest file !"
echo "-----------------------------"
bundleversion=`grep Bundle-Version META-INF/MANIFEST.MF | awk -F ' ' '{print $2}' | tr -d "\r"`
perl -i -pe 's/^Bundle-Version: ([^\s]*)/Bundle-Version: $1.r'$buildnumber'/' META-INF/MANIFEST.MF
echo "Updated Manifest File"
cat META-INF/MANIFEST.MF
echo "-----------------------------"

if [ -f META-INF/SOURCE_MANIFEST.MF ]; then
	echo "Source Manifest detected !"
	echo "-----------------------------"
	echo "Updating Source Manifest File..."
	echo "-----------------------------"
	echo ">>> Current Manifest File"
	echo "-----------------------------"
	cat META-INF/SOURCE_MANIFEST.MF
	echo "-----------------------------"
	perl -i -pe 's/^Bundle-Version: (.*)$/Bundle-Version: '$bundleversion'.r'$buildnumber'/' META-INF/SOURCE_MANIFEST.MF
	perl -i -pe 's/^Eclipse-SourceBundle: (.*)$/Eclipse-SourceBundle: %pluginName;version="'$bundleversion'.r'$buildnumber'"/' META-INF/SOURCE_MANIFEST.MF
	echo "Updated Manifest File"
	cat META-INF/SOURCE_MANIFEST.MF
	echo "-----------------------------"
fi

echo "Updating Manifest File complete..."
