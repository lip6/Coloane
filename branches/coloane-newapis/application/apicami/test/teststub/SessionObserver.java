package teststub;

import java.util.ArrayList;
import java.util.Observable;

import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

public class SessionObserver implements ISessionObserver {


	public void update( ArrayList<IMenu> menuList,
			ArrayList<IUpdateItem> updatesList) {
            if (menuList == null){
            System.out.println("test stub: on a notifié les modificateurs apré QQ");
            }else {
		   System.out.println("test stub: on a notifié l'ouverture session");
            }
		   /*
		    * l'affichage des menus
		    */

		//  System.out.println("\n test stub: affichage des menus");
		//   for (IMenu menu: menuList){
		//	   afficher(menu);
		  // }

	//	   System.out.println("\n test stub: affichage des modificateurs de menu");
	//	   afficher(updatesList);



	}


	private void afficher(IMenu menu) {
		System.out.print("--AQ(");
		if (menu.getParent() != null) {
			String parent = menu.getParent().getName();
			if (parent != null) {
				System.out.print(parent + ",");
			} else {
				System.out.print("null" + ",");
			}
		}
		System.out.print(menu.getName() + ",");
		if (menu.getType() != -1) {
			System.out.print(menu.getType() + ",");
		} else {
			System.out.print(",");
		}
		if (menu.getQuestionBehavior() != -1) {
			System.out.print(menu.getQuestionBehavior() + ",");
		} else {
			System.out.print(",");
		}

		boolean tmpValid = menu.isValid();
		if (tmpValid) {
			System.out.print("1" + ",");
		} else {
			System.out.print("2" + ",");
		}

		boolean tmpDialog = menu.isDialogAllowed();
		if (tmpDialog) {
			System.out.print("2" + ",");
		} else {
			System.out.print("1" + ",");
		}

		boolean tmpStop = menu.stopAuthorized();
		if (tmpStop) {
			System.out.print("2" + ",");
		} else {
			System.out.print("1" + ",");
		}

		if (menu.outputFormalism() != null) {
			System.out.print(menu.outputFormalism() + ",");
		} else {
			System.out.print(",");
		}
		boolean tmpActive = menu.isActivate();
		if (tmpActive) {
			System.out.print("1");
		} else {
			System.out.print("2");
		}

		System.out.println(")");
		ArrayList<IMenu> tmp = menu.getChildren();
		for (IMenu child : tmp) {
			afficher(child);
		}
	}

	/**
	 * cette methode affiche une selection de modificateurs des menus.
	 * @param modifMenu
	 */
	private void afficher(ArrayList<IUpdateItem> modifMenu) {

		for (IUpdateItem tq : modifMenu) {
			System.out.print("--TQ(");
			System.out.print(tq.getRootName() + ",");
			System.out.print(tq.getServiceName() + ",");
			if (tq.getState()) {
				System.out.println("7, )");
			} else {
				System.out.println("8, )");
			}
		}
	}
}
