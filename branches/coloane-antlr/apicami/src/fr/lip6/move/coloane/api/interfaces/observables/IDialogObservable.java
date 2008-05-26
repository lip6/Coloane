package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IDialogObserver;

public interface IDialogObservable {

	void addObserver(IDialogObserver o);

	void setCreateThread(boolean createThread);

}
