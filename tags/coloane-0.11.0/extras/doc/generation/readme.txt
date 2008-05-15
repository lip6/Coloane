--- README ---

Ce script permet :

	1. Generer une premiere page de garde pour un document
	2. Generer une page de garde pour une revision du document
	3. Recompiler une page de garde deja existante
	4. Remettre tous les compteurs a zero
	
1. Generation d'une nouvelle page de garde
------------------------------------------


La premiere chose a faire est de remplir le fichier < infos.dat >
5 champs sont a remplir :

	AUTEUR : Auteur du document original
	DATE : Date de la premiere version du document
	TYPE : Type du document (Rapport, Dossier, Schemas etc...)
	NBPAGES : Nombre de pages composant le document
	TITRE : Titre du document

Une fois le fichier rempli, utilisez la commande ''make rev'' depuis une console

>>> Le fichier garde.pdf est genere. C'est votre page de garde.

2. Generation d'une page de garde pour une revision
----------------------------------------------------

Il faut reconstruire une page de garde a chuaque modification du document.
C'est le fichier <rev.dat> qui est charge de conserver toutes les modifications 
du document. Ce fichier est rempli automatiquement grace a la commande :

''make rev''

Le script detectera que votre page de garde existe deja, et vous demandera de remplir
un petit formulaire pour detailler la modification que vous proposez.
A la fin de ce questionnaire, un nouveau fichier garde.pdf sera genere.

3. Regenerer une page de garde sans modification
------------------------------------------------

Pour re-generer une page de garde sans y apporter de modification, utilisez la commande

''make again''

Un novueau fichier garde.pdf sera genere.

4. Recommencer a partier de zero
--------------------------------

Pour remettre a zero les compteurs, utilisez la commande 

''make reset''

Cette commande efface le fichier contenant les revisions et re-initialise le 
fichier contenant les informations concernant le document.


