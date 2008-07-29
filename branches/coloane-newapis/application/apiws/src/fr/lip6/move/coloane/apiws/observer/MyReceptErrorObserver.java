package fr.lip6.move.coloane.apiws.observer;

import fr.lip6.move.coloane.apiws.ApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.observer.IMyReceptErrorObserver;

/**
 * Cette classe définie un ovserveru pour l'événement: récéption d'une 'FATAL ERROR' de la part du wrapper
 */
public class MyReceptErrorObserver implements IMyReceptErrorObserver {

	private ApiConnection connection;

	/**
	 * Constructeur
	 * @param connection la connection à fermer
	 */
	public MyReceptErrorObserver(ApiConnection connection) {
		this.connection = connection;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void update(String e) {
		connection.closeConnectionError();
	}

}
