package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette classe représent un menu principal
 */
public class RootMenu implements IRootMenu {

	private String name;

	private List<ItemMenu> subMenu;

	/**
	 * Constructeur
	 * @param name le nom du menu principal
	 * @param subMenu le sous-menu qui compose le menu principal
	 */
	public RootMenu(String name, List<ItemMenu> subMenu) {
		this.name = name;
		this.subMenu = subMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ItemMenu> getSubMenu() {
		return subMenu;
	}

}
