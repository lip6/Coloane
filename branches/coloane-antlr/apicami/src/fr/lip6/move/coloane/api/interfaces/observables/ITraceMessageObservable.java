package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.ITraceMessageObserver;

public interface ITraceMessageObservable {

	void addObserver(ITraceMessageObserver o);

	void setCreateThread(boolean createThread);

}
