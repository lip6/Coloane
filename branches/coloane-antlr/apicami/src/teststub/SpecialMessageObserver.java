package teststub;

import fr.lip6.move.coloane.api.interfaces.ISpecialMessage;
import fr.lip6.move.coloane.api.interfaces.observers.ISpecialMessageObserver;

public class SpecialMessageObserver implements ISpecialMessageObserver {

	public void update(ISpecialMessage message) {
		System.out.println("jai recu un message special de type:  "+ message.getType().getInt() + "  et comme contenu  " + message.getContent());

	}

}
