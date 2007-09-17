#! /bin/sh
if [ $# -ne 1 ]; then
	echo "FAILURE: This script needs the build number"
	exit 1
fi

number=$3

if [ ! -f META-INF/MANIFEST.MF ]; then
	echo "FAILURE: Manifest file does not exist !"
	exit 0
fi

echo "Updating Manifest File..."

# Recupere la version du projet
bundleVersion=`grep ^Bundle-Version: META-INF/MANIFEST.MF | awk -F ' ' '{ print $2}' | tr -d "\r"`
echo "Actual Bundle Version : $bundleVersion"

# # Modifie la version du projet
echo "Writing a new Manifest file !"
sed "/^Bundle-Version:/ s/:.*/: $bundleVersion.r$rev/" META-INF/MANIFEST.MF > META-INF/MANIFEST.MF

echo "Updating Manifest File complete..."
