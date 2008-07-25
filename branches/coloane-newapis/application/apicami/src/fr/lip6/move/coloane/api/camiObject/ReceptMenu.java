package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.List;

/**
 * Définition d'un retour d'ouverture de session.<br>
 * On retrouve :
 * <ul>
 * 	<li>La liste des menus à afficher</li>
 * 	<li>La liste des modifications à faire sur les menus</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 *
 */
public class ReceptMenu implements IReceptMenu {
	/** Liste des menus */
	private List<ISubMenu> menus;

	/** La liste des modification à faire sur les menus */
	private List<IUpdateMenu> updateMenus;

	/**
	 * Constructeur
	 * @param menus La liste des menus
 	 * @param updateMenus La liste des modifications à faire sur les menus
	 */
	public ReceptMenu(List<ISubMenu> menus, List<IUpdateMenu> updateMenus) {
		this.menus = menus;
		this.updateMenus = updateMenus;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubMenu> getMenus() {
		return this.menus;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IUpdateMenu> getUpdateMenus() {
		return this.updateMenus;
	}
}
