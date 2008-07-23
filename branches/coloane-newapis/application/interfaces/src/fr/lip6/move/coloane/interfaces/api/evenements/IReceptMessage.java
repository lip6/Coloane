package fr.lip6.move.coloane.interfaces.api.evenements;

/**
 * Cette interface définie l'objet message à envoyer aux observateurs d'événements: réception de messages.
 */
public interface IReceptMessage {

	/**
	 * Recupere le type du message recu
	 * @return le type du message recu
	 */
	int getTypeMessage();

	/**
	 * Recupere le message recu
	 * @return le message recu
	 */
	String getMessage();
}
