package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;

public class MMenuImpl implements IMMenu {
	
	private ArrayList<IRootMenu> roots;
	
	public MMenuImpl(MMenu m){
		this.roots = new ArrayList<IRootMenu>();
		for (int i=0; i<m.getRoots().length;i++){
			this.roots.add(new RootMenuImpl(m.getRoots()[i]));		
		}
	}
	
	public ArrayList<IRootMenu> getRootsMenus() {
		return roots;
	}

}
