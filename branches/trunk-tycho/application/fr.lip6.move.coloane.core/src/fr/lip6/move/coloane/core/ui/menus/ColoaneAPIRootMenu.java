package fr.lip6.move.coloane.core.ui.menus;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

public class ColoaneAPIRootMenu extends MenuManager {
	
	/**
	 * Constructor
	 * @param rootMenuName The name of the menu
	 * @param rootMenuId The Id of the menu
	 */
	ColoaneAPIRootMenu(String rootMenuName, String rootMenuId) {
		super (rootMenuName, rootMenuId);
	}

	/**
	 * Constructor
	 * @param rootMenuName The name of the menu
	 * @param rootMenuIcon An icon associated with the menu
	 * @param rootMenuId The Id of the menu
	 */
	ColoaneAPIRootMenu(String rootMenuName, ImageDescriptor rootMenuIcon, String rootMenuId) {
		super(rootMenuName, rootMenuIcon, rootMenuId);
	}
}
