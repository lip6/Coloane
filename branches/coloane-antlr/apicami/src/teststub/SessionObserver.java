package teststub;

import java.util.ArrayList;
import java.util.Observable;

import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

public class SessionObserver implements ISessionObserver {

	void update(Observable obs, Object obj){
		/** Code à exécuter lors de la réception d'un KO */
	}


	public void update(IFkInfo fkInfo, ArrayList<IMenu> menu, ArrayList<IUpdateItem> update) {
		// TODO Auto-generated method stub

	}
}
