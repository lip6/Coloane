package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IFKCloseConnectionObserver;

public interface IFkCloseConnectionObservable {

	void addObserver(IFKCloseConnectionObserver o);

	void setCreateThread(boolean createThread);

}
