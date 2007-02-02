package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

public interface IRootMenuCom {

	public String getRootMenu();
	
	public Vector<IMenuCom> getListMenu();
	
	public IMenuCom getMenu(String name);
	
}
