package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.ICloseConnectionObserver;



/* c'est utilisé quand FK envoie FC et pas quand coloane lenvoi*/
public interface ICloseConnectionObservable {

	void addObserver(ICloseConnectionObserver o);

	void setCreateThread(boolean createThread);

	void notifyObservers();

}
