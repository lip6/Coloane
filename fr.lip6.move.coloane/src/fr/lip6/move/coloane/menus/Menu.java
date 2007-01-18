package fr.lip6.move.coloane.menus;

import java.util.ArrayList;


public abstract class Menu {
	protected String name;
	protected ArrayList<ChildMenu> menus;
	protected boolean enabled = true;
	
	public Menu(String name) {
		this.name = name;
		menus = new ArrayList<ChildMenu>();
	}
	
	/**
	 * Adds a submenu to this menu.
	 * @param name The name of the menu to add
	 */
	public void addMenu(String name) {
		menus.add(new ChildMenu(name));
	}
	
	/**
	 * Adds a submenu to this menu.
	 * @param name The name of the menu to add
	 * @param enabled
	 */
	public void addMenu(String name, boolean enabled) {
		ChildMenu newMenu  = new ChildMenu(name);
		newMenu.setEnabled(enabled);
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
	public ChildMenu addMenu(String name, String fatherName)
		throws MenuNotFoundException {
	
		if (this.name.equals(fatherName)) {
			ChildMenu newMenu = new ChildMenu(name);
			menus.add(newMenu);
			return newMenu;
		} else {
				for (ChildMenu child : menus) {
					try {
						return child.addMenu(name, fatherName);
					} catch (MenuNotFoundException m) {}
				}
		}
		
		/*
		 * Si on est arrive ici, c'est qu'on n'est passe par aucun
		 * return, et donc qu'aucun menu avec pour nom fatherName
		 * n'a ete trouve.
		 */
		throw new MenuNotFoundException();
	}
	
	
	public ChildMenu addMenu(String name, String fatherName, boolean enabled)
		throws MenuNotFoundException {
		try {
			ChildMenu child = addMenu(name, fatherName);
			child.enabled = enabled;
			return child;
		} catch (MenuNotFoundException m) { throw m; }
	}
	
	
	/**
	 * Destroys the menu and its submenus.
	 *
	 */
	public void destroy() {
		for (Menu menu : menus) {
			menu.destroy();
			menus.remove(menu);
		}
		
		menus = null;
	}

	public boolean exists(String name) {
		try {
			getMenu(name);
			return true;
		} catch (MenuNotFoundException m) {
			return false;
		}
	}
	
	public ArrayList<ChildMenu> getChildren() {
		return menus;
	}
	
	public int getChildrenNumber() {
		return menus.size();
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public Menu getMenu(String name) 
		throws MenuNotFoundException {
		
		if (this.name.equals(name))
			return this;
		
		for (ChildMenu menu : menus) {
			try {
				return menu.getMenu(name);
			} catch (MenuNotFoundException m) {}
		}
		
		throw new MenuNotFoundException();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void removeMenu(String name)
		throws MenuNotFoundException {
		for (Menu menu : menus)
			if (menu.getName().equals(name)) {
				menu.destroy();
				menus.remove(menu);
				return;
			} else {
				try {
					menu.removeMenu(name);
					return;
				} catch (MenuNotFoundException m) {}
			}
		
		throw new MenuNotFoundException();
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setEnabled(String name, boolean enabled) throws MenuNotFoundException {
		getMenu(name).enabled = enabled;
	}
}
