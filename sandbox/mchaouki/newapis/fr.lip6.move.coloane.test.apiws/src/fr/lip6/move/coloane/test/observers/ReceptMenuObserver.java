package fr.lip6.move.coloane.test.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

public class ReceptMenuObserver implements IReceptMenuObserver {


	public void update(IReceptMenu e) {
		for (ISubMenu subMenu : e.getMenus()) {
			printMenus(subMenu,"");
		}
	}
	
	private void printMenus(ISubMenu menu, String dec) {
			System.out.println(dec+(menu.isVisible()?"[+]":"[-]")+"    "+"[menu   ]"+" "+menu.getName());
			
			for (IServiceMenu service : menu.getServiceMenus()) {
				System.out.println("   "+dec+(service.isVisible()?"[+]":"[-]")+"    "+"[service]"+" "+service.getName());
			}
			
			for (IOptionMenu option : menu.getOptions()) {
				System.out.println("   "+dec+(option.isVisible()?"[+]":"[-]")+(option.isValidated()?" [X] ":" [ ] ")+" [option ]"+" "+option.getName());
			}
			
			for (ISubMenu subMenu : menu.getSubMenus()) {
				printMenus(subMenu, "   " + dec);
			}
	}
}
