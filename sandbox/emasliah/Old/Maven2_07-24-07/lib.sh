#! /bin/sh
cd ..
COL=`pwd`
JO=javac_options
echo -n "-classpath ." > $JO
# Pour lib
cd lib
for fic in `ls`
do
	echo -n ":"./lib/$fic
done >> $COL/$JO
# Pour Interface et Apicami
cd $COL/plugins
for fic in `ls`
do
	echo -n ":"./plugins/$fic
done >> $COL/$JO
