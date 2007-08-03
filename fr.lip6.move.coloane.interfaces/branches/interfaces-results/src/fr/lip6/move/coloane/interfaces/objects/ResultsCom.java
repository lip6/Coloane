package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

/**
 * Cette classe defini la listes des resultats renvoyes par la plate-forme a la
 * suite d'un appel de service. Ces resultats doivent etre envoyes a Coloane
 * pour affichage.
 * 
 */

public class ResultsCom implements IResultsCom {

	/** Commande RQ (reponse a une question) */
	private String cmdRQ;

	/** Liste des commandes comprises entre DE et FE */
	private Vector<SousResultsCom> sous_resultats;

	/**
	 * Construit un objet Resultat à transmettre à Coloane
	 * 
	 */
	public ResultsCom() {
		sous_resultats = new Vector<SousResultsCom>();
		cmdRQ = new String();
	}

	/**
	 * Ajoute une liste de sous resultats DE et FE dans la liste des
	 * sous_resulats
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addResultats(SousResultsCom cmd) {
		sous_resultats.add(cmd);
	}

	/**
	 * Enleve un element de la liste des sous resultats
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */

	public void removeResultats(SousResultsCom cmd) {
		sous_resultats.remove(cmd);
	}

	/**
	 * Accede a la commande RQ
	 * 
	 * @return String la commande RQ
	 */
	public String getCmdRQ() {
		return cmdRQ;
	}

	/**
	 * Modifie la commande RQ
	 * 
	 * @param cmdRQ
	 *            la commande à modifier
	 */
	public void setcmdRQ(String cmdRQ) {
		this.cmdRQ = cmdRQ;
	}

	/**
	 * Accede au sous resultat compris entre DE et FE
	 * 
	 * @return Vector<SousResultsCom> la liste de sous resultats compris entre
	 *         DE et FE
	 */
	public Vector<SousResultsCom> getSous_resultats() {
		return sous_resultats;
	}

}
