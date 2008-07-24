package fr.lip6.move.coloane.test.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;

public class ReceptServiceStateObserver implements IReceptServiceStateObserver{

	public void update(IReceptServiceState e) {
		System.out.println("RECETP SERVICE STATE:\n" +
							"Service = " + e.getServiceName() + "\n" +
							"Message = " + e.getMessage() + "\n" +
							"State   = " + e.getState());
	}

}
