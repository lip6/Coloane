package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

import java.util.logging.Logger;

/**
 * Cette classe représent l'objet (qui définie un message) à envoyer aux observateurs d'événements: réception de messages.
 *
 * @author Monir CHAOUKI
 */
public class ReceptMessage implements IReceptMessage {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private int type;

	private String message;

	/**
	 * Constructeur du message à envoyer aux observateurs.
	 * @param type Le type du message.
	 * @param message Le contenu du message reçu.
	 */
	public ReceptMessage(int type, String message) {
		LOGGER.finest("Construction d'un message pour le core");
		this.type = type;
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getTypeMessage() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return message;
	}

}
