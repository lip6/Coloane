package fr.lip6.move.coloane.api.warehouse;

import fr.lip6.move.coloane.api.warehouse.services.Authentication;
import fr.lip6.move.coloane.api.warehouse.services.BrowseModels;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

import org.eclipse.jface.resource.ImageDescriptor;

public class WareHouseApi extends Observable implements IApi  {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	private List<IItemMenu> menus = new ArrayList<IItemMenu>();
	
	private boolean authentication;
	private String login;
	private String password;
	
	/**
	 * Constructor
	 */
	public WareHouseApi() {
		LOGGER.config("Connected to Warehouse API");
		this.authentication = false;
		this.reBuildMenus();
	}
	
	/**
	 * Rebuild at any time the entire menu tree.<br>
	 * This method take into account the state of various API properties
	 */
	private void reBuildMenus() {
		this.menus.clear();
		
		if (authentication) {
			IServiceMenu authMenu = new ServiceMenu("Current user: <" + this.login + ">", false, null, null); //$NON-NLS-1$);
			menus.add(authMenu);
		} else {
			IServiceMenu authMenu = new ServiceMenu("Set Authentication...", !authentication, null, new Authentication(this), this.getImage("authentication.png")); //$NON-NLS-1$);
			menus.add(authMenu);
		}
		
		IServiceMenu unauthMenu = new ServiceMenu("Unset Authentication", authentication, null, null, this.getImage("unauthenticate.png")); //$NON-NLS-1$);
		menus.add(unauthMenu);

		ISubMenu subMenu1 = new SubMenu("Models", authentication);
		IServiceMenu service1 = new ServiceMenu("Browse models...", true, "A simple model for demonstration purpose", new BrowseModels(this));
		subMenu1.addServiceMenu(service1);
		menus.add(subMenu1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<IItemMenu> getApiMenus() {
		return this.menus;
	}
	
	/**
	 * Set authentication properties
	 * @param login The registered login
	 * @param password The registered password
	 */
	public void setAuthentication(String login, String password) {
		this.login = login;
		this.password = password;
		this.authentication = true;

		this.reBuildMenus();
		this.setChanged();
		notifyObservers(menus);
	}
	
	/**
	 * @return The registered login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * @return The registered password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Return an {@link ImageDescriptor} form an image description
	 * @param image The image description
	 * @return the corresponding {@link ImageDescriptor}
	 */
	private ImageDescriptor getImage(String image) {
		return ImageDescriptor.createFromFile(this.getClass(), "/resources/" + image);
	}
}
