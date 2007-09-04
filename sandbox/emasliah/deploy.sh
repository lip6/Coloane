#! /bin/sh
if [ $# -ne 3 ]; then
	echo "Il faut trois arguments : le nom du jar, l'implementationTitle et le buildNumber"
	exit 1
fi

jar=$1
implementationTitle=$2
rev=$3

if [ ! -f target/$jar ]; then 
	echo "Le jar n'existe pas"
	exit 2 
fi

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "Le MANIFEST n'existe pas"
	exit 3
fi

bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
#implementationTitle=`grep ^Implementation-Title: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
#rev=`echo $jar | grep -o -E r[0-9]+`
newjar=`echo $implementationTitle-$bundleVersion.r$rev.jar`

echo "scp target/$jar izanami:/home/emasliah/ftp/coloane/updates/plugins/$newjar"
`scp target/$jar izanami:/home/emasliah/ftp/coloane/updates/plugins/$newjar`
