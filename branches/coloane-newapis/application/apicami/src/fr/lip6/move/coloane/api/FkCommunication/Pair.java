package fr.lip6.move.coloane.api.FkCommunication;


/**
 * Définition d'une paire d'objets communicants
 *
 * @param <T1> Le listener
 * @param <T2> Le speaker
 */
public class Pair<T1, T2> {
	/** L'objet speaker */
	private T1 speaker;

	/** L'objet listener */
	private T2 listener;

	/**
	 * Enregistre le listener
	 * @param listener Le listener
	 */
	public final void setListener(T2 listener) {
		this.listener = listener;
	}

	/**
	 * Enregistre le speaker
	 * @param speaker Le speaker
	 */
	public final void setSpeaker(T1 speaker) {
		this.speaker = speaker;
	}

	/**
	 * @return le listener
	 */
	public final T2 getListener() {
		return this.listener;
	}

	/**
	 * @return le speaker
	 */
	public final T1 getSpeaker() {
		return this.speaker;
	}
}
