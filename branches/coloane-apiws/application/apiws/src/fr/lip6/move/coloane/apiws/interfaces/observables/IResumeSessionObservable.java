package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerResumeSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.IResumeSessionObserver;

public interface IResumeSessionObservable {
	
public void addObserver(IResumeSessionObserver o);
	
	
	public void removeObserver(IResumeSessionObserver o);
	
	
	public void notifyObservers(IAnswerResumeSession e);
	
	
	void setCreateThread(boolean createThread);
}
