package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette classe représent un sous-menu.
 */
public class SubMenu extends ItemMenu implements ISubMenu {

	private List<IItemMenu> subMenu;

	/**
	 * Constructeur d'un item
	 * @param type le type de l'item :
	 * 				- item simple (i.e. un service)
	 * 				- option
	 * 				- sous-menu
	 * @param name le nom de l'item
	 * @param visible la visibilité de l'item
	 * @param associatedService l'identifiant du service auquel l'item est associé
	 * @param helps l'aide sur l'item
	 * @param subMenu la liste des items qui compose le sous-menu
	 */
	protected SubMenu(int type, String name, boolean visible, String associatedService, List<String> helps, List<IItemMenu> subMenu) {
		super(type, name, visible, associatedService, helps);
		this.subMenu = subMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IItemMenu> getSubMenu() {
		return subMenu;
	}

}
