#! /bin/sh
if [ $# -ne 3 ]; then
	echo "Il faut 3 arguments : le nom du jar, le buildNumber et le dossier des plugins"
	exit 1
fi

jar=$1
rev=$2
nightUpdatesPlugins=$3

if [ ! -f target/$jar ]; then 
	echo "Le jar n'existe pas"
	exit 0 
fi

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "Le MANIFEST n'existe pas"
	exit 0
fi

bundleSymbolicName=`grep ^Bundle-SymbolicName: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

if [ `echo $bundleSymbolicName | grep ";"` ];then
	bundleSymbolicName=`echo $bundleSymbolicName | awk -F ';' '{print $1}'`
fi

bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`

newjar=`echo $bundleSymbolicName\_$bundleVersion.r$rev.jar`

echo "scp target/$jar continuum@izanami:$nightUpdatesPlugins/$newjar"
`scp target/$jar continuum@izanami:$nightUpdatesPlugins/$newjar`

echo $bundleVersion.r$rev > last_$bundleSymbolicName
`scp last_$bundleSymbolicName continuum@izanami:$nightUpdatesPlugins`
rm -f last_$bundleSymbolicName

