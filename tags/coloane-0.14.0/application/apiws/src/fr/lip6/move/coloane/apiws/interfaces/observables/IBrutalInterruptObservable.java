package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

/**
 * Cette interface définie l'événement observable: récéption d'une erreur.
 *
 * @author Monir CHAOUKI
 */
public interface IBrutalInterruptObservable {
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'une erreur
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IBrutalInterruptObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement :  reception d'une erreur
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IBrutalInterruptObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement :  reception d'une erreur
	 * @param e l'objet qui represent l'erreur
	 */
	void notifyObservers(String e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
