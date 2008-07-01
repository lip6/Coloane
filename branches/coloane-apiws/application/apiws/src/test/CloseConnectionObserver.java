package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseConnectionObserver;

public class CloseConnectionObserver implements ICloseConnectionObserver {

	public void update(IAnswerCloseConnection s) {
		System.out.println("CLOSE CONNECTION id[0] -> "+s.getId()[0]+" id[1] -> "+s.getId()[1]);
	}

}
