#! /bin/sh
> hop.txt
rm -f erreur.txt
while read ligne
do
	if echo $ligne | grep -E '^~[^~]*$' > /dev/null
	then
		fic=./`echo $ligne | sed -e 's/~//'`
		echo ~~~~~~~~~$fic~~~~~~~~~ >> hop.txt
	else
		echo -n '="'"$ligne"'"'' ' >> hop.txt
		find $fic -name '*.java' -exec grep -Hn -E '".*'"$ligne"'.*"' {} \; >> hop.txt
	fi
done < LNG.properties

