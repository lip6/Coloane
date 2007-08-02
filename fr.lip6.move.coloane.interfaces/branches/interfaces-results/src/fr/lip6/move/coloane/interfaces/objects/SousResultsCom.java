package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

public class SousResultsCom {
	
	/** Nom de l'ensemble */
	private String nom_ensemble;

	/** Type de l'ensemble */
	private int type_ensemble;

	/** Liste des commandes RT (textual_result) */
	private Vector<String> cmdRT;

	/** Liste des commandes RO (object_designation) */
	private Vector<String> cmdRO;

	/** Liste des commandes ME (object_outline) */
	private Vector<String> cmdME;

	/** Liste des commandes WE (attirubute_change) */
	private Vector<String> cmdWE;

	/** Liste des commandes MT (atribute_outline) */
	private Vector<String> cmdMT;

	/**
	 * Le constructeur
	 * 
	 * @param nom_ensemble
	 *            le nom de l'ensemble
	 * @param type
	 *            _ensemble le type de l'ensemble
	 * 
	 */
	public SousResultsCom(String nom_ensemble, int type_ensemble) {
		this.nom_ensemble = nom_ensemble;
		this.type_ensemble = type_ensemble;
		cmdRT = new Vector<String>();
		cmdRO = new Vector<String>();
		cmdME = new Vector<String>();
		cmdMT = new Vector<String>();
		cmdWE = new Vector<String>();
		cmdMT = new Vector<String>();
	}

	/**
	 * Ajoute une cmd RT dans la liste de commandes RT
	 * 
	 * @param cmd
	 *            la commande a ajouter
	 */
	public void addCmdRT(String cmd) {
		cmdRT.add(cmd);
	}

	/**
	 * Ajoute une cmd RO dans la liste de commandes RO
	 * 
	 * @param cmd
	 *            la commande a ajouter
	 */
	public void addCmdRO(String cmd) {
		cmdRO.add(cmd);
	}

	/**
	 * Ajoute une cmd ME dans la liste de commandes ME
	 * 
	 * @param cmd
	 *            la commande a ajouter
	 */
	public void addCmdME(String cmd) {
		cmdME.add(cmd);
	}

	/**
	 * 
	 * Ajoute une cmd WE dans la liste de commandes WE
	 * 
	 * @param cmd
	 *            la commande a ajouter
	 * 
	 */

	public void addCmdWE(String cmd) {
		cmdWE.add(cmd);
	}

	/**
	 * Ajoute une cmd WE dans la liste de commandes WE
	 * 
	 * @param cmd
	 *            la commande a ajouter
	 */

	public void addCmdMT(String cmd) {
		cmdMT.add(cmd);
	}

	/**
	 * Supprime la commande RT dans la liste des commandes RT
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 * 
	 */
	public void removeCmdRT(String cmd) {
		cmdRT.remove(cmd);
	}

	/**
	 * Supprime la commande RT dans la liste des commandes RT
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 * 
	 */

	public void removeCmdRO(String cmd) {
		cmdRO.remove(cmd);
	}

	/**
	 * Supprime la commande RT dans la liste des commandes RT
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 * 
	 */
	public void removeCmdME(String cmd) {
		cmdME.remove(cmd);
	}

	/**
	 * Supprime la commande MT de la liste des commandes MT
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removeCmdMT(String cmd) {
		cmdMT.add(cmd);
	}

	/**
	 * Supprime la commande WE de la liste des commandes WE
	 * 
	 * @param cmd
	 *            la commande a supprimer
	 */
	public void removeCmdWE(String cmd) {
		cmdWE.remove(cmd);
	}

	/**
	 * Renvoie le nom de l'ensemble
	 * 
	 * @return le nom de l'ensemble
	 */
	public String getNom_ensemble() {
		return nom_ensemble;
	}

	/**
	 * Affecte un nom a l'ensemble
	 * 
	 * @param nom_ensemble
	 *            le nom a affecter
	 * 
	 */
	public void setNom_ensemble(String nom_ensemble) {
		this.nom_ensemble = nom_ensemble;
	}

	/**
	 * Renvoie le type de l'ensemble
	 * 
	 * @return le type de l'ensemble
	 */
	public int getType_ensemble() {
		return type_ensemble;
	}

	/**
	 * Affecte un type a l'ensemble
	 * 
	 * @param type_ensemble
	 *            le nom a affecter
	 * 
	 */
	public void setType_ensemble(int type_ensemble) {
		this.type_ensemble = type_ensemble;
	}
}
