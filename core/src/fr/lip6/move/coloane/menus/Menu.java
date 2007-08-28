package fr.lip6.move.coloane.menus;

import java.util.ArrayList;

/**
 * Definition d'un menu de service
 */
public abstract class Menu {

	/** Le nom du menu */
	private String name;

	/** Le parent du menu */
	private Menu parent;

	/** La liste des enfants du menu */
	private ArrayList<ChildMenu> childMenus;

	/** L'etat du menu */
	private boolean enabled = true;

	/**
	 * Constructeur
	 * @param menuName Nom du menu
	 * @param menuReference Menu parent
	 */
	public Menu(String menuName, Menu menuFatherName) {
		this.name = menuName;
		this.parent = menuFatherName;
		this.childMenus = new ArrayList<ChildMenu>();
	}

	/**
	 * Ajoute un menu dans la hierarchie des menus
	 * @param name Le nom du sous-menu a ajouter
	 * @param fatherName Le nom du parent direct du sous-menu
	 */
	public final ChildMenu addMenu(String menuName, String menuFatherName) {

		// Si ce menu est le pere du sous-menu qu'on cherche a ajouter
		if (this.name.equals(menuFatherName)) {
			ChildMenu newMenu = new ChildMenu(menuName, this);
			// Le fils doit etre active si le pere est active (et vice-versa).
			newMenu.setEnabled(this.enabled);
			this.childMenus.add(newMenu);
			return newMenu;

		// Sinon on recherche dans la liste des menus le bon pere
		} else {
			for (Menu child : this.childMenus) {
				ChildMenu newMenu = child.addMenu(menuName, menuFatherName);
				if (newMenu != null) { return newMenu; }
			}
		}

		return null;
	}


	/**
	 * Ajoute un menu dans la hierarchie des menus
	 * @param menuName Le nom du menu a ajouter
	 * @param menuFatherName Le nom du menu parent
	 * @param state L'etat du menu a ajouter
	 * @return Le menu qui vient d'etre ajoute
	 */
	public final ChildMenu addMenu(String menuName, String menuFatherName, boolean state) {
		ChildMenu child = addMenu(menuName, menuFatherName);
		if (child != null) { child.setEnabled(state); }
		return child;
	}


	/**
	 * Retourne le menu desire
	 * @param menuName Le nom du menu a chercher
	 * @return Le menu ou null si le menu est introuvable
	 */
	protected final Menu getMenu(String menuName) {

		// Si on a de la chance
		if (this.name.equals(menuName)) { return this; }

		// Parcours de tous les menus
		for (Menu menu : this.childMenus) {
			Menu wantedMenu = menu.getMenu(menuName);
			if (wantedMenu != null) { return wantedMenu; }
		}

		return null;
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
	public final Menu getParent() {
		return this.parent;
	}

	/**
	 * Le menu est-il un menu de reference<br>
	 * Un menu de reference est un menu immediatement sous root
	 * @return booleen
	 */
	protected final boolean isCategory() {
		return (this.parent instanceof RootMenu);
	}

	/**
	 * Retourne la reference necessaire lors de l'invocation de service
	 * @return Le menu immediatement sous root qui contient ce menu
	 */
	public final Menu getReference() {
		if (this.isCategory()) {
			return this;
		} else {
			return this.parent.getReference();
		}
	}

	/**
	 * Modifie le status du menu ET de tous ses fils
	 * @param state Le nouvel etat
	 */
	public final void setEnabled(boolean state) {
		this.enabled = state;
		for (Menu child : this.getChildren()) { child.setEnabled(state); }
	}

	/**
	 * Specifie le menu dont on doit changer l'etat
	 * @param menuName Le nom du menu
	 * @param state Le nouvel etat
	 * @throws MenuNotFoundException
	 */
	public final void setEnabled(String menuName, boolean state) {
		Menu toUpdate = getMenu(menuName);
		if (toUpdate != null) { toUpdate.setEnabled(state); }
	}
}
