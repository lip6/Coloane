package teststub;

import java.util.ArrayList;
import java.util.Observable;

import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

public class SessionObserver implements ISessionObserver {


	public void update(IFkInfo fkInfo, ArrayList<IMenu> menuList,
			ArrayList<ArrayList<IUpdateItem>> updatesList) {
		// TODO Auto-generated method stub
		System.out.println("on notifie");
	}

}
