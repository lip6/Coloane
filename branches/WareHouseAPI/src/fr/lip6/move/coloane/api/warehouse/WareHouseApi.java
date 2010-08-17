package fr.lip6.move.coloane.api.warehouse;

import fr.lip6.move.coloane.api.warehouse.services.AskForModel;
import fr.lip6.move.coloane.api.warehouse.services.Authentication;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.OptionMenu;
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
	
	public WareHouseApi() {
		LOGGER.config("Connected to Warehouse API");
		this.authentication = false;
		this.reBuildMenus();
	}
	
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

		ISubMenu subMenu1 = new SubMenu("Fetch a model", authentication);
		IServiceMenu service1 = new ServiceMenu("Demo", true, "A simple model for demonstration purpose", new AskForModel(this));
		subMenu1.addServiceMenu(service1);
		menus.add(subMenu1);
	}
	
	public void enrichMenus() {
		ISubMenu subMenu2 = new SubMenu("Fine Options", true);
		subMenu2.addOptionMenu(new OptionMenu("Debug", false, "no", true));
		menus.add(subMenu2);
		this.setChanged();
		notifyObservers(menus);
	}
	
	public List<IItemMenu> getApiMenus() {
		return this.menus;
	}
	
	public void setAuthentication(String login, String password) {
		this.login = login;
		this.password = password;
		this.authentication = true;

		this.reBuildMenus();
		this.setChanged();
		notifyObservers(menus);
	}
	
	public String getLogin() {
		return login;
	}
	
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
