package fr.lip6.move.coloane.menus;

import java.util.ArrayList;


public abstract class Menu {
	private String name;
	private String reference;
	private int level;
	protected ArrayList<ChildMenu> menus;
	protected boolean enabled = true;

	public Menu(String menuName, String menuReference, int menuLevel) {
		this.name = menuName;
		this.reference = menuReference;
		this.level = menuLevel;
		menus = new ArrayList<ChildMenu>();
	}

	/**
	 * Adds a submenu to this menu.
	 * @param name The name of the menu to add
	 */
	public final void addMenu(String menuName) {
		ChildMenu subMenu = null;

		if (this.level <= 1) {
			subMenu = new ChildMenu(menuName, this.name, this.level + 1);
		} else {
			subMenu = new ChildMenu(menuName, this.reference, this.level + 1);
		}

		menus.add(subMenu);
	}

	/**
	 * Adds a submenu to this menu.
	 * @param name The name of the menu to add
	 * @param enabled
	 */
	public final void addMenu(String menuName, boolean state) {
		ChildMenu newMenu  = null;

		if (this.level <= 1) {
			newMenu = new ChildMenu(menuName, this.name, this.level + 1);
		} else {
			newMenu = new ChildMenu(menuName, this.reference, this.level + 1);
		}

		newMenu.setEnabled(state);
		menus.add(newMenu);
	}

	/**
	 * Adds a menu in the menus hierarchy.
	 * @param name The name of the menu to add
	 * @param
	 * 	fatherName
	 * 	The name of the direct parent of the
	 * @throws MenuNotFoundException
	 */
	public final ChildMenu addMenu(String menuName, String menuFatherName) throws MenuNotFoundException {

		if (this.name.equals(menuFatherName)) {
			ChildMenu newMenu = null;

			if (this.level <= 1) {
				newMenu = new ChildMenu(menuName, this.name, this.level + 1);
			} else {
				newMenu = new ChildMenu(menuName, this.reference, this.level + 1);
			}

			/* Le fils doit etre active si le pere est active (et vice-versa). */
			newMenu.setEnabled(this.enabled);
			menus.add(newMenu);
			return newMenu;
		} else {
			for (ChildMenu child : menus) {
				try {
					return child.addMenu(name, menuFatherName);
				} catch (MenuNotFoundException m) {
					System.out.println("Echec lors de la creation du menu");
				}
			}
		}

		/*
		 * Si on est arrive ici, c'est qu'on n'est passe par aucun
		 * return, et donc qu'aucun menu avec pour nom fatherName
		 * n'a ete trouve.
		 */
		throw new MenuNotFoundException();
	}


	public final ChildMenu addMenu(String menuName, String menuFatherName, boolean state) throws MenuNotFoundException {
		try {
			ChildMenu child = addMenu(name, menuFatherName);
			child.enabled = state;
			return child;
		} catch (MenuNotFoundException m) { throw m; }
	}


	/**
	 * Destroys the menu and its submenus.
	 *
	 */
	public final void destroy() {
		for (Menu menu : menus) {
			menu.destroy();
			menus.remove(menu);
		}

		menus = null;
	}

	public final boolean exists(String menuName) {
		try {
			getMenu(name);
			return true;
		} catch (MenuNotFoundException m) {
			return false;
		}
	}

	public final ArrayList<ChildMenu> getChildren() {
		return menus;
	}

	public final int getChildrenNumber() {
		return menus.size();
	}

	public final boolean getEnabled() {
		return enabled;
	}

	public final Menu getMenu(String menuName) throws MenuNotFoundException {

		if (this.name.equals(menuName)) {
			return this;
		}

		for (ChildMenu menu : menus) {
			try {
				return menu.getMenu(menuName);
			} catch (MenuNotFoundException m) {
				System.err.println("Error");
			}
		}

		throw new MenuNotFoundException();
	}

	public final String getName() {
		return this.name;
	}

	public final void removeMenu(String menuName) throws MenuNotFoundException {
		for (Menu menu : menus) {
			if (menu.getName().equals(menuName)) {
				menu.destroy();
				menus.remove(menu);
				return;
			} else {
				try {
					menu.removeMenu(menuName);
					return;
				} catch (MenuNotFoundException m) {
					System.err.println("Echec");
				}
			}
		}

		throw new MenuNotFoundException();
	}

	public final void setEnabled(boolean state) {
		this.enabled = state;

		for (ChildMenu child : this.getChildren()) {
			child.setEnabled(enabled);
		}
	}

	public final void setEnabled(String menuName, boolean state) throws MenuNotFoundException {
		getMenu(name).setEnabled(enabled);
	}

	public final String getReference() {
		return this.reference;
	}
}
