package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

/**
 * Interface fournie par l'interface a Coloane 
 * pour la transmissions de resultats.
 */
public interface IResultsCom {

	/**
	 * Retourne la liste des elements de resultats
	 * @return La liste des elements de resultats
	 */
	public Vector<String> getListOfElement();
	
	/**
	 * Retourne la liste des descriptions de resultats
	 * @return La liste des descriptions de resutlats
	 */
	public Vector<String> getListOfDescription();

}
