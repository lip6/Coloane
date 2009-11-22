#! /bin/sh 

# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr) 

# Le but de ce script est de modifier le nom du jar puis ensuite de le deployer au bon endroit

# Dans le pom sont mentionnés les trois arguments de la commande:
#  -${project.version} le numero de version du projet
#  -${build.finalName}.${packaging} :le nom du jar final
#  -/coloane/public le chemin du dossier public

# Controle des arguments
if [ $# -ne 3 ]; then
	echo "FAILURE: This script needs 3 arguments : <version of project>, <jar's final name> and <public directory>"
	exit 0
fi

# Recuperation des arguments
version=$1
jar=$2
directory=$3

# Recherche du JAR
if [ ! -f target/$jar ]; then 
	echo "FAILURE: Jar file does not exist !"
	exit 0 
fi

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

# Recupere le nom du projet dans le MANIFEST
echo "Fetching information..."
bundleSymbolicName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
if [ `echo $bundleSymbolicName | grep ";"` ]; then
	bundleSymbolicName=`echo $bundleSymbolicName | awk -F ';' '{print $1}'`
fi

# Recupere la version du projet dans le MANIFEST
bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Calcule le nouveau nom du JAR
newjar=`echo $bundleSymbolicName\_$bundleVersion.jar`

# Calcul des chemins en fonction SNAPSHOT / RELEASE
if [ `echo $version | grep "SNAPSHOT"` ]; then
	branch=`echo night-updates`
elif [ `echo $version | grep "INCUBATION"` ]; then
	branch=`echo incubation-updates`
else
	branch=`echo updates`
fi

# Determine le chemin complet final pour deposer le nouveau JAR
if [ `echo $newjar | grep "feature"` ]; then
	directory=`echo $directory/$branch/features`
else
	directory=`echo $directory/$branch/plugins`
fi

# Copie...
cp target/$jar $directory/$newjar
echo "Copy $jar into $directory/$newjar"

# Calcul des noms des JAR contenant les sources (s'ils existent)
echo "Try to find sources..."
if [ -n "`ls target/*sources.jar`" ]; then 
	sourcesjar=`ls target/*sources.jar`
	newsourcesjar=`echo $bundleSymbolicName\.source\_$bundleVersion.jar`
	cp $sourcesjar $directory/$newsourcesjar
	echo "Copy $sourcesjar into $directory/$newsourcesjar"
else
	echo "No source found"
fi
