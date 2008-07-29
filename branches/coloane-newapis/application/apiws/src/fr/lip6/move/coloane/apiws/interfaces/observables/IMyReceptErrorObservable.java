package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.observer.IMyReceptErrorObserver;

/**
 * Cette interface définie l'observable d'événement: récéption d'une 'FATAL ERROR' de la part du wrapper
 */
public interface IMyReceptErrorObservable {
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : récéption d'une 'FATAL ERROR' de la part du wrapper
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IMyReceptErrorObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : récéption d'une 'FATAL ERROR' de la part du wrapper
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IMyReceptErrorObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un menu
	 * @param e l'objet qui represent les menus ou les modifications a appliquer sur les menus
	 */
	void notifyObservers(String e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
