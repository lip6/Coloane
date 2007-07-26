#! /bin/sh
if [ $# -ne 1 ]
then
	echo "Il faut un parametre : le dossier des plugins"
	exit 1
fi
LIB=$1
CORE=`pwd`
JO=javac_options
echo -n "-classpath ." > $JO
# Pour lib
if [ -d $LIB ]
then
	cd $LIB
	for fic in `ls`
	do
		echo -n ":"$LIB/$fic
	done >> $CORE/$JO
fi
