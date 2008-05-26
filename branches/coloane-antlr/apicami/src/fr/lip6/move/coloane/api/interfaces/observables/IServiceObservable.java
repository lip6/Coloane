package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IServiceObserver;

public interface IServiceObservable {

	void addObserver(IServiceObserver o);

	void setCreateThread(boolean createThread);

}
