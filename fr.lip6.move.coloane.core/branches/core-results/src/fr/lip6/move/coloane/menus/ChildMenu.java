package fr.lip6.move.coloane.menus;

import java.util.Iterator;


public class ChildMenu extends Menu implements Iterable<ChildMenu> {
	
	public ChildMenu(String name,String reference, int level) {
		super(name,reference,level);
	}

	public Iterator<ChildMenu> iterator() {
		return this.menus.iterator();
	}
	
	public boolean isLeaf() {
		return getChildrenNumber() == 0;
	}
}
