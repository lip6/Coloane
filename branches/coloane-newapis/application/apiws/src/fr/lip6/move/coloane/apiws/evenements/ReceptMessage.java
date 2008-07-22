package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

public class ReceptMessage implements IReceptMessage {

	private int type;

	private String message;

	/**
	 * Constructeur du message à envoyer aux observateurs.
	 * @param type Le type du message.
	 * @param message Le contenu du message reçu.
	 */
	public ReceptMessage(int type, String message) {
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