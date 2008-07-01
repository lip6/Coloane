package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;

public class OpenConnectionObserver implements IOpenConnectionObserver {

	public void update(IAnswerOpenConnection s) {
		System.out.println("OPEN CONNECTION Version -> "+s.getFkVersion()+" Major -> "+s.getFkMajor()+"Minor -> "+s.getFkMinor());
	}

}
