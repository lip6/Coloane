package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOptionItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public class OpenSessionObserver implements IOpenSessionObserver {

	public void update(IAnswerOpenSession s) {
		System.out.println("OPEN SESSION : idSession -> "+s.getIdSession()+" formalism -> "+s.getFormalism());
		System.out.println("menus null:"+(s.getMenus()==null));
		for (IRootMenu menu : s.getMenus().getRootsMenus()){
			printMenu(menu.getRoot(),"");
		}
	}
	
	public void printMenu(ISubMenu sm,String dec){
		System.out.println(dec+"["+(sm.isVisibility()?"+":"-")+"SUB  ] "+sm.getName()+" : validation="+sm.isValidation());
		if (sm.getServiceSimpleItems()!=null){
			for (IItemMenu smenu : sm.getServiceSimpleItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"Items]"+smenu.getName()+" : validation="+sm.isValidation());
			}
		}
		if (sm.getOptions()!=null){
			for (IOptionItem smenu : sm.getOptions()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"OP   ] "+smenu.getName()+" : validation="+sm.isValidation());
			}
		}
		if (sm.getSubMenus()!=null){
			for (ISubMenu smenu : sm.getSubMenus()){
				printMenu(smenu,"   ");
			}
		}
	}

}
