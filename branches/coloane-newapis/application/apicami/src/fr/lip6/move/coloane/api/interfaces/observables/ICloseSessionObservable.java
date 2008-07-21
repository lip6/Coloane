package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.ICloseConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ICloseSessionObserver;

public interface ICloseSessionObservable {

	void addObserver(ICloseSessionObserver o);

	void setCreateThread(boolean createThread);

	void notifyObservers(String string);

}
