package fr.lip6.move.coloane.interfaces.objects;



/**
 * Interface fournie par l'interface a Coloane pour la transmissions de
 * resultats.
 */
public interface IResultsCom {

	/**
	 * Ajoute une cmd RQ dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdRQ(String cmd);


	/**
	 * Enleve la commande RQ correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdRQ(String cmd);


	/**
	 * Ajoute une liste de sous resultats DE et FE dans la liste des
	 * sous_resulats
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addResultats(SousResultsCom cmd);

	/**
	 * Enleve un element de la liste des sous resultats
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */

	public void removeResultats(SousResultsCom cmd);
	

}
