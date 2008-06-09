#!/bin/sh

#
# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr)
#

version=$1
dir=$2

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

# Recupere le nom du projet dans le MANIFEST
echo "Fetching information..."
finalName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
if [ `echo $finalName | grep ";"` ]; then
	finalName=`echo $finalName | awk -F ';' '{print $1}'`
fi

# Recupere la version du projet dans le MANIFEST
finalVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Calcul des chemins en fonction SNAPSHOT / RELEASE
if [ `echo $version | grep "SNAPSHOT"` ]; then
	branch=`echo night-updates`
else
	branch=`echo updates`
fi

echo "Generating the version file"
echo $finalVersion > last_$finalName
cp last_$finalName $dir/$branch/last_versions
rm -f last_$finalName
