package fr.lip6.move.coloane.test.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

public class ReceptResultObserver implements IReceptResultObserver {

	public void update(IReceptResult e) {
		// TODO Auto-generated method stub
		System.out.println("RESULTAT RECU");
	}

}
