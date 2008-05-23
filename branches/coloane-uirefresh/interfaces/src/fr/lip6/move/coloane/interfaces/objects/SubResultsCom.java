package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

/**
 * Ensemble des commande comprises entre un DE et un FE
 * Ces commandes constituent un sous-ensemble de resultats
 */

public class SubResultsCom {

	/** Liste des commandes RT (textual_result) */
	private Vector<String> cmdRT;

	/** Liste des commandes RO (object_designation) */
	private Vector<String> cmdRO;

	/** Liste des commandes ME (object_outline) */
	private Vector<String> cmdME;

	/** Liste des commandes MT (attribute_outline) */
	private Vector<String> cmdMT;

	/** Liste des commandes XA (change_attribute) */
	private Vector<Vector<String>> cmdXA;

	/** Un texte de details pour ce sous-resultat */
	private String details;

	/**
	 * Constructeur
	 */
	public SubResultsCom(String d) {
		details = d;
		cmdRT = new Vector<String>();
		cmdRO = new Vector<String>();
		cmdME = new Vector<String>();
		cmdMT = new Vector<String>();
		cmdXA = new Vector<Vector<String>>();
	}

	/**
	 * Ajoute une cmd RT dans la liste de commandes RT
	 * @param rt la commande a ajouter
	 */
	public final void addCmdRT(String rt) {
		cmdRT.add(rt);
	}

	/**
	 * Ajoute une cmd RO dans la liste de commandes RO
	 * @param ro la commande a ajouter
	 */
	public final void addCmdRO(String ro) {
		cmdRO.add(ro);
	}

	/**
	 * Ajoute une cmd ME dans la liste de commandes ME
	 * @param me la commande a ajouter
	 */
	public final void addCmdME(String me) {
		cmdME.add(me);
	}

	/**
	 * Ajoute une cmd MT dans la liste de commandes MT
	 * @param mt la commande a ajouter
	 */

	public final void addCmdMT(String mt) {
		cmdMT.add(mt);
	}

	/**
	 * Ajoute une cmd XA dans la liste de commandes XA
	 * @param xa la commande a ajouter
	 */

	public final void addCmdXA(String id, String name, String value) {
		Vector<String> details = new Vector<String>();
		details.add(id);
		details.add(name);
		details.add(value);
		cmdXA.add(details);
	}

	/**
	 * Supprime la commande RT dans la liste des commandes RT
	 * @param rt la commande a supprimer
	 */
	public final void removeCmdRT(String rt) {
		cmdRT.remove(rt);
	}

	/**
	 * Supprime la commande RO dans la liste des commandes RO
	 * @param ro la commande a supprimer
	 */

	public final void removeCmdRO(String ro) {
		cmdRO.remove(ro);
	}

	/**
	 * Supprime la commande ME dans la liste des commandes ME
	 * @param me la commande a supprimer
	 */
	public final void removeCmdME(String me) {
		cmdME.remove(me);
	}

	/**
	 * Supprime la commande MT de la liste des commandes MT
	 * @param mt la commande a supprimer
	 */
	public final void removeCmdMT(String mt) {
		cmdMT.add(mt);
	}

	/**
	 * Retourne la liste des commandes RO enregistrees
	 * @return Vector de commande RO
	 */
	public final Vector<String> getCmdRO() {
		return cmdRO;
	}

	/**
	 * Retourne la liste des commandes ME enregistrees
	 * @return Vector de commande ME
	 */
	public final Vector<String> getCmdME() {
		return cmdME;
	}

	/**
	 * Retourne la liste des commandes XA enregistrees
	 * @return Vector de commande XA
	 */
	public final Vector<Vector<String>> getCmdXA() {
		return cmdXA;
	}

	/**
	 * Retourne la liste des commandes RT enregistrees
	 * @return Vector de commande RO
	 */
	public final Vector<String> getCmdRT() {
		return cmdRT;
	}

	/**
	 * Indique directement une liste de commandes RT
	 * @param rtList La liste de commandes RT
	 */
	public final void setCmdRT(Vector<String> rtList) {
		this.cmdRT = rtList;
	}

	/**
	 * Retourne le texte de details attache au sous-resultat
	 * @return Le texte
	 */
	public final String getDetails() {
		return this.details;
	}
}
