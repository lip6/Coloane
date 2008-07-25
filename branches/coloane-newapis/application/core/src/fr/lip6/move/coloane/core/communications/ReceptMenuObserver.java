package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

/**
 * Permet d'être notifié des changements de menu
 */
public class ReceptMenuObserver implements IReceptMenuObserver {
	/** {@inheritDoc} */
	public final void update(IReceptMenu menu) {
		UserInterface.getInstance().drawMenu(menu);
		for (ISubMenu subMenu : menu.getMenus()) {
			printMenus(subMenu, "");
		}
	}

	/**
	 * Affichage d'un menu
	 * @param menu menu qui doit être affiché
	 * @param dec décalage correspondant à la profondeur actuelle
	 */
	private void printMenus(ISubMenu menu, String dec) {
		System.out.println(dec + (menu.isVisible() ? "[+]" : "[-]") + " " + "[menu ]" + " " + menu.getName());

		for (IServiceMenu service : menu.getServiceMenus()) {
			System.out.println("   " + dec + (service.isVisible() ? "[+]" : "[-]") + " " + "[service]" + " " + service.getName());
		}

		for (IOptionMenu option : menu.getOptions()) {
			System.out.println("   " + dec + (option.isVisible() ? "[+]" : "[-]") + (option.isValidated() ? " [X] " : " [ ] ") + " [option]" + " " + option.getName());
		}

		for (ISubMenu subMenu : menu.getSubMenus()) {
			printMenus(subMenu, "   " + dec);
		}
	}
}
