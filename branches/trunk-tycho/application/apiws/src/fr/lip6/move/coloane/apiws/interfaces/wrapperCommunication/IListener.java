package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

/**
 * Cette interface représente un écouteur pour les messages asynchrone issuent du wrapper.
 *
 * @author Monir CHAOUKI
 */
public interface IListener {

	/**
	 * Lancer le listener
	 */
	void start();

	/**
	 * Stopper le listener
	 */
	void stopper();
}
