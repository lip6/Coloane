#! /bin/sh
while read ligne
do
	if echo $ligne | grep -E '^[^\.]' > /dev/null
	then
		nom=`echo $ligne | sed -e 's/\..*//'`
		echo $ligne
	else
		if echo $ligne | grep -E '^~~~~~~~~~\.[^ ]*~~~~~~~~~$' > /dev/null
		then
			echo $ligne
		else
			echo $nom $ligne
		fi
	fi 
done < hop.txt > res.txt
