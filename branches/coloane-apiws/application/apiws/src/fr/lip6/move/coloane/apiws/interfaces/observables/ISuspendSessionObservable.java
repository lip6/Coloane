package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSuspendSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISuspendSessionObserver;

public interface ISuspendSessionObservable {
	
	public void addObserver(ISuspendSessionObserver o);
	
	
	public void removeObserver(ISuspendSessionObserver o);
	
	
	public void notifyObservers(IAnswerSuspendSession e);
	
	
	void setCreateThread(boolean createThread);
}
