package teststub;

import java.util.Observable;


import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;

/**
 * c'est l'observateur de la connection
 * @author uu & kahoo
 *
 */

public class ConnectionObserver implements IConnectionObserver {

	public void update(IConnectionVersion arg) {

		System.out.println("testStub: on a notifié la connection");
		System.out.println(arg.getFkName());
		System.out.println(arg.getFkMajor());
		System.out.println(arg.getFkMinor());
	}
}
