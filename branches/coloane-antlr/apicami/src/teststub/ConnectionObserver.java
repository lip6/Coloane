package teststub;

import java.util.Observable;


import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;

/**
 * c'est l'observateur de la connection
 * @author uu & kahoo
 *
 */

public class ConnectionObserver implements IConnectionObserver {

	public void update(IFkVersion arg) {

		System.out.println("testStub: connection");
		System.out.println(arg.getFkName());
		System.out.println(arg.getFkMajor());
		System.out.println(arg.getFkMinor());
	}
}
