#!/bin/sh

#
# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr)
#

source=$1
dest=$2

# For night-updates...
night=$source"/night-updates"
targetnight=$dest"/night-updates"
if [ -d $source ]; then
	rsync -avz -r -C -S -e ssh $night/* coloane@coloane.lip6.fr:$targetnight
fi

# For updates...
update=$source"/updates"
targetupdate=$dest"/updates"
if [ -d $update ]; then
	rsync -avz -r -C -S -e ssh $update/* coloane@coloane.lip6.fr:$targetupdate
fi

# For night-updates...
reports=$source"/reports"
targetreports=$dest"/reports"
if [ -d $reports ]; then
	rsync -avz -r -C -S -e ssh $reports/* coloane@coloane.lip6.fr:$targetreports
fi
