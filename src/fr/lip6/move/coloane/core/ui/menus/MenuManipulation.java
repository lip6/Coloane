package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Set of methods that handle Coloane menu.<br>
 */
public final class MenuManipulation {
	/** The logger */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Utility class
	 */
	private MenuManipulation() { }

	/**
	 * Build a root menu
	 * @param rootMenuName The name of the menu to build
	 * @param rootMenuDescription the description of the menu to build
	 * @param rootMenuImage the icon associated with the menu (or <code>null</code> if not)
	 */
	public static ColoaneRootMenu buildRootMenu(String rootMenuName, String rootMenuDescription, ImageDescriptor rootMenuImage) {
		String menuId = rootMenuName.toLowerCase();
		// If the menu has no icon associated with
		if (rootMenuImage == null) {
			return new ColoaneRootMenu(rootMenuName, menuId);
		}
		return new ColoaneRootMenu(rootMenuName, rootMenuImage, menuId);
	}

	/**
	 * Build a new sub-menu
	 * @param menuDescription Description of the new sub-menu to build
	 * @return A menu manager that corresponds to the menu description
	 */
	public static ColoaneMenuManager buildSubMenu(ISubMenu menuDescription) {
		String menuId = menuDescription.getName().toLowerCase();
		ColoaneMenuManager item = new ColoaneMenuManager(menuDescription.getName(), menuId, menuDescription.isVisible());
		
		for (ISubMenu subMenu : menuDescription.getSubMenus()) {
			item.add(buildSubMenu(subMenu));
		}
		for (IServiceMenu service : menuDescription.getServiceMenus()) {
			item.add(buildServiceMenu(service, menuDescription.isVisible()));
		}
		for (IOptionMenu option : menuDescription.getOptions()) {
			item.add(buildOptionMenu(option, menuDescription.isVisible()));
		}
		return item;
	}

	/**
	 * Build a service menu item
	 * @param service The description of the service to create
	 * @param parentState Is the parent enabled ?
	 * @return The action that corresponds to the service description
	 */
	private static IAction buildServiceMenu(IServiceMenu service, boolean parentState) {
		IAction item = new ServiceAction(service);
		item.setEnabled(parentState && service.isVisible());
		return item;
	}

	/**
	 * Build an option menu item
	 * @param option The description of the option to create
	 * @param parentState Is the parent enabled ?
	 * @return The action that corresponds to the option description
	 */
	private static IAction buildOptionMenu(IOptionMenu option, boolean active) {
		IAction item = new OptionAction(option);
		item.setEnabled(active && option.isVisible());
		return item;
	}

//	/**
//	 * @param menuManager rootMenu
//	 * @param mapUpdateMenu Map contenant les élements à mettre à jour.
//	 */
//	public static void update(MenuManager menuManager, Map<String, IUpdateMenu> mapUpdateMenu) {
//		LOG.finer("Update menus"); //$NON-NLS-1$
//		update(menuManager, mapUpdateMenu, true);
//	}
//
//	/**
//	 * @param item élement du menu
//	 * @param mapUpdateMenu Map contenant les élements à mettre à jour.
//	 * @param parent est que les menus parents sont actifs.
//	 */
//	private static void update(IContributionItem item, Map<String, IUpdateMenu> mapUpdateMenu, Boolean parent) {
//		IUpdateMenu update = mapUpdateMenu.get(item.getId());
//
//		// Mise à jour d'un service ou d'une option
//		if (item instanceof ActionContributionItem) {
//			IAction action = ((ActionContributionItem) item).getAction();
//			IStatedElement statedElement = (IStatedElement) action;
//
//			if (update != null) {
//				statedElement.setState(update.getState());
//			}
//			action.setEnabled(parent && statedElement.getState());
//		}
//
//		// Mise à jour d'un sous-menu
//		if (item instanceof ColoaneMenuManager) {
//			ColoaneMenuManager coloaneMenuManager = (ColoaneMenuManager) item;
//
//			if (update != null) {
//				coloaneMenuManager.setState(update.getState());
//			}
//			for (IContributionItem subItem : coloaneMenuManager.getItems()) {
//				update(subItem, mapUpdateMenu, parent && coloaneMenuManager.getState());
//			}
//		}
//	}
}
