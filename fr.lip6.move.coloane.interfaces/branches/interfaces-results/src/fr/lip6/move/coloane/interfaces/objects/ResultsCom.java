package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;


/**
 * Cette classe defini la listes des reslutats renvoyes par la plate-forme
 * a la suite d'un appel de service. Ces resultats doivent etre envoyes a Coloane
 * pour affichage.
 * 
 */

public abstract class ResultsCom implements IResultsCom {

	/** Liste des commandes RQ (reponse a une question) */
	private Vector<String> cmdRQ;

	/** Liste des commandes TQ (transmission d'un etat) */
	private Vector<String> cmdTQ;

	/** Liste des commanes MO (affichage) */
	private Vector<String> cmdMO;

	/** Liste des commandes comprises entre DE et FE */
	private Vector<SousResultsCom> sous_resultats;

	/**
	 * Construit un objet Resultat à transmettre à Coloane
	 * 
	 */
	public ResultsCom() {
		sous_resultats = new Vector<SousResultsCom>();

		cmdRQ = new Vector<String>();
		cmdTQ = new Vector<String>();
		cmdMO = new Vector<String>();
	}

	/**
	 * Ajoute une cmd RQ dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdRQ(String cmd) {
		cmdRQ.add(cmd);
	}

	/**
	 * Ajoute une cmd TQ dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdTQ(String cmd) {
		cmdTQ.add(cmd);
	}

	/**
	 * Ajoute une cmd MO dans la liste des commandes RQ
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */
	public void addcmdMO(String cmd) {
		cmdMO.add(cmd);
	}

	/**
	 * Enleve la commande RQ correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdRQ(String cmd) {
		cmdRQ.remove(cmd);
	}

	/**
	 * Enleve la commande TQ correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdTQ(String cmd) {
		cmdTQ.remove(cmd);
	}

	/**
	 * Enleve la commande MO correspondante dans la liste
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removecmdMO(String cmd) {
		cmdMO.remove(cmd);
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
	 * Enleve n element de la liste des sous resultats
	 * 
	 * @param cmd
	 *            la commande à ajouter
	 */

	public void removeResultats(SousResultsCom cmd) {
		sous_resultats.remove(cmd);
	}


}
