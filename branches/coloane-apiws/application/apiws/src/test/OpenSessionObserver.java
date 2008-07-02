package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IService;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public class OpenSessionObserver implements IOpenSessionObserver {

	public void update(IAnswerOpenSession s) {
		System.out.println("OPEN SESSION : idSession -> "+s.getIdSession()+" formalism -> "+s.getFormalism());
		System.out.println("menus null:"+(s.getMenus()==null));
		for (IRootMenu menu : s.getMenus().getRootsMenus()){
			System.out.println(""+menu.getName());
			for (IService smenu : menu.getRoot().getServices()){
				System.out.println("   "+smenu.getName());
			}
		}
	}

}
