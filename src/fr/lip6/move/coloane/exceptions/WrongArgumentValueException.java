package fr.lip6.move.coloane.exceptions;

/**
 * Exception levee lorsqu'on construit un objet avec un mauvais argument entier passe en parametre
 * @author DQS equipe 2 (Styx)
 */
public class WrongArgumentValueException extends Exception {

	/**
	 * Identifie l'exception pour la serialization
	 */
	static final long serialVersionUID = 7092611880189329093L;
	
	/**
	 * Construceur 
	 * @param arg message d'erreur supplementaire
	 */
	public WrongArgumentValueException(String arg) {
		super(arg);
	}

}
