package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.Map;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

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
	/** Sauvegarde du menu de coloane, il faut absolument passé par getColoaneMenu() pour récupérer le menu */
	private static Menu coloaneMenu = null;

	/**
	 * Classe non instanciable
	 */
	private MenuManipulation() { }

	/**
	 * @return Le menu coloane ou null si il n'existe pas
	 */
	private static Menu getColoaneMenu() {
		// si le menu a déjà été trouvé
		if (coloaneMenu != null) {
			return coloaneMenu;
		}

		// sinon on le cherche
		Menu menu = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
		for (MenuItem item : menu.getItems()) {
			if (item.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) { //$NON-NLS-1$
				coloaneMenu = item.getMenu();
				return coloaneMenu;
			}
		}

		// le menu n'a pas été trouvé
		return null;
	}

	/**
	 * @param rootApiMenu menu reçu par l'api
	 * @param session session attaché à ce menu
	 * @return MenuManager correpondant au menu passé en parametre
	 */
	public static MenuManager build(ISubMenu rootApiMenu, ISession session) {
		return build(rootApiMenu, rootApiMenu.isVisible(), session);
	}

	/**
	 * @param apiMenu (sous-)menu reçu par l'api
	 * @param active <code>true</code> si les éléments de ce menu doivent être actif
	 * @param session session attaché à ce menu
	 * @return MenuManager correpondant au menu passé en parametre
	 */
	private static MenuManager build(ISubMenu apiMenu, boolean active, ISession session) {
		MenuManager item = new MenuManager(apiMenu.getName(), apiMenu.getName());
		for (IServiceMenu service : apiMenu.getServiceMenus()) {
			item.add(buildServiceMenu(service, active && apiMenu.isVisible(), session));
		}
		for (ISubMenu subMenu : apiMenu.getSubMenus()) {
			item.add(build(subMenu, active && apiMenu.isVisible(), session));
		}
		for (IOptionMenu option : apiMenu.getOptions()) {
			item.add(buildOptionMenu(option, active && apiMenu.isVisible(), session));
		}
		return item;
	}

	/**
	 * @param service service à créer
	 * @param active est-ce que le parent est actif
	 * @param session session attaché à ce menu
	 * @return ServiceAction
	 */
	private static IAction buildServiceMenu(IServiceMenu service, boolean active, ISession session) {
		IAction item = new ServiceAction(service, session);
		item.setEnabled(active && service.isVisible());
		return item;
	}

	/**
	 * @param option option à créer
	 * @param active est-ce que le parent est actif
	 * @param session session attaché à ce menu
	 * @return OptionAction
	 */
	private static IAction buildOptionMenu(IOptionMenu option, boolean active, ISession session) {
		IAction item = new OptionAction(option, session);
		item.setEnabled(active && option.isVisible());
		return item;
	}

	/**
	 * Ajoute le menu passé en parametre au menu de Coloane.
	 * @param menu menu
	 */
	public static void add(MenuManager menu) {
		if (getColoaneMenu() == null) {
			throw new IllegalStateException("Le menu de Coloane n'existe pas"); //$NON-NLS-1$
		}
		menu.fill(getColoaneMenu(), getColoaneMenu().getItemCount());
	}

	/**
	 * Supprime tous les menus sauf PLATFORM
	 */
	public static void clean() {
		// On ne fait pas de clean si le menu n'existe pas
		if (getColoaneMenu() != null && !getColoaneMenu().isDisposed()) {
			for (MenuItem item : getColoaneMenu().getItems()) {
				if (!item.getText().equals(Coloane.getParam("PLATFORM_MENU"))) { //$NON-NLS-1$
					item.dispose();
				}
			}
		}
	}

	/**
	 * @param menuManager rootMenu
	 * @param mapUpdateMenu Map contenant les élements à mettre à jour.
	 */
	public static void update(MenuManager menuManager, Map<String, IUpdateMenu> mapUpdateMenu) {
		update(menuManager, mapUpdateMenu, true);
	}

	/**
	 * @param item élement du menu
	 * @param mapUpdateMenu Map contenant les élements à mettre à jour.
	 * @param parentActive est que les menus parents sont actifs.
	 */
	private static void update(IContributionItem item, Map<String, IUpdateMenu> mapUpdateMenu, boolean parentActive) {
		boolean active;
		if (!parentActive) {
			active = false;
		} else {
			IUpdateMenu updateMenu = mapUpdateMenu.get(item.getId());
			if (updateMenu != null) {
				active = updateMenu.getState();
			} else {
				active = item.isEnabled();
			}
		}

		if (item.isEnabled() != active && item instanceof ActionContributionItem) {
			IAction action = ((ActionContributionItem) item).getAction();
			action.setEnabled(active);
		}

		if (item instanceof MenuManager) {
			for (IContributionItem subItem : ((MenuManager) item).getItems()) {
				update(subItem, mapUpdateMenu, active);
			}
		}
	}
}
