package fr.lip6.move.coloane.interfaces.api.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

public interface IReceptResultObservable {
	
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'un resultat
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IReceptResultObserver o);
	
	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'un resultat
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IReceptResultObserver o);
	
	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un resultat
	 * @param e l'objet qui represent les resultats
	 */
	public void notifyObservers(IReceptResult e);
	
	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
