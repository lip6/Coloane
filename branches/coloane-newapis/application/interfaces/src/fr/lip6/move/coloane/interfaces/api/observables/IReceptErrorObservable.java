package fr.lip6.move.coloane.interfaces.api.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptError;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptErrorObserver;

public interface IReceptErrorObservable {
	
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'une erreur
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IReceptErrorObserver o);
	
	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement :  reception d'une erreur
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IReceptErrorObserver o);
	
	/**
	 * Notifie tous les observateurs de l'evenement :  reception d'une erreur
	 * @param e l'objet qui represent l'erreur
	 */
	public void notifyObservers(IReceptError e);
	
	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}