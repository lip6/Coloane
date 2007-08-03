package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

public class SousResultsCom {
	
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
	public SousResultsCom() {
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

	public Vector<String> getCmdRO() {
		return cmdRO;
	}

}
