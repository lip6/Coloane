package fr.lip6.move.coloane.communications.objects;

import java.util.Vector;

/**
 * Racine de l'arborescence des menus envoyee par Framekit a l'IHM
 * @author DQS equipe 2 (Styx)
 */
public class Menu {
	
	/**
	 * defini si le menu est racine
	 */
	private boolean root;
	
	/**
	 * nom de l'arborescence des menus
	 */
	private String name;
	
	/**
	 * informe si le service est actif
	 */
	private boolean active;
	
	/**
	 * liste des service du menu
	 * @element-type Service service propose par Framekit a l'IHM
	 */
	private Vector<Service> serviceList;
	
	/**
	 * liste des sous menus
	 * @element-type Menu sous menu
	 */
	private Vector<Menu> subMenuList;
	
	/**
	 * constructeur
	 * @param menuName nom de l'arborescence des menus
	 * @param rt defini si le menu est racine
	 * @param activ defini si le menu est actif ou non
	 */
	public Menu(String menuName, boolean rt, boolean activ) {
		this.name = menuName;
		this.serviceList = new Vector<Service>();
		this.subMenuList = new Vector<Menu>();
		this.root = rt;
		this.active = activ;
	}
	
	/**
	 * dit si l'objet est le Menu racine
	 * @return un booleen donnant la reponse (vraie/fausse)
	 */
	public boolean isRoot() {
		return this.root;
	}
	
	/**
	 * accesseur
	 * @return si le Menu est actif
	 */
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * seteur
	 * @param act definit si le Menu est actif
	 */
	public void setActive(boolean act) {
		this.active = act;
	}
	
	/**
	 * donne le nombre de sous menu
	 * @return le nombre de sous menu
	 */
	public int getSubMenuNumber() {
		return this.subMenuList.size();
	}
	
	/**
	 * donne le nombre de services 
	 * @return le nombre de services
	 */
	public int getServiceNumber() {
		return this.serviceList.size();
	}
	
	/**
	 * donne le nom du menu
	 * @return le nom du menu
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * ajoute un sous menu a la racine
	 * @param menu sous menu a ajoute
	 */
	public void addSubMenu(Menu menu) {
		subMenuList.add(menu);
	}
	
	/**
	 * ajoute un service a l'arbre des menus
	 * @param service service a ajoute
	 */
	public void addService(Service service) {
		serviceList.add(service);
	}
	
	/**
	 * renvoi le nieme sous menu
	 * @param index indice du sous menu
	 * @return le sous menu
	 */
	public Menu getASubMenu(int index) {
		if (index > subMenuList.size()) {
			return null;
		} else {
			return (Menu) subMenuList.get(index);
		}
	}
	
	/**
	 * renvoi le ieme service
	 * @param index indice du service
	 * @return le service
	 */
	public Service getAService(int index) {
		if (index > serviceList.size()) {
			return null;
		} else {
			return (Service) serviceList.get(index);
		}
	}
}
