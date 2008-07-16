package fr.lip6.move.coloane.interfaces.api.evenements;

public interface IReceptMessage {
	
	/**
	 * Recupere le type du message recu
	 * @return le type du message recu
	 */
	public int getTypeMessage();
	
	/**
	 * Recupere le message recu
	 * @return le message recu
	 */
	public String getMessage();
}
