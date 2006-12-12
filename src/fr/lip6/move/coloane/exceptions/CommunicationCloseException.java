package fr.lip6.move.coloane.exceptions;

/**
 * Exception levee lorsqu'on essaie de lire sur la socket alors que celle-ci est fermee
 * 
 * @author M2-SAR 2005-2006 Equipe 2
 * @author Jean-Baptiste VORON
 */
public class CommunicationCloseException extends Exception {

	/**
	 * Identifie l'exception pour la serialization
	 */
	static final long serialVersionUID = 7092611880189329095L;
	
	/**
	 * Constructeur 
	 * @param arg0 message d'erreur supplementaire
	 */
	public CommunicationCloseException(String arg) {
		super(arg);
	}

}
      