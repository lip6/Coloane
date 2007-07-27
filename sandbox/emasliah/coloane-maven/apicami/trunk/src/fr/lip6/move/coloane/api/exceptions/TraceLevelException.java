package fr.lip6.move.coloane.api.exceptions;

/**
 * Cette classe définit une exception pour les
 * niveaux de trace
 * 
 * */

public class TraceLevelException extends Exception {

	/** 
	 * Identifie l'exception pour la serialisation
	 */
	static final long serialVersionUID = 1L;
	
	/** 
	 * Constructeur
	 * @param msg message d'erreur supplementaire
	 */
	public TraceLevelException(String msg){
		super(msg);
	}
}
