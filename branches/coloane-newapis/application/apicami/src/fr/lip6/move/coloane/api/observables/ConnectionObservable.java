package fr.lip6.move.coloane.api.observables;

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
	/** Liste des observeurs */
	private Map< String, Object> synchro;

	/** Les informations de connexion */
	private IConnectionInfo connectionInfo;

	/**
	 * Constructeur
	 * @param hashObservers La table qui contient les observers et sur laquelle on se synchronise
	 */
	public ConnectionObservable(Map< String, Object> hashObservers) {
		synchro = hashObservers;
	}

	/**
	 * @return les informations de connexion
	 */
	public final IConnectionInfo getConnectionInfo() {
		return this.connectionInfo;
	}

	/**
	 * Notifier tous les observers
	 * @param infos Les informations récupérées lors de l'ouverture de connexion
	 */
	public final void notifyObservers(IConnectionInfo infos) {
		this.connectionInfo = infos;
		synchronized (synchro) {
			synchro.notify();
		}
	}
}
