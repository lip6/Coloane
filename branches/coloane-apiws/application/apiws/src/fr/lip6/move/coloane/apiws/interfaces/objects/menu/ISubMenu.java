package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface ISubMenu extends IItemMenu {
	
	/**
	 * Recupere la liste des options
	 * @return la liste des options
	 */
	public ArrayList<IOptionItem> getOptions();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<IServiceSimpleItem> getServiceSimpleItems();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<IServiceWithObjectsItem> getServiceWithObjectsItems();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<IServiceWithOneObjectItem> getServiceWithOneObjectItems();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<IServiceWithOneTextItem> getServiceWithOneTextItems();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<IServiceWithTextsItem> getServiceWithTextsItems();
	
	
	/**
	 * Recupere la liste des sous-menus
	 * @return la liste des sous-menus
	 */
	public ArrayList<ISubMenu> getSubMenus();
	
}
