package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;

/**
 * Cette interface définie l'événement observable: déconnexion ordonnée.
 *
 * @author Monir CHAOUKI
 */
public interface IDisconnectObservable {
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : deconnexion ordonnee
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IDisconnectObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : deconnexion ordonnee
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IDisconnectObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : deconnexion ordonnee
	 */
	void notifyObservers();

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
