#! /bin/sh

echo "-----------------------------"
echo "Reverting modified files"
echo "-----------------------------"

# Nettoyage
if [ -d META-INF/.svn ]; then
	echo "Suppression du MANIFEST modifie"
	svn revert META-INF/MANIFEST.MF
fi

if [ -d resources/.svn ]; then
	echo "Suppression des descripteurs XML"
	svn revert --recursive resources
fi

echo "Cleaning complete..."
