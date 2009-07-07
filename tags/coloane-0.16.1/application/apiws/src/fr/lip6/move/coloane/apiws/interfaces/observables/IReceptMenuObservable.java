package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

/**
 * Cette interface définie l'événement observable: récéption d'un menu.
 *
 * @author Monir CHAOUKI
 */
public interface IReceptMenuObservable {
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'un menu
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IReceptMenuObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'un menu
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IReceptMenuObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un menu
	 * @param e l'objet qui represent les menus ou les modifications a appliquer sur les menus
	 */
	void notifyObservers(IReceptMenu e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
