package fr.lip6.move.coloane.interfaces.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.services.IService;

/**
 * Define a menu that provides access to a service.
 * 
 * @author Jean-Baptiste Voron
 */
public class ServiceMenu extends ItemMenu implements IServiceMenu {
	/** Associated service */
	private IService associatedService;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visible The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param path The service path
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IService associatedService, String path) {
		super(name, visibility, helpMessage, path);
		this.associatedService = associatedService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IService getAssociatedService() {
		return this.associatedService;
	}
}
