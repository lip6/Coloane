package teststub;

import fr.lip6.move.coloane.api.interfaces.observers.ICloseSessionObserver;

public class CloseSessionObserver  implements ICloseSessionObserver {

	public void update(String num){
		System.out.println("fermeture session....." + num);
	}
}



