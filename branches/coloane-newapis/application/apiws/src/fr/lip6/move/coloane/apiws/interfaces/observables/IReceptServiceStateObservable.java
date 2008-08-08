package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;

/**
 * Cette interface définie l'événement observable: récéption d'une information sur un service en cours d'exécution.
 *
 * @author Monir CHAOUKI
 */
public interface IReceptServiceStateObservable {

	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IReceptServiceStateObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IReceptServiceStateObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param e l'objet qui represent l'information sur le service en cours d'exécution.
	 */
	void notifyObservers(IReceptServiceState e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
