package fr.lip6.move.coloane.menus;

import fr.lip6.move.coloane.exceptions.MenuNotFoundException;

import java.util.ArrayList;

public abstract class Menu {
	private String name;
	private String reference;
	private int level;
	private ArrayList<ChildMenu> childMenus;
	private boolean enabled = true;

	/**
	 * Constructeur
	 * Niveau :
	 * <ul>
	 *   <li>0 : Root</li>
	 *   <li>>0 : Enfants</li>
	 * </ul>
	 * @param menuName Nom du menu
	 * @param menuReference Nom du menu reference
	 * @param menuLevel Niveau du menu
	 */
	public Menu(String menuName, String menuReference, int menuLevel) {
		this.name = menuName;
		this.reference = menuReference;
		this.level = menuLevel;
		childMenus = new ArrayList<ChildMenu>();
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
			childMenus.add(newMenu);
			return newMenu;

		// Sinon on recherche dans la liste des menus le bon pere
		} else {
			for (Menu child : this.childMenus) {
				try {
					return child.addMenu(menuName, menuFatherName);
				} catch (MenuNotFoundException m) {
					// Ce sous-menu ne convient pas
					continue;
				}
			}
		}

		// Si on est arrive ici, c'est qu'on n'est passe par aucun return, et donc qu'aucun menu avec pour nom fatherName n'a ete trouve.
		throw new MenuNotFoundException(menuName, menuFatherName);
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
			ChildMenu child = addMenu(menuName, menuFatherName);
			child.setEnabled(state);
			return child;
		} catch (MenuNotFoundException m) {
			System.err.println("Impossible de construire le menu :" + m.toString());
			throw m;
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
		for (Menu menu : childMenus) {
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
		for (Menu menu : childMenus) {
			if (menu.getName().equals(menuName)) {
				menu.destroy();
				childMenus.remove(menu);
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

	/**
	 * Destruction de tous les menus
	 */
	protected final void destroy() {
		for (Menu menu : childMenus) {
			menu.destroy();
			childMenus.remove(menu);
		}
		childMenus = null;
	}

	/**
	 * Retourne la liste des sous-menus
	 * @return Une liste de menus
	 */
	public final ArrayList<ChildMenu> getChildren() {
		return childMenus;
	}

	/**
	 * Retourne l'etat d'activation du menu
	 * @return booleen
	 */
	public final boolean isEnabled() {
		return enabled;
	}

	/**
	 * Retourne le nom du menu
	 * @return Le nom du menu sous forme de chaine de caracteres
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Retourne la reference du menu courant
	 * @return Le nom du menu de reference courant
	 */
	public final String getReference() {
		return this.reference;
	}

	/**
	 * Modifie le status du menu ET de tous ses fils
	 * @param state Le nouvel etat
	 */
	public final void setEnabled(boolean state) {
		this.enabled = state;

		for (Menu child : this.getChildren()) {
			child.setEnabled(enabled);
		}
	}

	/**
	 * Specifie le menu dont on doit changer l'etat
	 * @param menuName Le nom du menu
	 * @param state Le nouvel etat
	 * @throws MenuNotFoundException Si on ne trouve pas le menu
	 */
	public final void setEnabled(String menuName, boolean state) throws MenuNotFoundException {
		getMenu(name).setEnabled(enabled);
	}
}
