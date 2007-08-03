package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;




/**
 * Interface fournie par l'interface a Coloane pour la transmission de
 * resultats.
 */
public interface IResultsCom {

	/**
	 * Accede a la commande RQ
	 * 
	 * @return String la commande RQ
	 */
	public String getCmdRQ();
	
	/**
	 * Modifie la commande RQ
	 * 
	 * @param cmdRQ
	 *            la commande à modifier
	 */
	public void setcmdRQ(String cmd);
	

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
	 *            la commande à enlever
	 */	
	public void removeResultats(SousResultsCom cmd);
	
	
	/**
	 * Accede au sous resultat compris entre DE et FE
	 * 
	 * @return Vector<SousResultsCom> la liste de sous resultats compris entre
	 *         DE et FE
	 */
	public Vector<SousResultsCom> getSous_resultats();
	
	
}
