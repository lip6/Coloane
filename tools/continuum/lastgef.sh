#! /bin/sh
if [ $# -ne 3 ]; then
	echo "Il faut trois arguments : l'implementationTitle, la version de gef et le dossier des features"
	exit 1
fi

implementationTitle=$1
ver=$2
nightUpdatesFeatures=$3

echo $ver > last_$implementationTitle
echo "scp last_$implementationTitle coloane.lip6.fr:$nightUpdatesFeatures"
`scp last_$implementationTitle coloane.lip6.fr:$nightUpdatesFeatures`
rm -f last_$implementationTitle
