package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.model.interfaces.IArc;
import fr.lip6.move.coloane.interfaces.model.interfaces.INode;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import junit.framework.TestCase;

public class TestArc extends TestCase {
	static final int RANDOM = 4;

	/** AJOUT D'UN ARC VALIDE **/

	/**
	 * Ajout de 2 noeuds relies par un arc
	 * @param model
	 * @param arc
	 */
	public final void add1Arc2NodeOK(IModel model, IArc arc) {
		int idNode1, idNode2, idArc;

		// Creation des noeuds source et cible
		INode node1 = new Node("Node");
		INode node2 = new Node("Node");

		// Affectation des noeuds de l'arc
		arc.setStartingNode(node1);
		arc.setEndingNode(node2);

		// Test si le prochain id est dans la liste
		assertFalse(model.getListOfId().contains(
				Integer.valueOf(model.getMaxId() + 1)));
		try {
			// Ajout du premier noeud
			model.addNode(node1);

			// Test si l'id est bien present dans la liste des id
			idNode1 = node1.getId();
			assertTrue(model.getListOfId().contains(Integer.valueOf(idNode1)));
			assertEquals(idNode1, model.getMaxId());
			assertFalse(model.getListOfId().contains(
					Integer.valueOf(model.getMaxId() + 1)));

			// Ajout du second noeud
			model.addNode(node2);

			// Test si l'id est bien present dans la liste des id
			idNode2 = node2.getId();
			assertTrue(model.getListOfId().contains(Integer.valueOf(idNode2)));
			assertEquals(idNode2, model.getMaxId());
			assertFalse(model.getListOfId().contains(
					Integer.valueOf(model.getMaxId() + 1)));

			// Test la presence des noeuds dans le modele
			assertEquals(node1, model.getANode(idNode1));
			assertEquals(node2, model.getANode(idNode2));
			assertFalse(model.getListOfId().contains(
					Integer.valueOf(model.getMaxId() + 1)));

			// Ajout de l'arc
			model.addArc(arc);

		} catch (ModelException e) {
			e.toString();
		}

		// Test si l'id est bien present dans la liste des id
		idArc = arc.getId();
		assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
		assertEquals(idArc, model.getMaxId());
	}

	/**
	 * Ajout d'un noeud source relie a un noeud deja existant Test d'unicite des
	 * identifiants
	 * @param model
	 * @param arc
	 */
	public final void add1Arc1SourceOK(IModel model, IArc arc) {

		int ind, idNode1, idArc;

		// Creation des noeuds source et cible
		INode node1 = new Node("Node");

		// Creation du noeud source
		ind = (int) (Math.random() * model.getListOfNodeSize());

		// Affectation des noeuds de l'arc
		arc.setStartingNode(node1);
		arc.setEndingNode(model.getNthNode(ind));

		// Test id
		assertFalse(model.getListOfId().contains(
				Integer.valueOf(model.getMaxId() + 1)));
		try {
			// Ajout du noeud au model
			model.addNode(node1);

			// Test id et presence du noeud dans le modele
			idNode1 = node1.getId();
			assertTrue(model.getListOfId().contains(Integer.valueOf(idNode1)));
			assertEquals(idNode1, model.getMaxId());
			assertEquals(node1, model.getANode(idNode1));
			assertFalse(model.getListOfId().contains(
					Integer.valueOf(model.getMaxId() + 1)));

			// Ajout de l'arc
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}
		// Test id
		idArc = arc.getId();
		assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
		assertEquals(idArc, model.getMaxId());
	}

	/**
	 * Ajout d'un arc dont le noeud cible n'est pas present dans le modele Test
	 * d'unicite des identifiants
	 * @param model
	 * @param arc
	 */
	public final void add1Arc1TargetOK(IModel model, IArc arc) {

		int ind, idNode2, idArc;

		// Creation des noeuds source et cible
		INode node2 = new Node("Node");

		// Creation du noeud cible
		ind = (int) (Math.random() * model.getListOfNodeSize());

		// Affectation des noeuds de l'arc
		arc.setEndingNode(node2);
		arc.setStartingNode(model.getNthNode(ind));

		// Test id
		assertFalse(model.getListOfId().contains(
				Integer.valueOf(model.getMaxId() + 1)));
		try {
			// Ajout du noeud au model
			model.addNode(node2);

			// Test id et presence du noeud dans le modele
			idNode2 = node2.getId();
			assertTrue(model.getListOfId().contains(Integer.valueOf(idNode2)));
			assertEquals(idNode2, model.getMaxId());
			assertEquals(node2, model.getANode(idNode2));
			assertFalse(model.getListOfId().contains(
					Integer.valueOf(model.getMaxId() + 1)));

			// Ajout de l'arc
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}
		// Test id
		idArc = arc.getId();
		assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
		assertEquals(idArc, model.getMaxId());
	}

	/**
	 * Ajout d'un arc dont les noeuds sont deja present dans le modele (choisis
	 * aleatoirement) Test d'unicite des identifiants
	 * @param model
	 * @param arc
	 */
	public final void add1ArcNoNodeOK(IModel model, IArc arc) {
		int ind, idArc;

		// Affectation des noeuds de l'arc
		ind = (int) (Math.random() * model.getListOfNodeSize());
		arc.setStartingNode(model.getNthNode(ind));

		ind = (int) (Math.random() * model.getListOfNodeSize());
		arc.setEndingNode(model.getNthNode(ind));

		// Test id
		assertFalse(model.getListOfId().contains(
				Integer.valueOf(model.getMaxId() + 1)));

		try {
			// Ajout de l'arc
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}

		// Test id
		idArc = arc.getId();
		assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
		assertEquals(idArc, model.getMaxId());
	}

	/**
	 * switchArcOk regroupe les ajouts d'arc realisable
	 * @param model
	 * @param arc
	 */
	public final void switchArcOK(IModel model, IArc arc) {

		int actionOK = (int) (Math.random() * RANDOM);

		switch (actionOK) {

		case 0:
			add1Arc2NodeOK(model, arc);
			break;

		case 1:
			if (model.getListOfNodeSize() == 0) {
				break;
			}
			add1Arc1SourceOK(model, arc);
			break;

		case 2:
			if (model.getListOfNodeSize() == 0) {
				break;
			}
			add1Arc1TargetOK(model, arc);
			break;

		case 3:
			if (model.getListOfNodeSize() == 0) {
				break;
			}
			add1ArcNoNodeOK(model, arc);
			break;

		default:
			break;
		}
	}


	/** AJOUT D'UN ARC NON VALIDE **/

	/**
	 * Ajout d'un arc dont les noeuds ne sont present dans le modele
	 * @param model
	 * @param arc
	 */
	public final void add1ArcNoNodeNull(IModel model, IArc arc) {
		int idNode1, idNode2;
		INode node1 = new Node("Node");
		INode node2 = new Node("Node");

		arc.setStartingNode(node1);
		arc.setEndingNode(node2);

		idNode1 = node1.getId();
		idNode2 = node2.getId();

		assertTrue(node1 != model.getANode(idNode1));
		assertTrue(node2 != model.getANode(idNode2));
		try {
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}
	}

	/**
	 * Ajout d'un arc dont le noeud source n'est pas present dans le modele
	 * @param model
	 * @param arc
	 */
	public final void add1ArcNoSourceNull(IModel model, IArc arc) {
		int ind, idNode1;
		INode node1 = new Node("Node");

		ind = (int) (Math.random() * model.getListOfNodeSize());

		arc.setStartingNode(node1);
		arc.setEndingNode(model.getNthNode(ind));

		idNode1 = node1.getId();
		assertTrue(model.getANode(idNode1) == null);
		try {
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}
	}

	/**
	 * * Ajout d'un arc dont le noeud cible n'est pas present dans le modele
	 * @param model
	 * @param arc
	 */
	public final void add1ArcNoTargetNull(IModel model, IArc arc) {
		int ind, idNode2;
		INode node2 = new Node("Node");

		ind = (int) (Math.random() * model.getListOfNodeSize());

		arc.setStartingNode(model.getNthNode(ind));
		arc.setEndingNode(node2);

		idNode2 = node2.getId();
		assertTrue(model.getANode(idNode2) == null);
		try {
			model.addArc(arc);
		} catch (ModelException e) {
			e.toString();
		}
	}

	/**
	 * switchArcNull regroupe les ajouts d'arc impossible a realiser
	 * @param model
	 * @param arc
	 */
	public final void switchArcNull(IModel model, IArc arc) {

		int actionNull = (int) (Math.random() * RANDOM);

		try {
			switch (actionNull) {

			case 0:
				add1ArcNoNodeNull(model, arc);
				break;

			case 1:
				if (model.getListOfNodeSize() == 0) {
					break;
				}
				add1ArcNoSourceNull(model, arc);
				break;

			case 2:
				if (model.getListOfNodeSize() == 0) {
					break;
				}
				add1ArcNoTargetNull(model, arc);
				break;

			// Ajout d'un arc n'ayant pas de noeud cible ou source
			case 3:
				model.addArc(arc);
				break;

			default:
				break;
			}
		} catch (ModelException e) {
			e.toString();
		}
	}

	/**
	 * Ajout d'un arc dont l'identifiant existe deja
	 * @param model
	 */
	public final void addArcWrongID(IModel model) {

		try {

			int alea = (int) (Math.random() * model.getListOfId().size());
			int id = model.getListOfId().get(alea);

			IArc arcCorrupt = new Arc("arc", id);

			alea = (int) (Math.random() * model.getListOfNodeSize());
			INode node1 = model.getNthNode(alea);

			alea = (int) (Math.random() * model.getListOfNodeSize());
			INode node2 = model.getNthNode(alea);

			arcCorrupt.setStartingNode(node1);
			arcCorrupt.setEndingNode(node2);

			model.addArc(arcCorrupt);

			// Si une exception n'est pas leve alors on
			// aura une erreur d'assertion
			assertTrue(false);

		} catch (ModelException e) {
			// sinon
			assertTrue(true);
		}
	}

	/** Retire d'un arc et effectue les assertions necessaires
	 * @param model
	 * @param arc
	 */
	public final void removeArc(IModel model) {

		int ind = (int) (Math.random() * model.getListOfArcSize());
		IArc arc = model.getNthArc(ind);

		if (!(arc == null)) {

			int idArc = arc.getId();

			INode node1 = arc.getStartingNode();
			INode node2 = arc.getEndingNode();

			// Arc present dans la liste
			assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
			try {
				model.removeArc(arc);
			} catch (ModelException e) {
				fail(e.toString());
			}

			// Arc plus present dans la liste des arcs du modele
			assertTrue(model.getAnArc(idArc) == null);

			// Arc plus present dans la liste des noeuds de sa
			// cible et de sa source

			assertFalse(node1.getListOfOutputArc().contains(arc));
			assertFalse(node2.getListOfInputArc().contains(arc));

			// Arc plus present dans la liste des identifiants
			assertFalse(model.getListOfId().contains(Integer.valueOf(idArc)));

		}
	}

	/** Ajoute un arc valide et effectue les tests necessaires
	 * @param model
	 * @param arc
	 */
	public final void addArcOK(IModel model, IArc arc) {
		switchArcOK(model, arc);

		if (!(model.getListOfNodeSize() == 0)) {

			// On teste que les listes d'arc ont ete
			// correctement mises a jour
			INode node1 = arc.getStartingNode();
			INode node2 = arc.getEndingNode();

			int idArc = arc.getId();

			assertTrue(model.getAnArc(idArc) != null);
			assertTrue(node1.getListOfOutputArc().contains(arc));
			assertTrue(node2.getListOfInputArc().contains(arc));
		}
	}

	/** Ajoute un arc non valide et effectue les tests necessaires
	 * @param model
	 * @param arc
	 */
	public final void addArcNull(IModel model, IArc arc) {

		switchArcNull(model, arc);

		if (!(model.getListOfNodeSize() == 0)) {
			// On test si l'arc a ete ajoute au model
			int idArc = arc.getId();
			assertTrue(model.getAnArc(idArc) == null);
		}
	}

	/** Test **/
	public final void test() {
		IModel model = new Model(new CamiTranslator());
		IArc arc = new Arc("arc1");

		addArcOK(model, arc);

		arc = new Arc("arc2");
		addArcNull(model, arc);

		removeArc(model);

		addArcWrongID(model);
	}
}
