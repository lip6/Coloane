package fr.lip6.move.coloane.interfaces.api.evenements;

/**
 * Cette interface définie l'objet message à envoyer aux observateurs d'événements: réception de messages.
 */
public interface IReceptMessage {

	int ADMINISTRATOR_MESSAGE = 1;
	int WARNING_MESSAGE = 2;
	int COPYRIGHT_MESSAGE = 3;
	int TRACE_MESSAGE = 4;
	int ERROR_MESSAGE = 5;


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
