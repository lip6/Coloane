package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface représent un item.
 * Un item peut être:
 * 		- un item simple (i.e. service)
 * 		- une option
 * 		- un sous-menu
 */
public abstract class ItemMenu implements IItemMenu {

	private int type;

	private String name;

	private boolean visible;

	private String associatedService;

	private List<String> helps;

	/**
	 * Constructeur d'un item
	 * @param type le type de l'item :
	 * 				- 1 = item simple (i.e. un service)
	 * 				- 2 = option
	 * 				- 3 = sous-menu
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
	 * {@inheritDoc}
	 */
    public final int getType() {
    	return type;
    }

	/**
	 * {@inheritDoc}
	 */
    public final void setType(int type) {
    	this.type = type;
    }

	/**
	 * {@inheritDoc}
	 */
    public final String getName() {
    	return name;
    }

	/**
	 * {@inheritDoc}
	 */
    public final void setName(String name) {
    	this.name = name;
    }

	/**
	 * {@inheritDoc}
	 */
    public final boolean isVisible() {
    	return visible;
    }

	/**
	 * {@inheritDoc}
	 */
    public final void setVisible(boolean visible) {
    	this.visible = visible;
    }

	/**
	 * {@inheritDoc}
	 */
    public final String getAssociatedService() {
    	return associatedService;
    }

	/**
	 * {@inheritDoc}
	 */
    public final void setAssociatedService(String associatedService) {
    	this.associatedService = associatedService;
    }

	/**
	 * {@inheritDoc}
	 */
    public final List<String> getHelps() {
    	return helps;
    }

	/**
	 * {@inheritDoc}
	 */
    public final void addHelp(String help) {
    	this.helps.add(help);
    }

}
