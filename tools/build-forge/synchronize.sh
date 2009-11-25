#!/bin/sh

#
# Synchornise le répertoire de build de continuum avec le répertoire public de apache
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
#	rsync -avz -r -C -S $night/* $targetnight
fi

# For incubation-updates...
incubation=$source"/incubation-updates"
targetincubation=$dest"/incubation-updates"
if [ -d $source ]; then
	rsync -avz -r -C -S -e ssh $incubation/* coloane@coloane.lip6.fr:$targetincubation
#	rsync -avz -r -C -S $night/* $targetnight
fi


# For updates...
update=$source"/updates"
targetupdate=$dest"/updates"
# Check wether there are some files to sync
status=`ls -l $update | head -n 1 | cut -d " " -f 2`
if [ -d $update -a  $status -ne 0 ]; then
	rsync -avz -r -C -S -e ssh $update/* coloane@coloane.lip6.fr:$targetupdate
#	rsync -avz -r -C -S $update/* $targetupdate
fi

# For reports...
reports=$source"/reports"
targetreports=$dest"/reports"
status=`ls -l $reports | head -n 1 | cut -d " " -f 2`
if [ -d $reports -a  $status -ne 0 ]; then
	rsync -avz -r -C -S -e ssh $reports/* coloane@coloane.lip6.fr:$targetreports
#	rsync -avz -r -C -S $reports/* $targetreports
fi
