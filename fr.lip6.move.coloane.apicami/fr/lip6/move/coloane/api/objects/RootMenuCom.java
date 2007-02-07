package fr.lip6.move.coloane.api.objects;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.IMenuCom;
import fr.lip6.move.coloane.interfaces.IRootMenuCom;

public class RootMenuCom implements IRootMenuCom {
	
	/** Le nom du menu root */
	private String rootMenu;
	
	/** L'ensemble des sous-menus (sans hierarchie) */
	private Vector<IMenuCom> listMenu;
	
	/**
	 * Constructeur
	 * @param rootMenu Le nom du menu root
	 */
	public RootMenuCom (String rootMenu) {
		this.rootMenu = rootMenu;
		listMenu = new Vector<IMenuCom>();
	}
	
	/**
	 * Ajout d'un sous menu
	 * @param serviceName Le nom du service attache au sous-menu
	 * @param serviceFather Le nom du menu pere
	 * @param enabled Son etat
	 */
	public void addMenu (String serviceName, String serviceFather, boolean enabled) {
		MenuCom menu = new MenuCom(serviceName, serviceFather, enabled);
		this.listMenu.add((IMenuCom)menu);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IRootMenu#getListMenu()
	 */
	public Vector<IMenuCom> getListMenu() {
		return listMenu;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IRootMenu#getRootMenu()
	 */
	public String getRootMenu() {
		return rootMenu;
	}

	/**
	 * Indique le nom du menu root apres sa creation
	 * @param rootMenu Le nom du menu root
	 */
	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IRootMenu#getMenu(String)
	 */
	public IMenuCom getMenu(String name) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				return menu;
			}
		}
		return null;
	}
	
	/**
	 * Modifie l'etat d'un sous-menu
	 * @param name Le nom du sous-menu
	 * @param enabled L'etat du sous-menu
	 */
	public void setEnabled(String name, boolean enabled) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				menu.setEnabled(enabled);
			}
		}
	}
	
}
