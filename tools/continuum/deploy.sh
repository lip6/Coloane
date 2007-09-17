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

# # Modifie la version du projet
# echo "Writing a new Manifest file !"
# sed "/^Bundle-Version:/ s/:.*/: $bundleVersion.r$rev/" META-INF/MANIFEST.MF > META-INF/MANIFEST.MF

# Affichage indicatif
newjar=`echo $bundleSymbolicName\_$bundleVersion.r$rev.jar`

# Copie...
#echo "scp target/$jar coloane.rsr.lip6.fr:$nightUpdatesPlugins/$newjar"
`scp target/$jar coloane.rsr.lip6.fr:$directory/$newjar`

echo $bundleVersion.r$rev > last_$bundleSymbolicName
`scp last_$bundleSymbolicName coloane.rsr.lip6.fr:$nightUpdatesPlugins`
rm -f last_$bundleSymbolicName

