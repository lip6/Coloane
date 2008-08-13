package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.apicami.objects.menu.SubMenu;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/** La liste des services installés sur la plate-forme */
	private List<IService> services;

	/**
	 * Constructeur
	 * @param menus La liste des menus
 	 * @param updateMenus La liste des modifications à faire sur les menus
	 */
	public ReceptMenu(List<ISubMenu> menus, List<IUpdateMenu> updateMenus) {
		this.menus = menus;
		this.updateMenus = updateMenus;
		this.services = new ArrayList<IService>();

		// Extraction des services
		if (menus != null) {
			for (ISubMenu root : menus) {
				this.services.addAll(((SubMenu) root).getRealServices());
			}
		}
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
	public final Map<String, IUpdateMenu> getUpdateMenus() {
		Map<String, IUpdateMenu> mapUpdateMenu = new HashMap<String, IUpdateMenu>();
		if (this.updateMenus == null) { return null; }

		// Parcours des mises à jour
		for (IUpdateMenu element : this.updateMenus) {
			if (element == null) { continue; }
			mapUpdateMenu.put(element.getServiceName(), element);
		}
		return mapUpdateMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IService> getServices() {
		return this.services;
	}
}
