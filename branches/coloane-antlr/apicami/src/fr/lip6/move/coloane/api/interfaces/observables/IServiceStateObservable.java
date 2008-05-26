package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IServiceStateObserver;

public interface IServiceStateObservable {

	void addObserver(IServiceStateObserver o);

	void setCreateThread(boolean createThread);

}
