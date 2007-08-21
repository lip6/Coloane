package fr.lip6.move.coloane.menus;

public class ChildMenu extends Menu {

	/**
	 * Constructeur
	 * @param name Le nom du sous-menu
	 * @param reference Le nom de la reference
	 * @param level Le niveau de ce sous-menu
	 */
	public ChildMenu(String name, String reference, int level) {
		super(name, reference, level);
	}

	/**
	 * Le menu est-il une feuille (ie Pas de sous-menu)
	 * @return booleen
	 */
	public final boolean isLeaf() {
		return getChildren().size() == 0;
	}
}
