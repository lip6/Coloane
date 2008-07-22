package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface représent un item.
 * Un sous-menu peut être:
 * 		- une option
 * 		- un sous-menu
 */
public abstract class ItemMenu {

	private int type;

	private String name;

	private boolean visible;

	private String associatedService;

	private List<String> helps;

	/**
	 * Constructeur d'un item
	 * @param type le type de l'item :
	 * 				- item simple (i.e. un service)
	 * 				- option
	 * 				- sous-menu
	 * @param name le nom de l'item
	 * @param visible la visibilité de l'item
	 * @param associatedService l'identifiant du service auquel l'item est associé
	 * @param helps l'aide sur l'item
	 */
	protected ItemMenu(int type, String name, boolean visible, String associatedService, List<String> helps) {
		this.type = type;
		this.name = name;
		this.visible = visible;
		this.associatedService = associatedService;
		this.helps = helps;
	}

	/**
     * Recupere le type de l'item
     * @return le type de l'item
     */
    public final int getType() {
    	return type;
    }

    /**
     * Modifie le type de l'item
     * @param type le nouveau type de l'item
     */
    public final void setType(int type) {
    	this.type = type;
    }

	/**
	 * Récupére le nom de l'item
	 * @return le nom de l'item
	 */
    public final String getName() {
    	return name;
    }

	/**
	 * Modifie le nom de l'item
	 * @param name le nouveau nom de l'item
	 */
    public final void setName(String name) {
    	this.name = name;
    }

	/**
     * Recupere la visibilite de l'item
     * @return la visibilite de l'item
     */
    public final boolean isVisible() {
    	return visible;
    }

    /**
     * Modifie la visibilite de l'item
     * @param visible la nouvelle visibilite de l'item
     */
    public final void setVisible(boolean visible) {
    	this.visible = visible;
    }


    /**
     * Récupére l'identifiant du service associé à l'item
     * @return l'identifiant du service associé à l'item
     */
    public final String getAssociatedService() {
    	return associatedService;
    }

    /**
     * Modifie l'identifiant du service associé à l'item
     * @param associatedService le nouveau identifiant du service associé à l'item
     */
    public final void setAssociatedService(String associatedService) {
    	this.associatedService = associatedService;
    }

	/**
     * Recupere les messages d'aide sur l'item
     * @return les messages d'aide sur l'item
     */
    public final List<String> getHelps() {
    	return helps;
    }

	/**
     * Ajoute un message d'aide
     * @param help le message d'aide a ajouter
     */
    public final void addHelp(String help) {
    	this.helps.add(help);
    }

}
