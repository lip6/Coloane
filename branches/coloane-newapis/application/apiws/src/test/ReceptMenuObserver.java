package test;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

public class ReceptMenuObserver implements IReceptMenuObserver {

	public void update(IReceptMenu e) {
		// TODO Auto-generated method stub
		System.out.println("Menu");
	}

}