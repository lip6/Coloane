package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define a menu that provides access to a service.
 * 
 * @author Jean-Baptiste Voron
 */
public class ServiceMenu extends ItemMenu implements IServiceMenu {
	/** Associated service */
	private String associatedService;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visible The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param path The service path
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, String associatedService, String path) {
		super(name, visibility, helpMessage, path);
		this.associatedService = associatedService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAssociatedService() {
		return this.associatedService;
	}
}
