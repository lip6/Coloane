package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

/**
 * Définition d'un message en provenance de FrameKit
 */
public class ReceptMessage implements IReceptMessage {
	/** Le type du message */
	private int type;

	/** Le contenu du message */
	private String message;

	/**
	 * Constructeur
	 * @param type Le type du message
	 * @param message Le message en question
	 */
	public ReceptMessage(int type, String message) {
		this.type = type;
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getTypeMessage() {
		return this.type;
	}
}
