package fr.lip6.move.coloane.menus;

import fr.lip6.move.coloane.exceptions.MenuNotFoundException;

import java.util.ArrayList;

public abstract class Menu {
	private String name;
	private String reference;
	private int level;
	private ArrayList<ChildMenu> menus;
	private boolean enabled = true;

	/**
	 * Constructeur
	 * Niveau :
	 * <ul>
	 *   <li> 0 : Root</li>
	 *   <li> >0 : Enfants</li>
	 * @param menuName Nom du menu
	 * @param menuReference Nom du menu reference
	 * @param menuLevel Niveau du menu
	 */
	public Menu(String menuName, String menuReference, int menuLevel) {
		this.name = menuName;
		this.reference = menuReference;
		this.level = menuLevel;
		menus = new ArrayList<ChildMenu>();
	}

	/**
	 * Ajoute un sous menu a ce (this) menu
	 * @param name Le nom du menu a ajouter
	 */
	public final void addMenu(String menuName) throws MenuNotFoundException {
		this.addMenu(menuName, true);
	}

	/**
	 * Ajoute un sous-menu a ce (this) menu
	 * @param name Le nom du menu a ajouter
	 * @param state L'etat du sous menu a ajouter
	 */
	public final void addMenu(String menuName, boolean state) throws MenuNotFoundException {
		this.addMenu(menuName, this.name, state);
	}

	/**
	 * Ajoute un menu dans la hierarchie des menus
	 * @param name Le nom du sous-menu a ajouter
	 * @param fatherName Le nom du parent direct du sous-menu
	 * @throws MenuNotFoundException
	 */
	public final ChildMenu addMenu(String menuName, String menuFatherName) throws MenuNotFoundException {

		// Si ce menu est le pere du sous-menu qu'on cherche a ajouter
		if (this.name.equals(menuFatherName)) {
			ChildMenu newMenu = null;

			if (this.level <= 1) {
				newMenu = new ChildMenu(menuName, this.name, this.level + 1);
			} else {
				newMenu = new ChildMenu(menuName, this.reference, this.level + 1);
			}

			// Le fils doit etre active si le pere est active (et vice-versa).
			newMenu.setEnabled(this.enabled);
			menus.add(newMenu);
			return newMenu;

		// Sinon on recherche dans la liste des menus le bon pere
		} else {
			for (ChildMenu child : menus) {
				try {
					return child.addMenu(name, menuFatherName);
				} catch (MenuNotFoundException m) {
					throw m;
				}
			}
		}

		// Si on est arrive ici, c'est qu'on n'est passe par aucun return, et donc qu'aucun menu avec pour nom fatherName n'a ete trouve.
		throw new MenuNotFoundException();
	}


	/**
	 * Constructeur
	 * @param menuName Nom du sous-menu a ajouter
	 * @param menuFatherName Nom du menu parent
	 * @param state Etat (active / desactive)
	 * @throws MenuNotFoundException
	 */
	public final ChildMenu addMenu(String menuName, String menuFatherName, boolean state) throws MenuNotFoundException {
		try {
			ChildMenu child = addMenu(name, menuFatherName);
			child.setEnabled(state);
			return child;
		} catch (MenuNotFoundException m) {
			throw m;
		}
	}


	/**
	 * Destruction de tous les menus
	 */
	protected final void destroy() {
		for (Menu menu : menus) {
			menu.destroy();
			menus.remove(menu);
		}

		menus = null;
	}

	/**
	 * Est-ce que le menu designe existe ?
	 * @param menuName Nom du menu a rechercher
	 * @return boolean true = existe
	 */
	public final boolean exists(String menuName) {
		try {
			getMenu(name);
			return true;
		} catch (MenuNotFoundException m) {
			return false;
		}
	}

	/**
	 * Retourne le menu designe
	 * @param menuName Le nom du menu a retourner
	 * @return Menu Le menu designe
	 * @throws MenuNotFoundException
	 */
	protected final Menu getMenu(String menuName) throws MenuNotFoundException {

		if (this.name.equals(menuName)) {
			return this;
		}

		// Parcours de tous les menus
		for (ChildMenu menu : menus) {
			try {
				return menu.getMenu(menuName);
			} catch (MenuNotFoundException m) {
				System.err.println("Error");
			}
		}

		throw new MenuNotFoundException();
	}

	/**
	 * Supprime le menu designe de la liste des menus
	 * @param menuName Le nom du menu a supprimer
	 * @throws MenuNotFoundException
	 */
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
					continue;
				}
			}
		}

		throw new MenuNotFoundException();
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

	public final String getName() {
		return this.name;
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

	protected final ArrayList<ChildMenu> getMenus() {
		return menus;
	}
}
