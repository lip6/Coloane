package fr.lip6.move.coloane.test.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

public class ReceptMessageObserver implements IReceptMessageObserver {

	public void update(IReceptMessage e) {
		switch (e.getTypeMessage()){
			case IReceptMessage.ADMINISTRATOR_MESSAGE : {
				System.out.println("ADMINISTRATOR_MESSAGE : " + e.getMessage());
				break;
			}
			case IReceptMessage.COPYRIGHT_MESSAGE : {
				System.out.println("COPYRIGHT_MESSAGE :" + e.getMessage());
				break;
			}
			case IReceptMessage.TRACE_MESSAGE : {
				System.out.println("TRACE_MESSAGE :" + e.getMessage());
				break;
			}
			case IReceptMessage.WARRNING_MESSAGE : {
				System.out.println("WARRNING_MESSAGE :" + e.getMessage());
				break;
			}
			case IReceptMessage.ERROR_MESSAGE : {
				System.out.println("ERROR_MESSAGE :" + e.getMessage());
				break;
			}
			default : {
				System.out.println("MESSAGE INCONNU :" + e.getMessage());
				break;
			}
		
		}

	}

}
