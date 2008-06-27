package test;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;

public class OpenConnectionObserver implements IOpenConnectionObserver {

	public void update(IAnswerOpenConnection s) {
		System.out.println("OPEN CONNECTION");
		System.out.println("Version -> "+s.getFkVersion());
		System.out.println("Major   -> "+s.getFkMajor());
		System.out.println("Minor   -> "+s.getFkMinor());
		System.out.println("");
	}

}
