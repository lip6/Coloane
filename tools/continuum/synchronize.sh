#!/bin/sh

#
# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr)
#

source=$1
dest=$2

# For night-updates...
night=$1"/night-updates"
targetnight=$2"/night-updates"
rsync -avz -r -C -S -e ssh $night/* continuum@coloane.lip6.fr:$targetnight

# For updates...
update=$1"/updates"
targetupdate=$2"/updates"
rsync -avz -r -C -S -e ssh $update/* continuum@coloane.lip6.fr:$targetupdate

# For night-updates...
reports=$1"/reports"
targetreports=$2"/reports"
rsync -avz -r -C -S -e ssh $reports/* continuum@coloane.lip6.fr:$targetreports
