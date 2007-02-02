package fr.lip6.move.coloane.api.objects;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.IMenuCom;
import fr.lip6.move.coloane.interfaces.IRootMenuCom;

public class RootMenuCom implements IRootMenuCom {
	
	private String rootMenu;
	
	private Vector<IMenuCom> listMenu;
	
	
	public RootMenuCom (String rootMenu) {
		this.rootMenu = rootMenu;
	}
	
	public void addMenu (String serviceName, String serviceFather, boolean enabled) {
		MenuCom menu = new MenuCom(serviceName, serviceFather, enabled);
		this.listMenu.add((IMenuCom)menu);
	}

	public Vector<IMenuCom> getListMenu() {
		return listMenu;
	}

	public String getRootMenu() {
		return rootMenu;
	}

	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}
	
	public IMenuCom getMenu(String name) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				return menu;
			}
		}
		return null;
	}
	
	public void setEnabled(String name, boolean enabled) {
		for (IMenuCom menu : listMenu) {
			if (menu.getServiceName().equals(name)) {
				menu.setEnabled(enabled);
			}
		}
	}
	
}
