package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.lang.reflect.Array;
import java.util.Vector;

import junit.framework.TestCase;

/*Scenario de test:
 * Le nombre de tour effectue est specifie par la variable "MAXLOOP"
 * Le nombre d'arc a supprimer par est specifie par la variable "NBREMOVE"
 *
 * Ajout d'un arc de facon correcte ou non aleatoirement
 * Si on ne peut ajouter un arc alors on tente d'ajouter un noeud et un arc dont les identifiants
 * existe deja dans le modele
 *
 * Tous les 8 tours, nb_remove arcs sont supprimes
 *
 * Tous les 15 tours 1 noeud choisi aleatoirement est supprime.
 *
 * Tous les 10 tours on effectue un translate et un buildmodel (export/import) afin d'en comparer les sorties
 * tout en comparant l'ensemble de leurs noeuds et arcs des 2 models
 * Lors de la suppression d'un arc, on compare le model avant la suppression au model apres la suppression
 *
 * A chaque ajout d'un arc on ajoute aleatoirement un attribut au model, a un noeud ou a une arc
 *
 * */

public class TestModel extends TestCase {

	static final int MAXLOOP = 500;
	static final int NBREMOVE = 2;
	static final int RANDOM = 4;

	private int tour = 1;
	private Model model = new Model();

	private int idNode1, idNode2, idArc;

	/** switchArcOk regroupe les ajouts d'arc realisable * */
	public final void switchArcOK(IArc arc) {

		int actionOK = (int) (Math.random() * RANDOM);
		int i;

		// Creation des noeuds source et cible
		INode node1 = new Node("Node");
		INode node2 = new Node("Node");

		try {
			switch(actionOK) {

				// Ajout de 2 noeuds relies par un arc
				case 0:

					// Affectation des noeuds de l'arc
					arc.setStartingNode(node1);
					arc.setEndingNode(node2);

					// Test si le prochain id est dans la liste
					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout du premier noeud
					model.addNode(node1);

					// Test si l'id est bien present dans la liste des id
					idNode1 = node1.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idNode1)));

					assertEquals(idNode1, model.getMaxId());

					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout du second noeud
					model.addNode(node2);

					// Test si l'id est bien present dans la liste des id
					idNode2 = node2.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idNode2)));

					assertEquals(idNode2, model.getMaxId());

					// Test la presence des noeuds dans le modele
					assertEquals(node1, model.getANode(idNode1));
					assertEquals(node2, model.getANode(idNode2));

					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout de l'arc
					model.addArc(arc);

					// Test si l'id est bien present dans la liste des id
					idArc = arc.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
					assertEquals(idArc, model.getMaxId());

					break;

				// Ajout d'un noeud source relie a un noeud deja existant
				case 1:
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					// Creation du noeud source
					i = (int) (Math.random() * model.getListOfNodeSize());

					// Affectation des noeuds de l'arc
					arc.setStartingNode(node1);
					arc.setEndingNode(model.getNthNode(i));

					// Test id
					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout du noeud au model
					model.addNode(node1);

					// Test id et presence du noeud dans le modele
					idNode1 = node1.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idNode1)));
					assertEquals(idNode1, model.getMaxId());

					assertEquals(node1, model.getANode(idNode1));

					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout de l'arc
					model.addArc(arc);

					// Test id
					idArc = arc.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
					assertEquals(idArc, model.getMaxId());

					break;

					// Ajout d'un noeud cible relie a un noeud deja present dans le modele
				case 2:
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					i = (int) (Math.random() * model.getListOfNodeSize());

					// Affectation des noeuds de l'arc
					arc.setStartingNode(model.getNthNode(i));
					arc.setEndingNode(node2);

					// Test id
					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout du noeud au model
					model.addNode(node2);

					// Test id et presence du noeud dans le modele
					idNode2 = node2.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idNode2)));
					assertEquals(idNode2, model.getMaxId());

					assertEquals(node2, model.getANode(idNode2));

					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout de l'arc
					model.addArc(arc);

					// Test id
					idArc = arc.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
					assertEquals(idArc, model.getMaxId());
					break;

					// Ajout d'un arc dont les noeuds sont deja present dans le modele (choisis aleatoirement)
				case 3:
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					i = (int) (Math.random() * model.getListOfNodeSize());

					// Affectation des noeuds de l'arc
					arc.setStartingNode(model.getNthNode(i));
					i = (int) (Math.random() * model.getListOfNodeSize());
					arc.setEndingNode(model.getNthNode(i));

					// Test id
					assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId() + 1)));

					// Ajout de l'arc
					model.addArc(arc);

					// Test id
					idArc = arc.getId();
					assertTrue(model.getListOfId().contains(Integer.valueOf(idArc)));
					assertEquals(idArc, model.getMaxId());
					break;

				default:
					break;
			}
		} catch (ModelException e) {
			e.toString();
		}
	}

	/** switchArcNull regroupe les ajouts d'arc impossible a realiser * */
	public final void switchArcNull(IArc arc) {

		int actionNull = (int) (Math.random() * RANDOM);
		int i;

		INode node1 = new Node("Node");
		INode node2 = new Node("Node");

		try {
			switch(actionNull) {
				// Ajout d'un arc dont les noeuds ne sont present dans le modele
				case 0:

					arc.setStartingNode(node1);
					arc.setEndingNode(node2);

					idNode1 = node1.getId();
					idNode2 = node2.getId();

					assertTrue(node1 != model.getANode(idNode1));
					assertTrue(node2 != model.getANode(idNode2));

					model.addArc(arc);
					break;

					// Ajout d'un arc dont le noeud source n'est pas present
					// dans le modele
				case 1:
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(node1);
					arc.setEndingNode(model.getNthNode(i));

					idNode1 = node1.getId();
					assertTrue(model.getANode(idNode1) == null);

					model.addArc(arc);
					break;

					// Ajout d'un arc dont le noeud cible n'est pas present dans le modele
				case 2:
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(model.getNthNode(i));
					arc.setEndingNode(node2);

					idNode2 = node2.getId();
					assertTrue(model.getANode(idNode2) == null);

					model.addArc(arc);
					break;

					// Ajout d'un arc dont le noeud cible et le noeud source ne sont pas presents dans le modele
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
	 * Ajoute aleatoirement un attribut quelconque a une des elements du modele
	 * ou au modele lui meme
	 */
	public final void aleaAttribute() {

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

	/**
	 * Verifie si toutes les valeurs du tableau t1 sont dans le tableau t2 et
	 * inversement *
	 */
	public final boolean compareTab(String[] t1, String[] t2) {
		boolean b = true;
		boolean tmp = false;
		if (Array.getLength(t1) == Array.getLength(t2)) {
			for (int i = 0; i < Array.getLength(t1); i++) {
				for (int j = 0; j < Array.getLength(t2); j++) {
					tmp = tmp || t1[i].equals(t2[j]);
				}
				b = b && tmp;
				tmp = false;
			}
			return b;
		} else {
			return false;
		}
	}

	/** SCENARIO * */
	public final void testScenario() {
		TestModelPI pi = new TestModelPI();
		String[] translate;
		String[] newBuild = new String[1];
		Vector<String> cami;

		// Creation du modele
		model.setFormalism("net");
		String[] attributs = {"declaration", "author(s)", "version", "project"};
		String[] valeurs = {"a", "c", "1.0", "d"};
		for (int i = 0; i < 4; i++) {
			String value = new String();
			value = valeurs[i];
			IAttribute attr = new Attribute(attributs[i], value, 1);
			model.addAttribute(attr);

		}

		int actionAlea; // represente l'action a effectuer (Ajout d'un arc correct ou non)

		// Effectue le scenarion sur max_tour de boucle
		while (tour <= MAXLOOP) {

			actionAlea = (int) (Math.random() * 2);
			IArc arc = new Arc("Arc");

			// Tour de test pour buildModel/translate
			if ((tour % 10) == 0) {

				translate = model.translate();

				cami = new Vector<String>();
				for (int i = 0; i < Array.getLength(translate); i++) {
					cami.addElement(translate[i]);
				}
				try {
					Model modelOut = new Model(cami);
					newBuild = modelOut.translate();

					assertTrue(compareTab(translate, newBuild));

					for (int a = 0; a < model.getListOfId().size(); a++) {
						int id = model.getListOfId().get(a);
						if (!(model.getAnArc(id) == null)) {

							assertTrue(compareTab(model.getAnArc(id).translate(), modelOut.getAnArc(id).translate()));

							// Test avec erreur du translate entre 2 arcs
							// differents
							if (model.getListOfArcSize() > 1) {
								int anth = (int) (Math.random() * model.getListOfArcSize());
								while (model.getNthArc(anth).getId() == id) {
									anth = (int) (Math.random() * model.getListOfArcSize());
								}

								assertFalse(compareTab(model.getAnArc(id).translate(), model.getNthArc(anth).translate()));
							}
						} else if (!(model.getANode(id) == null)) {

							assertTrue(compareTab(model.getANode(id).translate(), model.getANode(id).translate()));

							// Test avec erreur du translate entre 2 arcs
							// differents
							if (model.getListOfNodeSize() > 1) {

								int nth = (int) (Math.random() * model.getListOfNodeSize());

								while (model.getNthNode(nth).getId() == id) {
									nth = (int) (Math.random() * model.getListOfNodeSize());
								}

								assertFalse(compareTab(model.getANode(id).translate(), modelOut.getNthNode(nth).translate()));
							}
						}
					}

				} catch (Exception e) {
					return;
				}
			} else {

				// Tour de test pour l'ajout
				if ((tour % 8) == 0) {
					int ind = 0;
					int i = 0;

					// On retire nb_remove arcs
					while (i < NBREMOVE) {

						translate = model.translate();

						if (model.getListOfArcSize() == 0) {
							break;
						}

						ind = (int) (Math.random() * model.getListOfArcSize());
						arc = model.getNthArc(ind);
						if (!(arc == null)) {

							idArc = arc.getId();

							INode node1 = arc.getStartingNode();
							INode node2 = arc.getEndingNode();

							idNode1 = node1.getId();
							idNode2 = node2.getId();

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

							i = i + 1;

							newBuild = model.translate();

							assertFalse(compareTab(newBuild, translate));
						}
					}
				} else {
					if ((tour % 15) == 0 && !(model.getListOfNodeSize() == 0)) {

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

						// Ajout d'un PI a un arc
						if (model.getListOfArcSize() != 0) {
							int x = (int) (Math.random() * 50);
							int y = (int) (Math.random() * 50);
							int nthArc = (int) (Math.random() * model.getListOfArcSize());

							pi.aleaAddPI(model.getNthArc(nthArc), x, y);
						}
					} else {

						// Ajout autorise
						if (actionAlea == 0) {


							switchArcOK(arc);

							if (!(model.getListOfNodeSize() == 0)) {

								// On teste que les listes d'arc ont ete
								// correctement mises a jour
								INode node1 = arc.getStartingNode();
								INode node2 = arc.getEndingNode();

								idNode1 = arc.getStartingNode().getId();
								idNode2 = arc.getEndingNode().getId();
								idArc = arc.getId();

								assertTrue(model.getAnArc(idArc) != null);
								assertTrue(node1.getListOfOutputArc().contains(arc));
								assertTrue(node2.getListOfInputArc().contains(arc));

								if (!(model.getListOfArcSize() == 0)) {

									// On ajoute aleatoirement un attribut a un element du modele
									aleaAttribute();

									// On retire un PI a un arc pris au hasard
									int x = (int) (Math.random() * 50);
									int y = (int) (Math.random() * 50);
									int nthArc = (int) (Math.random() * model.getListOfArcSize());

									pi.aleaRemovePI(model.getNthArc(nthArc), x, y);
								}
							}

						} else {

							// Action non autorisee

							switchArcNull(arc);

							if (!(model.getListOfNodeSize() == 0)) {

								// On test si l'arc a ete ajoute au model
								idArc = arc.getId();
								assertTrue(model.getAnArc(idArc) == null);

								// Ajout d'un noeud dont
								// l'identifiant existe deja
								try {

									INode nodeCorrupt = new Node("node");
									int alea = (int) (Math.random() * model.getListOfId().size());

									int id = model.getListOfId().get(alea);

									nodeCorrupt.setId(id);

									model.addNode(nodeCorrupt);

									// Si une exception n'est pas leve alors on
									// aura une erreur d'assertion
									assertTrue(false);

								} catch (ModelException e) {
									assertTrue(true);
								}

								// Ajout d'un arc dont
								// l'identifiant existe deja
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

						}
					}
				}
			}
			tour++;
		}
	}
}
