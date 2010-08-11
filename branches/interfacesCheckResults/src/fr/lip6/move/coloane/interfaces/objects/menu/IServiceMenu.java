package fr.lip6.move.coloane.interfaces.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.services.IApiService;

/**
 * Define a menu that provides access to a service.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IServiceMenu extends IItemMenu {

    /**
     * @return The service associated with the menu item
     */
	IApiService getAssociatedService();
}
