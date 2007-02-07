package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

/**
 * Interface fournie par l'API a Coloane pour la transmission
 * de menus de services (et autres type de menus)
 * @see IMenuCom
 */

public interface IRootMenuCom {

	/**
	 * Recupere le nom du menu root
	 * @return Le nom du menu Root
	 */
	public String getRootMenu();
	
	/**
	 * Recupere la liste des menus
	 * @return Vector<IMenuCom> La liste des sous menus
	 * @see IMenuCom
	 */
	public Vector<IMenuCom> getListMenu();
	
	/**
	 * Retourne un sous-menu du menu root
	 * @param name Le nom du sous-menu qu'on souhaite obtenir
	 * @return IMenuCom
	 * @see IMenuCom
	 */
	public IMenuCom getMenu(String name);
	
}
