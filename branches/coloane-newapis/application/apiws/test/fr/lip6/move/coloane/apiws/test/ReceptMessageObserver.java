package fr.lip6.move.coloane.apiws.test;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

public class ReceptMessageObserver implements IReceptMessageObserver {

	public void update(IReceptMessage e) {
		// TODO Auto-generated method stub
		System.out.println("MESSAGE :"+e.getMessage());
	}
	
}