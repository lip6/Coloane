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

	/**
	 * Recupere la liste des menus
	 * @return Vector<IMenuCom> La liste des sous menus
	 * @see IMenuCom
	 */
	public Vector<IMenuCom> getListMenu() {
		return listMenu;
	}

	/**
	 * Recupere le nom du menu root
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
	
	/**
	 * Retourne un sous-menu du menu root
	 * @param name Le nom du sous-menu qu'on souhaite obtenir
	 * @return IMenuCom
	 * @see IMenuCom
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
