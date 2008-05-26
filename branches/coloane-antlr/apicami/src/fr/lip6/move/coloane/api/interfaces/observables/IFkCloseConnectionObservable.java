package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.observers.IFKCloseConnectionObserver;


/* c'est utilisé quand FK envoie FC et pas quand coloane lenvoi*/
public interface IFkCloseConnectionObservable {

	void addObserver(IFKCloseConnectionObserver o);

	void setCreateThread(boolean createThread);

}
