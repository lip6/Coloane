package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSuspendSession;

public interface ISuspendSessionObserver {
	
	public void update(IAnswerSuspendSession e);
}
