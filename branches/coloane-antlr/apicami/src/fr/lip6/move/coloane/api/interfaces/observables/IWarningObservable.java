package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;

public interface IWarningObservable {

	void addObserver(IWarningObserver o);

	void setCreateThread(boolean createThread);

	void notifyObservers(String text);

}
