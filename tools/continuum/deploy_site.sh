#! /bin/sh

if [ $# -ne 1 ]; then
	echo "Il faut 1 arguments : le dossier du site de nigth-updates"
	exit 1
fi

nightUpdates=$1

if [ ! -f resources/site.xml ]; then 
	echo "Le fichier site.xml n'existe pas"
	exit 0 
fi

cp resources/site.xml $nightUpdates/site.xml
