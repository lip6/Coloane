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

echo "Updating Manifest File..."

# # Modifie la version du projet
echo "Writing a new Manifest file !"
perl -i -pe 's/^Bundle-Version: ([^\s]*)/Bundle-Version: $1.'$number'/' META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF

echo "Updating Manifest File complete..."
