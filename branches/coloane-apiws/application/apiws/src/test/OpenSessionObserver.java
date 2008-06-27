package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public class OpenSessionObserver implements IOpenSessionObserver {

	public void update(IAnswerOpenSession s) {
		System.out.println("OPEN SESSION");
		System.out.println("Formalism -> "+s.getFormalism());
		System.out.println("idSession -> "+s.getIdSession());
		System.out.println("");
	}

}
