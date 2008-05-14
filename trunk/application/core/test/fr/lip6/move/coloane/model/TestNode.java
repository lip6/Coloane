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

public class TestNode extends TestCase {

	/** Retire un noeud choisi aleatoirement et effectue les tests necessaires
	 * @param model
	 */
	public final void removeNode(IModel model) {
		if (model.getListOfNodeSize() != 0) {

			int idNode;
			int ind = 0;
			Vector<IArc> outArc;
			Vector<IArc> inArc;

			INode nodeRemove;

			ind = (int) (Math.random() * model.getListOfNodeSize());

			// Copie du arcs sortant et entrant afin de recuperer
			// les id apres la suppression du noeud
			nodeRemove = model.getNthNode(ind);
			idNode = nodeRemove.getId();

			outArc = new Vector<IArc>(nodeRemove.getListOfOutputArc());
			inArc = new Vector<IArc>(nodeRemove.getListOfInputArc());

			// Suppresion du noeud elu
			try {
				model.removeNode(nodeRemove);
			} catch (ModelException e) {
				fail(e.toString());
			}

			// On test que le noeud n'est plus present
			assertTrue(model.getANode(idNode) == null);

			// Test des la mise a jour des listes d'arc
			for (int i = 0; i < outArc.size(); i++) {
				IArc a = outArc.get(i);
				INode n = a.getEndingNode();
				int aId = a.getId();

				assertTrue(model.getAnArc(aId) == null);
				assertFalse(n.getListOfInputArc().contains(a));
			}

			for (int i = 0; i < inArc.size(); i++) {
				IArc a = inArc.get(i);
				INode n = a.getStartingNode();
				int aId = a.getId();

				assertTrue(model.getAnArc(aId) == null);
				assertFalse(n.getListOfOutputArc().contains(a));
			}
		}
	}

	/** Ajoute un noeud dont l'identifiant existe deja dans le modele
	 * @param model
	 */
	public final void addNodeWrongID(IModel model) {
		try {

			INode nodeCorrupt = new Node("place");
			int alea = (int) (Math.random() * model
					.getListOfId().size());

			int id = model.getListOfId().get(alea);

			nodeCorrupt.setId(id);

			model.addNode(nodeCorrupt);

			// Si une exception n'est pas leve alors on
			// aura une erreur d'assertion
			assertTrue(false);

		} catch (ModelException e) {
			assertTrue(true);
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

		addNodeWrongID(model);
		removeNode(model);

		} catch (ModelException e) {
			e.toString();
		}
	}
}
