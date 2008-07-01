package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface ISubMenu extends IQuestion {
	
	/**
	 * Recupere la liste des options
	 * @return la liste des options
	 */
	public ArrayList<IOption> getOption();
	
	/**
	 * Recupere la liste des services
	 * @return la liste des services
	 */
	public ArrayList<IService> getServices();
	
	/**
	 * Recupere la liste des services sur des objets
	 * @return la liste des services sur des objets
	 */
	public ArrayList<IServiceWithObjects> getServicesWithObjects();
	
	/**
	 * Recupere la liste des services sur un objet
	 * @return la liste des services sur un objet
	 */
	public ArrayList<IServiceWithOneObject> getServicesWithOneObject();
	
	/**
	 * Recupere la liste des services sur un texte
	 * @return la liste des services sur un texte
	 */
	public ArrayList<IServiceWithOneText> getServiceWithOneText();
	
	/**
	 * Recupere la liste des services sur du texte
	 * @return la liste des services sur du texte
	 */
	public ArrayList<IServiceWithTexts> getServiceWithTexts();
	
	/**
	 * Recupere la liste des sous-menus
	 * @return la liste des sous-menus
	 */
	public ArrayList<ISubMenu> getSubMenus();
	
}
