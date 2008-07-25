package fr.lip6.move.coloane.api.camiObject.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.List;

/**
 * Définition de la racine d'un menu de services
 */
public class RootMenu implements IRootMenu {
	/** Le nom du menu racine */
	private String name;

	private List<ISubMenu> submenus;

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Ajoute un sous-menu au menu racine
	 * @param submenu Le sous-menu à ajouter
	 */
	public final void addSubMenu(ISubMenu submenu) {
		this.submenus.add(submenu);
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubMenu> getAllSubMenus() {
		return this.submenus;
	}

}
