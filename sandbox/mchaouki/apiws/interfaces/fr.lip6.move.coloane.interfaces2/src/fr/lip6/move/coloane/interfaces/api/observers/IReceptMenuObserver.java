package fr.lip6.move.coloane.interfaces.api.observers;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.objects.menu.IMenu;
import fr.lip6.move.coloane.interfaces.api.objects.menu.IUpdateMenu;

public interface IReceptMenuObserver {
	
	/**
	 * Met a jour l'observateur d'evenement : reception de menus
	 * @param menus la liste des menus recus
	 * @param updateMenus la liste des modifications sur les menus 
	 */
	public void update(ArrayList<IMenu> menus, ArrayList<IUpdateMenu> updateMenus);
}
