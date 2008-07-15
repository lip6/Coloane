package fr.lip6.move.coloane.interfaces.api.evenements;

public interface IReceptError {

	
	/**
	 * Recupere le type du message d'erreur recu
	 * @return le type du message d'erreur recu
	 */
	public int getTypeErrorMessage();
	
	/**
	 * Recupere le message d'erreur recu
	 * @return le message d'erreur recu
	 */
	public String getErrorMessage();
}
