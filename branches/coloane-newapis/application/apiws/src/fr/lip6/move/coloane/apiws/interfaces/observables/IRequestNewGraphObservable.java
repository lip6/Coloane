package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IRequestNewGraphObserver;

/**
 * Cette interface définie l'observable pour l'événement: demande un nouveua graph au core de coloane.
 */
public interface IRequestNewGraphObservable {

	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement: demande un nouveua graph au core de coloane.
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IRequestNewGraphObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement: demande un nouveua graph au core de coloane.
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IRequestNewGraphObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement: demande un nouveua graph au core de coloane.
	 * @param e le formalism du model désiré
	 */
	void notifyObservers(String e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
