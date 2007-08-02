package fr.lip6.move.coloane.interfaces.objects;

//import java.util.Vector;

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
	 * Ajoute une cmd TQ dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdTQ(String cmd);

	/**
	 * Ajoute une cmd MO dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdMO(String cmd);

	/**
	 * Enleve la commande RQ correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdRQ(String cmd);

	/**
	 * Enleve la commande TQ correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdTQ(String cmd);

	/**
	 * Enleve la commande MO correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdMO(String cmd);

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
