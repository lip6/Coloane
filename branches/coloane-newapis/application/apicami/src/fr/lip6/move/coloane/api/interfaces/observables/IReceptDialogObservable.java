package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.observers.IReceptDialogObserver;

public interface IReceptDialogObservable {

	void addObserver(IReceptDialogObserver o);

	void setCreateThread(boolean createThread);

	void notifyObservers(IDialog dialog,Integer i);

}
