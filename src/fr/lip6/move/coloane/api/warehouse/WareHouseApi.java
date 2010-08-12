package fr.lip6.move.coloane.api.warehouse;

import fr.lip6.move.coloane.api.warehouse.services.FirstService;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.OptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

public class WareHouseApi extends Observable implements IApi  {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	private List<ISubMenu> menus = new ArrayList<ISubMenu>();
	
	public WareHouseApi() {
		LOGGER.config("Connected to Warehouse API");
		
		ISubMenu subMenu1 = new SubMenu("Services", true);
		IServiceMenu service1 = new ServiceMenu("Stats", true, "no help available", new FirstService(this));
		subMenu1.addServiceMenu(service1);
		menus.add(subMenu1);
		
		ISubMenu subMenu2 = new SubMenu("Options", true);
		subMenu2.addOptionMenu(new OptionMenu("Speed", true, "no", true));
		menus.add(subMenu2);
	}
	
	public void enrichMenus() {
		ISubMenu subMenu2 = new SubMenu("Fine Options", true);
		subMenu2.addOptionMenu(new OptionMenu("Debug", false, "no", true));
		menus.add(subMenu2);
		this.setChanged();
		notifyObservers(menus);
	}
	
	public List<ISubMenu> getApiMenus() {
		return this.menus;
	}
}
