package fr.lip6.move.coloane.api.warehouse;

import fr.lip6.move.coloane.api.warehouse.services.Authentication;
import fr.lip6.move.coloane.api.warehouse.services.BrowseModels;
import fr.lip6.move.coloane.interfaces.api.Api;
import fr.lip6.move.coloane.interfaces.api.IApiObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;
import fr.lip6.move.coloane.interfaces.objects.services.ConsoleMessage;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

public class WareHouseApi extends Api  {
	
	/**
	 * Constructor
	 */
	public WareHouseApi() {
		super();
		LOGGER.config("Connected to Warehouse API");
		this.reBuildMenus();
	}
	
	/**
	 * Rebuild at any time the entire menu tree.<br>
	 * This method take into account the state of various API properties
	 */
	private void reBuildMenus() {
		this.menus.clear();
		
		if (this.isAuthenticated()) {
			IServiceMenu authMenu = new ServiceMenu("Current user: <" + this.getAuthenticationCredential("login") + ">", false, null, null); //$NON-NLS-1$);
			menus.add(authMenu);
		} else {
			IServiceMenu authMenu = new ServiceMenu("Set Authentication...", !this.isAuthenticated(), null, new Authentication(this), this.getImage("authentication.png")); //$NON-NLS-1$);
			menus.add(authMenu);
		}
		
		IServiceMenu unauthMenu = new ServiceMenu("Unset Authentication", this.isAuthenticated(), null, null, this.getImage("unauthenticate.png")); //$NON-NLS-1$);
		menus.add(unauthMenu);

		ISubMenu subMenu1 = new SubMenu("Public models", true);
		IServiceMenu service1 = new ServiceMenu("Fetch a model...", true, "Fetch a model from the warehouse", new BrowseModels(this));
		subMenu1.addServiceMenu(service1);
		
		ISubMenu subMenu2 = new SubMenu("Private models", this.isAuthenticated());
		IServiceMenu service2 = new ServiceMenu("Load a model...", true, "Load a previously stored model from the warehouse", new BrowseModels(this));
		IServiceMenu service3 = new ServiceMenu("Store the current model", true, "Store a model to the warehouse", new BrowseModels(this));
		subMenu2.addServiceMenu(service2);
		subMenu2.addServiceMenu(service3);

		menus.add(subMenu1);
		menus.add(subMenu2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAuthenticated(boolean authentication) {
		super.setAuthenticated(authentication);
		if (authentication) {
			this.reBuildMenus();
			this.notifyObservers(IApiObserver.MENU_OBSERVER, this.menus);
			
			ConsoleMessage copyMessage = new ConsoleMessage("Api Warehouse !", ConsoleMessage.COPYRIGHT_MESSAGE);
			ConsoleMessage debugMessage = new ConsoleMessage("Authenticated...", ConsoleMessage.DEBUG_MESSAGE);
			List<ConsoleMessage> messages = new ArrayList<ConsoleMessage>();
			messages.add(copyMessage);
			messages.add(debugMessage);
			this.notifyObservers(IApiObserver.MESSAGE_OBSERVER, messages);
		}
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
