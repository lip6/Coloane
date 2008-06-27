package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseSessionObserver;

public class CloseSessionObserver implements ICloseSessionObserver {

	public void update(IAnswerCloseSession s) {
		System.out.println("CLOSE SESSION");
		System.out.println("Formalism -> "+s.getFormalism());
		System.out.println("idSession -> "+s.getIdSession());
		System.out.println("");
	}

}
