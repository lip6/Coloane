package fr.lip6.move.coloane.interfaces.api.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

/**
 * Cette interface définie l'événement observable: récéption d'un résultat.
 */
public interface IReceptResultObservable {

	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'un resultat
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IReceptResultObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'un resultat
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IReceptResultObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un resultat
	 * @param e l'objet qui represent les resultats
	 */
	void notifyObservers(IReceptResult e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
