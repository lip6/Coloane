#! /bin/sh
> hop.txt
rm -f erreur.txt
txt=""
while read ligne
do
	if echo $ligne | grep -E '^~[^~]*$' > /dev/null
	then
			if ! echo $txt | grep -E '^ *$' > /dev/null
			then 
				echo -e $txt | sort -u
			fi
			echo -n $ligne
			txt=""
	else
		if echo $txt | grep -E '^ *$'
		then
			txt=$ligne
		else
			txt=$txt\\n$ligne
		fi
	fi
done < LNG.properties.old > LNG.properties
echo $txt | sort -u >> LNG.properties

