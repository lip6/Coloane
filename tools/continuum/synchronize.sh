#!/bin/sh

#
# Auteur  : Jean-Baptiste Voron
# Contact : jean-baptiste.voron@lip6.fr
# Projet  : Coloane (http://coloane.lip6.fr)
#

source=$1
dest=$2

rsync -avz -r -C -S -e ssh $1 \
continuum@coloane.lip6.fr:$2> $1/reports/synchro.log"`date | tr " :" -`".log
