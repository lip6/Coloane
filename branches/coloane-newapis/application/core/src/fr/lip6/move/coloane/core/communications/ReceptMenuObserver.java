package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * Permet d'être notifié des changements de menu
 */
public class ReceptMenuObserver implements IReceptMenuObserver {
	/** {@inheritDoc} */
	public final void update(IReceptMenu menu) {
		UserInterface.getInstance().drawMenus(menu.getMenus());

		// Affichage du menu dans la console pour le debug
//		for (ISubMenu subMenu : menu.getMenus()) {
//			Map<String, IUpdateMenu> mapUpdateMenu = new HashMap<String, IUpdateMenu>();
//			for (IUpdateMenu element : menu.getUpdateMenus()) {
//				mapUpdateMenu.put(element.getServiceName(), element);
//			}
//			printMenus(subMenu, mapUpdateMenu, ""); //$NON-NLS-1$
//		}
	}

	/**
	 * @param item item concerné
	 * @param map map contenant les mises à jours a effectuer
	 * @return [+] si sa item est actif [-] sinon
	 */
	private String visibility(IItemMenu item, Map<String, IUpdateMenu> map) {
		if (map.containsKey(item.getName())) {
			if (map.get(item.getName()).getState()) {
				return "[+]"; //$NON-NLS-1$
			} else {
				return "[-]"; //$NON-NLS-1$
			}
		} else {
			if (item.isVisible()) {
				return "[+]"; //$NON-NLS-1$
			} else {
				return "[-]"; //$NON-NLS-1$
			}
		}
	}

	/**
	 * Affichage du menu dans la console
	 * @param menu menu qui doit être affiché
	 * @param mapUpdateMenu mise à jours à appliquer
	 * @param dec décalage correspondant à la profondeur actuelle
	 */
	private void printMenus(ISubMenu menu, Map<String, IUpdateMenu> mapUpdateMenu, String dec) {

		System.out.println(dec + visibility(menu, mapUpdateMenu) + " " + "[menu ]" + " " + menu.getName()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		for (IServiceMenu service : menu.getServiceMenus()) {
			System.out.println("   " + dec + visibility(service, mapUpdateMenu) + " " + "[service]" + " " + service.getName()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}

		for (IOptionMenu option : menu.getOptions()) {
			System.out.println("   " + dec + visibility(option, mapUpdateMenu) + (option.isValidated() ? " [X] " : " [ ] ") + " [option]" + " " + option.getName()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}

		for (ISubMenu subMenu : menu.getSubMenus()) {
			printMenus(subMenu, mapUpdateMenu, "   " + dec); //$NON-NLS-1$
		}
	}
}
