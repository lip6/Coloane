package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

/**
 * Cette interface définie l'événement observable: récéption d'un résultat.
 *
 * @author Monir CHAOUKI
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
	void notifyObservers(IResult e);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
