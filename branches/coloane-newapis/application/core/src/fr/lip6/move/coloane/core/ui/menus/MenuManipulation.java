package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.ICommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.CommandContributionItem;

/**
 * Bibliothèque de méthode statique permettant de manipuler le menu de Coloane.<br>
 * <br>
 * <ul>On peut :
 *   <li>Construire un MenuManager à partir d'un ISubMenu
 *   <li>Ajouter un MenuManager dans le menu de Coloane
 *   <li>Nettoyer le menu de Coloane
 *   <li>modifier l'état d'un élément du menu
 * </ul>
 */
public final class MenuManipulation {
	private static Menu COLOANE;
	static {
		Menu menu = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
		for (MenuItem item : menu.getItems()) {
			if (item.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) { //$NON-NLS-1$
				COLOANE = item.getMenu();
			}
		}
	}

	/**
	 * Classe non instanciable
	 */
	private MenuManipulation() { }

	/**
	 * @param rootApiMenu menu reçu par l'api
	 * @return MenuManager correpondant au menu passé en parametre
	 */
	public static MenuManager build(ISubMenu rootApiMenu) {
		try {
			return build(rootApiMenu, rootApiMenu.isVisible());
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @param apiMenu (sous-)menu reçu par l'api
	 * @param active <code>true</code> si les éléments de ce menu doivent être actif
	 * @return MenuManager correpondant au menu passé en parametre
	 */
	private static MenuManager build(ISubMenu apiMenu, boolean active) {
		MenuManager item = new MenuManager(apiMenu.getName());
		for (ISubMenu subMenu : apiMenu.getSubMenus()) {
			item.add(build(subMenu, active && apiMenu.isVisible()));
		}
		for (IServiceMenu service : apiMenu.getServiceMenus()) {
			item.add(buildServiceMenu(service, active && apiMenu.isVisible()));
		}
		for (IOptionMenu option : apiMenu.getOptions()) {
			item.add(buildOptionMenu(option, active && apiMenu.isVisible()));
		}
		return item;
	}

	private static IAction buildServiceMenu(IServiceMenu service, boolean active) {
		IAction item = new ServiceAction(service.getName());
		item.setEnabled(active && service.isVisible());
		return item;
	}

	private static IAction buildOptionMenu(IOptionMenu option, boolean active) {
		IAction item = new ServiceAction(option.getName());
		item.setEnabled(active && option.isVisible());
		return item;
	}

	/**
	 * Ajoute le menu passé en parametre au menu de Coloane.
	 * @param menu menu
	 */
	public static void add(MenuManager menu) {
		if (COLOANE == null) {
			throw new IllegalStateException("Le menu de Coloane n'existe pas"); //$NON-NLS-1$
		}
		menu.fill(COLOANE, COLOANE.getItemCount());
	}

	/**
	 * Supprime tous les menus sauf PLATFORM
	 */
	public static void clean() {
		if (COLOANE == null) {
			throw new IllegalStateException("Le menu de Coloane n'existe pas"); //$NON-NLS-1$
		}
		for (MenuItem item : COLOANE.getItems()) {
			if (!item.getText().equals(Coloane.getParam("PLATFORM_MENU"))) { //$NON-NLS-1$
				item.dispose();
			}
		}
	}

	/**
	 * Removes a menu from the menubar
	 * @param menuName The name of menu we want to delete
	 */
	public static void remove(String menuName) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		for (MenuItem item : shell.getMenuBar().getItems()) {
			if (item.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) { //$NON-NLS-1$
				remove(item, menuName);
			}
		}
	}

	/**
	 *
	 * @param father
	 * @param menuName
	 */
	public static void remove(MenuItem father, String menuName) {
		for (MenuItem mi : father.getMenu().getItems()) {
			if (mi.getText().equals(menuName)) {
				mi.dispose();
				return;
			}
		}
	}
}
