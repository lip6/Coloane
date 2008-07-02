package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.RootMenu;

public class RootMenuImpl implements IRootMenu {

	private ISubMenu root;
	
	public RootMenuImpl(RootMenu rootMenu){
		this.root = new SubMenuImpl(rootMenu.getRoot());
	}
	
	public String getName() {
		return root.getName();
	}

	public ISubMenu getRoot() {
		return root;
	}

}
