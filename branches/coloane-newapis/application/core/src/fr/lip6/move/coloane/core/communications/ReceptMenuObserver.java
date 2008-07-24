package fr.lip6.move.coloane.core.communications;

import java.util.List;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;

/**
 * Permet d'être notifié des changements de menu
 */
public class ReceptMenuObserver implements IReceptMenuObserver {

	/** {@inheritDoc} */
	public final void update(IReceptMenu e) {
		displayMenu("MENU ", e.getMenus());
	}

	private void displayMenu(String s, List<IRootMenu> menus) {
		for (IRootMenu menu : menus) {
			System.out.println(menu.getName());
		}
	}

}
