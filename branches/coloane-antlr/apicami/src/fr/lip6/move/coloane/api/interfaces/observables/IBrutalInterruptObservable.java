package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IBrutalInterruptObserver;

public interface IBrutalInterruptObservable {

	void addObserver(IBrutalInterruptObserver o);

	void setCreateThread(boolean createThread);


}
