package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.apicami.ApiConnection;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;

import java.util.Map;

/**
 * Observable d'une déconnexion de la plate-forme
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ConnectionObservable {
	/** Le point de synchronisation */
	private Map< String, Object> hashObservers;

	/** L'observer */
	private ApiConnection apiConnection;

	/**
	 * Constructeur
	 * @param hashObservers Le point de synchronisation
	 */
	public ConnectionObservable(Map< String, Object> hashObservers) {
		this.hashObservers = hashObservers;
	}

	/**
	 * Permet l'enregistrement de l'API
	 * @param api L'objet qui doit être prévenu a la fin de la connexion
	 */
	public final void registerApiConnection(ApiConnection api) {
		this.apiConnection = api;
	}

	/**
	 * Notifier tous les observers
	 * @param infos Les informations récupérées lors de l'ouverture de connexion
	 */
	public final void notifyObservers(IConnectionInfo infos) {
		synchronized (hashObservers) {
			apiConnection.notifyEndOpenConnection(infos);
		}
	}
}
