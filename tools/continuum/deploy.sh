#! /bin/sh 

# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr) 

# Le but de ce script est de modifier le nom du jar puis ensuite de le deployer au bon endroit
# Le script ne s'execute que lorsque la commande mvn deploy est lancé sur le pom pere.

# Dans le pom sont mentionnés les trois arguments de la commande:
#  -${project.version} le numero de version du projet
#  -${build.finalName}.${packaging} :le nom du jar
#  -${buildNumber} le numero attribué par le buildnumber numero de revision
#  -/coloane/public le chemin du dossier public

# Controle des arguments
if [ $# -ne 4 ]; then
	echo "FAILURE: This script needs 4 arguments : <version of project>, <jar's final name>, <build number> and <public directory>"
	exit 1
fi

# Recuperation des arguments
version=$1
jar=$2
rev=$3
directory=$4

if [ ! -f target/$jar ]; then 
	echo "FAILURE: Jar file does not exist !"
	exit 0 
fi

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

# Recupere le nom du projet
echo "Fetching information..."

# BundleSymbolicName contiendra le deuxieme champs de la ligne contenant "Bundle-SymbolicName:..."
bundleSymbolicName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Pour la condition suivante on teste quand meme si il y a un ";" en plus et on l'enleve si necessaire
if [ `echo $bundleSymbolicName | grep ";"` ]; then
	bundleSymbolicName=`echo $bundleSymbolicName | awk -F ';' '{print $1}'`
fi

# Recupere la version du projet
# On recupere la version qu'il y a dans le manifest,on aura quelque chose du genre x.x.x.rxxxx
bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Affichage indicatif
newjar=`echo $bundleSymbolicName\_$bundleVersion.jar`

# Calcul des chemins
if [ `echo $version | grep "SNAPSHOT"` ]; then
	branch=`echo night-updates`
else
	branch=`echo updates`
fi

directory=`echo $directory/$branch/plugins`

# Copie...
cp target/$jar $directory/$newjar

echo $bundleVersion > last_$bundleSymbolicName
cp last_$bundleSymbolicName $directory
rm -f last_$bundleSymbolicName

# Nettoyage
if [ -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi
