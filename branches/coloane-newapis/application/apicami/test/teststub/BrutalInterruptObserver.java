package teststub;

import java.util.Observable;

import fr.lip6.move.coloane.api.interfaces.observers.IBrutalInterruptObserver;

public class BrutalInterruptObserver implements IBrutalInterruptObserver {


	public void update(String message) {
		System.out.println("jai recu un KO avec <"+ message +"> comme message");

	}


		

}
