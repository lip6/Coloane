package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSuspendSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISuspendSessionObserver;

public class SuspendSessionObserver implements ISuspendSessionObserver{

	public void update(IAnswerSuspendSession e) {
		System.out.println("SUSPEND SESSION : idSession -> "+e.getIdSession());
	}

}
