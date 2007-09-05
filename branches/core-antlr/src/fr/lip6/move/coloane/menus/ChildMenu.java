package fr.lip6.move.coloane.menus;

/**
 * Definition d'un sous-menu, qui est lui meme un menu<br>
 * Il dispose en plus des methodes du menu simple, un indicateur de fin.<br>
 * @see {@link Menu}
 */
public class ChildMenu extends Menu {

	/**
	 * Constructeur
	 * @param name Le nom du sous-menu
	 * @param fatherMenu Le menu parent
	 */
	public ChildMenu(String name, Menu fatherMenu) {
		super(name, fatherMenu);
	}

	/**
	 * Le menu est-il une feuille (ie Pas de sous-menu) ?
	 * @return booleen
	 */
	public final boolean isLeaf() {
		return getChildren().size() == 0;
	}
}
