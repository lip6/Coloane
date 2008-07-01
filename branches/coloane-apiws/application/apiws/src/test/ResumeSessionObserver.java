package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerResumeSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.IResumeSessionObserver;

public class ResumeSessionObserver implements IResumeSessionObserver {

	public void update(IAnswerResumeSession e) {
		System.out.println("RESUME SESSION : idSession -> "+e.getIdSession());
	}

}
