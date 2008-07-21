package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IReceptResultObserver;

public interface IReceptResultObservable {

	void addObserver(IReceptResultObserver o);

	void setCreateThread(boolean createThread);

}
