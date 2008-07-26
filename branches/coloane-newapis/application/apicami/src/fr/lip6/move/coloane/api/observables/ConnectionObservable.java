package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.api.ApiConnection;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;

/**
 * Observable d'une déconnexion de la plate-forme
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ConnectionObservable {
	/** L'unique observer */
	private ApiConnection apiConnection;


	/**
	 * Constructeur
	 * @param apiConnection Le gestionnaire de connexion qui doit être averti
	 */
	public ConnectionObservable(ApiConnection apiConnection) {
		this.apiConnection = apiConnection;
	}

	/**
	 * Notifier tous les observers
	 * @param infos Les informations récupérées lors de l'ouverture de connexion
	 */
	public final void notifyObservers(IConnectionInfo infos) {
		synchronized (apiConnection) {
			apiConnection.notifyEndOpenConnection(infos);
		}
	}
}
