package fr.lip6.move.coloane.model;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;


import java.util.Vector;

import junit.framework.TestCase;

public class TestModelTranslate extends TestCase {

	/**
	 * Verifie si toutes les valeurs du tableau t1 sont dans le tableau t2 et
	 * inversement
	 * @param t1, sortie d'une traduction de modele
	 * @param t2, sortie d'une traduction de modele
	 * @return boolean, indique si les 2 traductions sont identiques
	 */
	public final boolean compareTranslate(Vector<String> t1, Vector<String> t2) {
		boolean b = true;
		boolean tmp = false;
		if (t1.size() == t2.size()) {
			for (int i = 0; i < t1.size(); i++) {
				for (int j = 0; j < t2.size(); j++) {
					tmp = tmp || t1.get(i).equals(t2.get(j));
				}
				b = b && tmp;
				tmp = false;
			}
			return b;
		} else {
			return false;
		}
	}

	/**
	 * Test si translate et buildModel effectuent correctement
	 * la traduction et la lecture d'un modele
	 * @param model
	 */
	public final void testTranslate(IModel model) {
		Vector<String> translate, cami, newBuild;

		translate = model.translate();

		cami = new Vector<String>();
		for (int i = 0; i < translate.size(); i++) {
			cami.addElement(translate.get(i));
		}
		try {
			IModel modelOut = new Model(cami, new CamiTranslator());
			newBuild = modelOut.translate();

			assertTrue(compareTranslate(translate, newBuild));

		} catch (Exception e) {
			return;
		}
	}

	/** Test **/
	public final void test() {
		IModel model = new Model(new CamiTranslator());
		INode node1 = new Node("place");
		INode node2 = new Node("place");
		IArc arc = new Arc("Arc");

		try {
		model.addNode(node1);
		model.addNode(node1);
		arc.setStartingNode(node1);
		arc.setEndingNode(node2);

		testTranslate(model);

		} catch (ModelException e) {
			e.toString();
		}
	}
}
