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
bundleSymbolicName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
if [ `echo $bundleSymbolicName | grep ";"` ];then
	bundleSymbolicName=`echo $bundleSymbolicName | awk -F ';' '{print $1}'`
fi

# Recupere la version du projet
bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

# Affichage indicatif
newjar=`echo $bundleSymbolicName\_$bundleVersion.jar`

# Copie...
`scp target/$jar coloane.rsr.lip6.fr:$directory/$newjar`

echo $bundleVersion > last_$bundleSymbolicName
`scp last_$bundleSymbolicName coloane.rsr.lip6.fr:$directory`
rm -f last_$bundleSymbolicName

# Nettoyage
if [ ! -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi
