#le but de ce script est de modifier le nom du jar puis ensuite de le deplyer dans night-updates
#deploy.sh ne s'execute que lorsque la commande mvn deploy est lancé sur le pom pere.
#Dans le pom sont mentionnés les trois arguments de la commande:
#-${build.finalName}.${packaging} :le nom du jar
#-${buildNumber} le numero attribué par le buildnumber numero de revision
#-/coloane/public/night-updates/plugins le chemin du dossier des plugins


#! /bin/sh 



if [ $# -ne 3 ]; then
	echo "FAILURE: This script needs 3 arguments : Jar's name, Build number and Plugin's directory"
	exit 1
fi

jar=$1
rev=$2
directory=$3

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

#bundleSymbolicName contiendra le deuxieme champs de la ligne contenant "Bundle-SymbolicName:...",chaque champs etant separé par un espace.
#concretement pour Iinterface on va avoir :"fr.lip6.move.coloane.interfaces"
bundleSymbolicName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
#Pour la condition suivante on teste quand meme si il y a un ";" en plus et on l'enleve si necessaire
if [ `echo $bundleSymbolicName | grep ";"` ];then
	bundleSymbolicName=`echo $bundleSymbolicName | awk -F ';' '{print $1}'`
fi

# Recupere la version du projet
#on recupere la version qu'il y a dans le manifest,on aura quelque chose du genre x.x.x.rxxxx
bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Affichage indicatif
newjar=`echo $bundleSymbolicName\_$bundleVersion.jar`

# Copie...
`scp target/$jar coloane.lip6.fr:$directory/$newjar`

echo $bundleVersion > last_$bundleSymbolicName
`scp last_$bundleSymbolicName coloane.lip6.fr:$directory`
rm -f last_$bundleSymbolicName

# Nettoyage
if [ -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi
