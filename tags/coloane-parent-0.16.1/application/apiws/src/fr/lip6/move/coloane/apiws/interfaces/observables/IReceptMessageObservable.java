package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

/**
 * Cette interface définie l'événement observable: récéption d'un message.
 *
 * @author Monir CHAOUKI
 */
public interface IReceptMessageObservable {

	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'un message
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IReceptMessageObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'un message
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IReceptMessageObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un message
	 * @param e l'objet qui represent un message
	 */
	void notifyObservers(IReceptMessage e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
