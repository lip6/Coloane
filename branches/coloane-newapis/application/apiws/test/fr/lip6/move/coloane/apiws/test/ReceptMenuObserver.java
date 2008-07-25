package fr.lip6.move.coloane.apiws.test;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

/**
 * Observeurs des menus.
 */
public class ReceptMenuObserver implements IReceptMenuObserver {

	/**
	 * {@inheritDoc}
	 */
	public final void update(IReceptMenu e) {
		// TODO Auto-generated method stub
		System.out.println("Menu");
	}

}
