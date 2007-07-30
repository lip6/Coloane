package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

public abstract class RootMenuCom implements IRootMenuCom {
	
	/** Le nom du menu root */
	private String name;
	
	/** L'ensemble des sous-menus (sans hierarchie) */
	private Vector<IMenuCom> listMenu;
	
	/**
	 * Constructeur d'un menu racine
	 * @param name Le nom du menu root
	 */
	public RootMenuCom (String name) {
		this.name = name;
		listMenu = new Vector<IMenuCom>();
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#addMenu(IMenuCom)
	 */
	public void addMenu (IMenuCom smenu) {
		this.listMenu.add(smenu);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getListMenu()
	 */
	public Vector<IMenuCom> getListMenu() {
		return listMenu;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getRootMenu()
	 */
	public String getRootMenuName() {
		return name;
	}

	/**
	 * Indique le nom du menu root apres sa creation
	 * @param name Le nom du menu root
	 */
	public void setRootMenu(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getMenu(String)
	 */
	public IMenuCom getMenu(String name) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				return menu;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#setEnabled(String, boolean)
	 */
	public void setEnabled(String name, boolean enabled) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				menu.setEnabled(enabled);
			}
		}
	}
	
}
