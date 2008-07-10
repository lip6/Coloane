package fr.lip6.move.coloane.interfaces.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.objects.menu.IMenu;
import fr.lip6.move.coloane.interfaces.api.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

public interface IReceptMenuObservable {
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'un menu
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IReceptMenuObserver o);
	
	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'un menu
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IReceptMenuObserver o);
	
	/**
	 * Notifie tous les observateurs de l'evenement : reception d'un menu
	 */
	public void notifyObservers(ArrayList<IMenu> menus, ArrayList<IUpdateMenu> updateMenus);
	
	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
