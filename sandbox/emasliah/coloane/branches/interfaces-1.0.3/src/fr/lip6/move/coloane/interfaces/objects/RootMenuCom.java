package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

public abstract class RootMenuCom implements IRootMenuCom {

	/** Le nom du menu root */
	private String name;

	/** L'ensemble des sous-menus (sans hierarchie) */
	private Vector<IMenuCom> listMenu;

	/**
	 * Constructeur d'un menu racine
	 * @param rootMenuName Le nom du menu root
	 */
	public RootMenuCom(String rootMenuName) {
		this.name = rootMenuName;
		listMenu = new Vector<IMenuCom>();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#addMenu(IMenuCom)
	 */
	public final void addMenu(IMenuCom smenu) {
		this.listMenu.add(smenu);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getListMenu()
	 */
	public final Vector<IMenuCom> getListMenu() {
		return listMenu;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getRootMenu()
	 */
	public final String getRootMenuName() {
		return name;
	}

	/**
	 * Indique le nom du menu root apres sa creation
	 * @param name Le nom du menu root
	 */
	public final void setRootMenu(String rootMenuName) {
		this.name = rootMenuName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IRootMenu#getMenu(String)
	 */
	public final IMenuCom getMenu(String menuName) {
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
	public final void setEnabled(String menuName, boolean state) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				menu.setEnabled(state);
			}
		}
	}

}
