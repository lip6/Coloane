# Coloane

Coloane is a multi-formalism editor. 
The original web page and description can be found here : https://move.lip6.fr/software/COLOANE/

It has also served as a basis for the platform Cosyverif https://www.cosyverif.org/

The latest version of Coloane plugins are generated using GitHub actions, and available from : https://lip6.github.io/Coloane/

Note that this is an eclipse update site, not a web page. Simply add to your update sites in a running eclipse to download the latest version. 

## This repository

This repository hosts parts of Coloane, but not the whole project, and in particular it does not provide the tool connectors for either 
* CPN-AMI / FrameKit (a model-checking integration platform developped at LIP6 from 1995 to 2010 roughly)
* Cosyverif connectors (a.k.a. Alligator)

Some of these features are still present in branches of this repository, but not active.

Mostly the components maintained in this repository serve the needs of ITS-Tools https://github.com/lip6/ITSTools, which has some dependencies
on Coloane for some of the graphical model editors provided.

Maintenance is minimal, basically bumping versions of dependencies, updating to more recent eclipse releases, upgrading Java version...

But feel free to use the issues page if you need some components of Coloane that are not currently enabled.

## Acknowledgements

Coloane was developped at LIP6 mostly between 2008 and 2012 by Jean-Baptiste Voron (leading) and others (check source history).

This repository is maintained by Yann Thierry-Mieg, LIP6, CNRS & Sorbonne Université.
