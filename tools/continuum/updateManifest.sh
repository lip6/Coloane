#!/bin/sh

#Ce script se lance dés qu'une commande maven se lance.En effet,ce script se lance car la generates-sources est
#lancé alors que c'est le premier element du cycle de vie maven.
#Concretement ce script,après des tests de routine (nbre arguments,manifest existant..) affiche a l'utilisateur a
#la sortie standard le manifest puis modifie le manifest present en changeant son bundleversion.
#a la base il est de la forme 0.x.0 et devient 0.x.x-rxxxx on rajoute le buildnumber a la fin(si j'ai bien compris #la ligne de commande perl


#! /bin/sh
if [ $# -ne 1 ]; then
	echo "FAILURE: This script needs the build number"
	exit 1
fi

number=$1

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
