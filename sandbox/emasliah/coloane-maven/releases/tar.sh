#! /bin/sh
if [ $# -ne 2 ]
then
	echo "Il faut deux parametres : la version de core et le numero de build"
	exit 1
fi
echo $1
echo $2
CORE="$1"
BUILD="$2"
NB=`date +%F`_coloane_"$CORE"_r`printf "%04d" "$BUILD"`
echo Tar cree : $NB
cd night_builds/$BUILD
tar -cvzf ../$NB.tar.gz *
