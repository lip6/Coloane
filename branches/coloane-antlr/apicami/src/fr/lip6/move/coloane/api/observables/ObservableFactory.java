package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

public class ObservableFactory {

	public static IConnectionObservable getNewConnectionObservable(){
		return new ConnectionObservable();
	}

}
