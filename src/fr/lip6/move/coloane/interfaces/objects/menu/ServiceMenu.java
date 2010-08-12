package fr.lip6.move.coloane.interfaces.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.services.IApiService;

/**
 * Define a menu that provides access to a service.
 * 
 * @author Jean-Baptiste Voron
 */
public class ServiceMenu extends ItemMenu implements IServiceMenu {
	/** Associated service */
	private IApiService associatedService;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visible The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param path The service path
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IApiService associatedService) {
		super(name, visibility, helpMessage);
		this.associatedService = associatedService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiService getAssociatedService() {
		return this.associatedService;
	}
}
