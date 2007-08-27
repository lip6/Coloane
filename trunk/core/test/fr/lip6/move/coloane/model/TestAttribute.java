package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;


import junit.framework.TestCase;


public class TestAttribute extends TestCase {

	/**
	 * Ajoute aleatoirement un attribut quelconque a un des elements du modele
	 * ou au modele lui-meme
	 * @param model
	 */
	public final void aleaAttribute(IModel model) {

		int alea = (int) (Math.random() * model.getListOfId().size());
		int id = model.getListOfId().get(alea);

		if (!(model.getAnArc(id) == null)) {

			IAttribute attrs = new Attribute("valuation", "new", 0);
			model.getAnArc(id).addAttribute(attrs);

		} else if (!(model.getANode(id) == null)) {

			IAttribute attrs = new Attribute("name", "new", 0);
			model.getANode(id).addAttribute(attrs);

		} else if (id == 1) {

			IAttribute attrs = new Attribute("model", "new", 0);
			model.addAttribute(attrs);
		}
	}

	/** Test **/
	public final void test() {
		TestModelTranslate testTranslate = new TestModelTranslate();
		IModel model = new Model(new CamiTranslator());
		INode node1 = new Node("place");
		INode node2 = new Node("place");
		IArc arc = new Arc("Arc");

		try {
			model.addNode(node1);
			model.addNode(node1);
			arc.setStartingNode(node1);
			arc.setEndingNode(node2);

			aleaAttribute(model);

			testTranslate.testTranslate(model);

			} catch (ModelException e) {
				e.toString();
			}
	}
}
