package teststub;

import fr.lip6.move.coloane.api.interfaces.observers.ICloseConnectionObserver;

public class CloseConnectionObserver implements ICloseConnectionObserver {

	public void update(){
		System.out.println("deconncetion de la part de FK.....");
	}
}
