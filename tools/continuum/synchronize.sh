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
rsync -avz -r -C -S -e ssh $night/* continuum@coloane.lip6.fr:$targetnight

# For updates...
update=$source"/updates"
targetupdate=$dest"/updates"
rsync -avz -r -C -S -e ssh $update/* continuum@coloane.lip6.fr:$targetupdate

# For night-updates...
reports=$source"/reports"
targetreports=$dest"/reports"
rsync -avz -r -C -S -e ssh $reports/* continuum@coloane.lip6.fr:$targetreports
