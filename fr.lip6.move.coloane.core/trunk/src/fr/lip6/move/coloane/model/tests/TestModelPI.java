package fr.lip6.move.coloane.model.tests;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;

import java.lang.reflect.Array;

import junit.framework.TestCase;

public class TestModelPI extends TestCase {

	public final void afficheTranslate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}

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
				System.out.println(e.toString());

			}

		} else {
			// Sinon, l'ajout est effectue.
			try {
				arc.addPI(x, y);

				// On test alors la presence du PI
				// (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {
					if (arc.getListOfPI().get(i).getXPosition() == x
							&& arc.getListOfPI().get(i).getYPosition() == y) {

						assertTrue(true);
					}
				}
			} catch (ModelException e) {
				System.out.println(e.toString());
				assertTrue(false);

			}
		}
	}

	public final void aleaRemovePI(IArc arc, int x, int y) {

		boolean presence = false;

		// On regarde si (x,y) existe pour l'arc
		for (int i = 0; i < arc.getListOfPI().size(); i++) {
			if (arc.getListOfPI().get(i).getXPosition() == x
					&& arc.getListOfPI().get(i).getYPosition() == y) {

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
				System.out.println(e.toString());
			}

		} else {
			// Sinon, la suppresion est effectue.
			try {
				arc.removePI(x, y);

				// On test alors la presence du PI (x,y)
				for (int i = 0; i < arc.getListOfPI().size(); i++) {

					if (arc.getListOfPI().get(i).getXPosition() == x
							&& arc.getListOfPI().get(i).getYPosition() == y) {

						//test echoue car (x,y) encore present!
						assertFalse(true);
					}
				}
			} catch (ModelException e) {
				System.out.println(e.toString());
				assertTrue(false);

			}
		}
	}

}
