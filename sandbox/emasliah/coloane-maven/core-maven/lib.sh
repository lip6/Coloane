#! /bin/sh
if [ $# -ne 1 ]
then
	echo "Il faut un parametre : le dossier des plugins"
	exit 1
fi
cd .. 
LIB=$1
SP=`pwd` # Le super pom doit etre dans ce dossier
JO=javac_options
echo -n "-classpath ." > $JO
# Pour lib
if [ -d $LIB ]
then
	cd $LIB
	for fic in `ls`
	do
		echo -n ":"$LIB/$fic
	done >> $SP/$JO
fi
# Pour Interface et Apicami
if [ -d $SP/plugins ]
then
	cd $SP/plugins
	for fic in `ls`
	do
		echo -n ":"./plugins/$fic
	done >> $SP/$JO
fi
