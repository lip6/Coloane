package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;

import junit.framework.TestCase;

public class TestModelPI extends TestCase {

	public final void aleaAddPI(IArc arc, int x, int y) {

		boolean presence = false;

		// On regarde si (x,y) existe pour l'arc
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			if (arc.getListOfPI().get(i).getXPosition() == x
					&& arc.getListOfPI().get(i).getYPosition() == y) {

				presence = true;
			}
		}
		// Si oui, une exception doit etre levee
		if (presence) {
			try {
				arc.addPI(x, y);
				assertTrue(false);
			} catch (ModelException e) {
				assertTrue(true);
			}

		} else {
			// Sinon, l'ajout est effectue.
			try {
				arc.addPI(x, y);

				// On test alors la presence du PI
				// (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {
					if (arc.getListOfPI().get(i).getXPosition() == x && arc.getListOfPI().get(i).getYPosition() == y) {
						assertTrue(true);
					}
				}
			} catch (ModelException e) {
				assertTrue(false);
			}
		}
	}

	public final void aleaRemovePI(IArc arc, int x, int y) {

		boolean presence = false;

		// On regarde si (x,y) existe pour l'arc
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			if (arc.getListOfPI().get(i).getXPosition() == x && arc.getListOfPI().get(i).getYPosition() == y) {
				presence = true;
			}
		}

		// Si (x,y) n'existe pas, une exception doit etre levee
		if (!presence) {
			try {
				arc.removePI(x, y);
				assertTrue(false);
			} catch (ModelException e) {
				assertTrue(true);
			}

		} else {
			// Sinon, la suppresion est effectue.
			try {
				arc.removePI(x, y);

				// On test alors la presence du PI (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {
					if (arc.getListOfPI().get(i).getXPosition() == x && arc.getListOfPI().get(i).getYPosition() == y) {
						//test echoue car (x,y) encore present!
						assertFalse(true);
					}
				}
			} catch (ModelException e) {
				assertTrue(false);
			}
		}
	}

}
