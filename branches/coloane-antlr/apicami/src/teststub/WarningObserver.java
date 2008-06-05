package teststub;

import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;

public class WarningObserver implements IWarningObserver{
	
		public void update(String message){
		// actions à réaliser en cas de reception d'un message warning
			System.out.println("Warning <"+ message +"> ");
	}
}
