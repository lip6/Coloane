package fr.lip6.move.coloane.model.tests;

import junit.framework.TestCase;
import java.lang.reflect.Array;

import fr.lip6.move.coloane.interfaces.model.*;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

public class TestModel_PI extends TestCase {

	public void affiche_translate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}

	public void aleaAddPI(IArc arc, int x, int y) {
		
		boolean presence = false;

		// On regarde si (x,y) existe pour l'arc
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			if (arc.getListOfPI().get(i).getXPosition() == x
					&& arc.getListOfPI().get(i).getYPosition() == y) {

				presence = true;
			}
		}
		// Si oui, une exception doit être levée
		if (presence) {
			try {
				arc.addPI(x, y);
				assertTrue(false);
			} catch (SyntaxErrorException e) {
				assertTrue(true);
				System.out.println(e.toString());
			
			}

		} else {
			// Sinon, l'ajout est effectué.
			try {
				arc.addPI(x, y);

				// On test alors la présence du PI
				// (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {
					if (arc.getListOfPI().get(i).getXPosition() == x
							&& arc.getListOfPI().get(i).getYPosition() == y) {

						assertTrue(true);
					}
				}
			} catch (SyntaxErrorException e) {
				System.out.println(e.toString());
				assertTrue(false);
				
			}
		}
	}
	
	public void aleaRemovePI(IArc arc, int x, int y) {
		
		boolean presence = false;

		// On regarde si (x,y) existe pour l'arc
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			if (arc.getListOfPI().get(i).getXPosition() == x
					&& arc.getListOfPI().get(i).getYPosition() == y) {

				presence = true;
			}
		}

		// Si (x,y) n'existe pas, une exception doit être levée
		if (!presence) {
			try {
				arc.removePI(x, y);
				assertTrue(false);
			} catch (SyntaxErrorException e) {
				assertTrue(true);
				System.out.println(e.toString());
			}

		} else {
			// Sinon, la suppresion est effectué.
			try {
				arc.addPI(x, y);

				// On test alors la présence du PI
				// (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {
					
					if (arc.getListOfPI().get(i).getXPosition() == x
							&& arc.getListOfPI().get(i).getYPosition() == y) {
						
						//test echoué car (x,y) encore présent!
						assertFalse(true);
					}
				}
			} catch (SyntaxErrorException e) {
				System.out.println(e.toString());
				assertTrue(false);
				
			}
		}
	}
	
}
