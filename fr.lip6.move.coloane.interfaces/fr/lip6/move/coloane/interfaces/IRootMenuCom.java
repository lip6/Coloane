package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

/**
 * Interface fournie par l'API a Coloane pour la transmission
 * de menus de services (et autres type de menus)
 */

public interface IRootMenuCom {

	public String getRootMenu();
	
	public Vector<IMenuCom> getListMenu();
	
	public IMenuCom getMenu(String name);
	
}
