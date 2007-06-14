package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.util.Vector;
import java.lang.reflect.Array;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

/*Scenario de test:
 * Le nombre de tour effectué est spécifié par la variable "max_tour"
 * Le nombre d'arc à supprimer par est spécifié par la variable "nb_remove"
 * 
 * Ajout d'un arc de facon correcte ou non aléatoirement
 * Si on ne peut ajouter un arc alors on tente d'ajouter un noeud et un arc dont les identifiants
 * existe deja dans le modele
 * 
 * Tous les 8 tours, un ou plusieurs arcs sont ajoutés à la liste des arcs à retirer 
 * les arcs présents dans la liste sont alors supprimés
 * 
 * Tous les 15 tours 1 noeud choisi aléatoirement est supprimé.
 * 
 * Tous les 10 tours on effectue un translate et un buildmodel (export/import) afin d'en comparer les sorties
 * tout en comparant l'ensemble de leurs noeuds et arcs des 2 models
 * Lors de la suppression d'un arc, on compare le model avant la suppression au model après la suppression
 * 
 * A chaque ajout d'un arc on ajoute aleatoirement un attribut au model, à un noeud ou à une arc
 * 
 * */

public class TestModel extends TestCase {
	int max_tour = 500;

	int nb_remove = 2;

	int tour = 1;

	Model model = new Model();

	int id_node1, id_node2, id_arc;

	/* switchArcOk regroupe les ajouts d'arc réalisable */
	public void switchArcOK(IArc arc) {

		int action_ok;

		action_ok = (int) (Math.random() * 4);
		try {
			switch(action_ok){

				/* Ajout de 2 noeuds reliés par un arc */
				case 0: {
					INode node1 = new Node("Node");
					INode node2 = new Node("Node");

					arc.setStartingNode(node1);
					arc.setEndingNode(node2);

					/* Test si le prochain id est dans la liste */
					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addNode(node1);
					id_node1 = node1.getId();

					/* Test si l'id est bien présent dans la liste des id */
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_node1)));
					assertEquals(id_node1, model.getMaxId());

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addNode(node2);
					id_node2 = node2.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_node2)));
					assertEquals(id_node2, model.getMaxId());

					assertEquals(node1, model.getANode(id_node1));
					assertEquals(node2, model.getANode(id_node2));

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addArc(arc);
					id_arc = arc.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_arc)));
					assertEquals(id_arc, model.getMaxId());

					System.out.println("Ajout de l'arc " + arc.getId()
							+ " et des noeuds " + id_node1 + " et " + id_node2
							+ "\n");
					break;
				}

					/* Ajout d'un noeud source relié à un noeud déjà existant */
				case 1: {
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					INode node1 = new Node("Node");

					int i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(node1);
					arc.setEndingNode(model.getNthNode(i));

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addNode(node1);
					id_node1 = node1.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_node1)));
					assertEquals(id_node1, model.getMaxId());

					assertEquals(node1, model.getANode(id_node1));

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addArc(arc);
					id_arc = arc.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_arc)));
					assertEquals(id_arc, model.getMaxId());

					System.out.println("Ajout de l'arc " + arc.getId()
							+ " et du noeud source " + id_node1
							+ " relié au noeud " + id_node2 + "\n");
					break;
				}

					/*
					 * Ajout d'un noeud cible relié à un noeud déjà présent dans
					 * le modèle
					 */
				case 2: {

					if (model.getListOfNodeSize() == 0) {
						break;
					}

					INode node2 = new Node("Node");

					int i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(model.getNthNode(i));
					arc.setEndingNode(node2);

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addNode(node2);
					id_node2 = node2.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_node2)));
					assertEquals(id_node2, model.getMaxId());

					assertEquals(node2, model.getANode(id_node2));

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addArc(arc);
					id_arc = arc.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_arc)));
					assertEquals(id_arc, model.getMaxId());

					System.out.println("Ajout de l'arc " + arc.getId()
							+ " et du noeud cible " + id_node2
							+ " relié au noeud " + id_node2 + "\n");
					break;
				}

				case 3: {

					if (model.getListOfNodeSize() == 0) {
						break;
					}

					int i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(model.getNthNode(i));
					i = (int) (Math.random() * model.getListOfNodeSize());
					arc.setEndingNode(model.getNthNode(i));

					assertFalse(model.getListOfId().contains(
							Integer.valueOf(model.getMaxId() + 1)));
					model.addArc(arc);
					id_arc = arc.getId();
					assertTrue(model.getListOfId().contains(
							Integer.valueOf(id_arc)));
					assertEquals(id_arc, model.getMaxId());

					System.out.println("Ajout de l'arc " + arc.getId()
							+ " relié aux noeuds "
							+ arc.getStartingNode().getId() + " et "
							+ arc.getEndingNode().getId() + " du modele\n");
					break;

				}
				default: {
					break;
				}
			}
		} catch (SyntaxErrorException e) {
			e.toString();
		}
	}

	/* switchArcNull regroupe les ajouts d'arc impossible à réaliser */
	public void switchArcNull(IArc arc) {

		int action_null;

		action_null = (int) (Math.random() * 4);
		try {
			switch(action_null){
				// Ajout d'un arc dont les noeuds ne sont présent dans le modèle
				case 0: {
					INode node1 = new Node("Node");
					INode node2 = new Node("Node");

					arc.setStartingNode(node1);
					arc.setEndingNode(node2);

					id_node1 = node1.getId();
					id_node2 = node2.getId();

					assertTrue(node1 != model.getANode(id_node1));
					assertTrue(node2 != model.getANode(id_node2));

					model.addArc(arc);
					break;
				}

					/*
					 * Ajout d'un arc dont le noeud source n'est pas présent
					 * dans le modèle
					 */
				case 1: {
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					INode node1 = new Node("Node");

					int i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(node1);
					arc.setEndingNode(model.getNthNode(i));

					id_node1 = node1.getId();
					assertTrue(model.getANode(id_node1) == null);

					model.addArc(arc);
					break;
				}
					/*
					 * Ajout d'un arc dont le noeud cible n'est pas présent dans
					 * le modèle
					 */
				case 2: {
					if (model.getListOfNodeSize() == 0) {
						break;
					}

					INode node2 = new Node("Node");

					int i = (int) (Math.random() * model.getListOfNodeSize());

					arc.setStartingNode(model.getNthNode(i));
					arc.setEndingNode(node2);

					id_node2 = node2.getId();
					assertTrue(model.getANode(id_node2) == null);

					model.addArc(arc);
					break;
				}

				case 3: {
					model.addArc(arc);
					break;
				}
				default: {
					break;
				}
			}

		} catch (SyntaxErrorException e) {
			e.toString();
		}
	}

	public void aleaAttribute() {
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

	/* Affiche le contenu du tableau t */
	public void affiche_translate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}

	/* Verifie si toutes les valeurs de t1 sont dans t2 */
	public boolean compareTab(String[] t1, String[] t2) {
		boolean b = true;
		boolean tmp = false;
		if (Array.getLength(t1) == Array.getLength(t2)) {
			for (int i = 0; i < Array.getLength(t1); i++) {
				for (int j = 0; j < Array.getLength(t2); j++) {
					tmp = tmp || t1[i].equals(t2[j]);
				}
				// ligne a decommenter afin de connaitre les lignes différentes
				// if(!tmp){System.out.println("Erreur Translate: ligne "+i);}
				b = b && tmp;
				tmp = false;
			}
			return b;
		} else {
			return false;
		}
	}

	public void testScenario() {
		String[] translate;
		String[] new_build = new String[1];
		Vector<String> cami;

		model.setFormalism("net");
		String[] attributs = { "declaration", "author(s)", "version", "project" };
		String[] valeurs = { "a", "c", "1.0", "d" };
		for (int i = 0; i < 4; i++) {
			String value = new String();
			value = valeurs[i];
			IAttribute attr = new Attribute(attributs[i], value, 1);
			model.addAttribute(attr);

		}
		int action_alea;

		while (tour <= max_tour) {
			action_alea = (int) (Math.random() * 2);
			IArc arc = new Arc("Arc");

			System.out.println("Tour:" + tour);

			if ((tour % 10) == 0) {

				translate = model.translate();
				affiche_translate(translate);

				cami = new Vector<String>();
				for (int i = 0; i < Array.getLength(translate); i++) {
					cami.addElement(translate[i]);
				}
				System.out.println("\nBuildModel");
				try {
					Model model_out = new Model(cami);
					new_build = model_out.translate();
					affiche_translate(new_build);

					assertTrue(compareTab(translate, new_build));

					for (int a = 0; a < model.getListOfId().size(); a++) {
						int id = model.getListOfId().get(a);
						if (!(model.getAnArc(id) == null)) {

							assertTrue(compareTab(model.getAnArc(id)
									.translate(), model_out.getAnArc(id)
									.translate()));

							/*
							 * Test avec erreur du translate entre 2 arcs
							 * differents
							 */
							if (model.getListOfArcSize() > 1) {
								int anth = (int) (Math.random() * model
										.getListOfArcSize());
								while (model.getNthArc(anth).getId() == id) {
									anth = (int) (Math.random() * model
											.getListOfArcSize());
								}

								assertFalse(compareTab(model.getAnArc(id)
										.translate(), model.getNthArc(anth)
										.translate()));
							}
						} else if (!(model.getANode(id) == null)) {

							assertTrue(compareTab(model.getANode(id)
									.translate(), model.getANode(id)
									.translate()));

							/*
							 * Test avec erreur du translate entre 2 arcs
							 * differents
							 */
							if (model.getListOfNodeSize() > 1) {
								int nth = (int) (Math.random() * model
										.getListOfNodeSize());
								while (model.getNthNode(nth).getId() == id) {
									nth = (int) (Math.random() * model
											.getListOfNodeSize());
								}

								assertFalse(compareTab(model.getANode(id)
										.translate(), model_out.getNthNode(nth)
										.translate()));
							}
						}
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}
			} else {

				if ((tour % 8) == 0) {
					int ind = 0;
					int i = 0;

					while (i < nb_remove) {

						translate = model.translate();

						if (model.getListOfArcSize() == 0) {
							System.out.println("Aucun Arc a retirer\n");
							break;
						}

						ind = (int) (Math.random() * model.getListOfArcSize());
						arc = model.getNthArc(ind);
						if (!(arc == null)) {

							id_arc = arc.getId();

							INode node1 = arc.getStartingNode();
							INode node2 = arc.getEndingNode();
							id_node1 = node1.getId();
							id_node2 = node2.getId();

							/* arc présent dans la liste */
							assertTrue(model.getListOfId().contains(
									Integer.valueOf(id_arc)));
							model.removeArc(arc);

							/* arc plus présent dans la liste des arcs du modèle */
							assertTrue(model.getAnArc(id_arc) == null);

							/*
							 * arc plus présent dans la liste des noeuds de sa
							 * cible et de sa source
							 */
							assertFalse(node1.getListOfOutputArc()
									.contains(arc));
							assertFalse(node2.getListOfInputArc().contains(arc));

							/* arc plus présent dans la liste des identifiants */
							assertFalse(model.getListOfId().contains(
									Integer.valueOf(id_arc)));

							/*
							 * Test translate/buildmodel en cas de model
							 * differents
							 */

							System.out.println("Retrait d'arcs\narc_id:"
									+ id_arc + "\nid_node1:" + id_node1
									+ " id_node2:" + id_node2 + "\nMaxId:"
									+ model.getMaxId() + "\n");
							i = i + 1;

							new_build = model.translate();

							// affiche_translate(translate);
							// System.out.println("\nTranslate erronné");
							// affiche_translate(new_build);
							assertFalse(compareTab(new_build, translate));
						}
					}
				} else {
					if ((tour % 15) == 0 && !(model.getListOfNodeSize() == 0)) {
						int id_node;
						int ind = 0;
						INode node_remove;
						ind = (int) (Math.random() * model.getListOfNodeSize());
						Vector<IArc> outArc;
						Vector<IArc> inArc;

						node_remove = model.getNthNode(ind);
						id_node = node_remove.getId();

						/*
						 * Copie du arcs sortant et entrant afin de recupèrer
						 * les id après la suppression du noeud
						 */
						outArc = new Vector<IArc>(node_remove
								.getListOfOutputArc());
						inArc = new Vector<IArc>(node_remove
								.getListOfInputArc());

						model.removeNode(node_remove);

						assertTrue(model.getANode(id_node) == null);

						/* Test des la mise à jour des listes d'arc */
						for (int i = 0; i < outArc.size(); i++) {
							IArc a = outArc.get(i);
							INode n = a.getEndingNode();
							int a_id = a.getId();
							assertTrue(model.getAnArc(a_id) == null);
							assertFalse(n.getListOfInputArc().contains(a));
						}

						for (int i = 0; i < inArc.size(); i++) {
							IArc a = inArc.get(i);
							INode n = a.getStartingNode();
							int a_id = a.getId();
							assertTrue(model.getAnArc(a_id) == null);
							assertFalse(n.getListOfOutputArc().contains(a));
						}
						System.out.println("Retrait de noeud\nid_node:"
								+ id_node + "\n");
					} else {
						/* Ajout autorisé */
						if (action_alea == 0) {
							System.out.println("Cas : Ajout autorisé");
							switchArcOK(arc);
							if (!(model.getListOfNodeSize() == 0)) {

								INode node1 = arc.getStartingNode();
								INode node2 = arc.getEndingNode();
								id_node1 = arc.getStartingNode().getId();
								id_node2 = arc.getEndingNode().getId();
								id_arc = arc.getId();

								assertTrue(model.getAnArc(id_arc) != null);
								assertTrue(node1.getListOfOutputArc().contains(
										arc));
								assertTrue(node2.getListOfInputArc().contains(
										arc));
								if (!(model.getListOfArcSize() == 0)) {
									aleaAttribute();
								}
							} else {
								System.out
										.println("ListOfNode vide: l'ajout d'un arc ne peut s'effectuer\n");
							}
						} else {
							/* Action non autorisée */
							System.out.println("Cas : Ajout non conforme");
							switchArcNull(arc);
							if (!(model.getListOfNodeSize() == 0)) {
								id_arc = arc.getId();
								assertTrue(model.getAnArc(id_arc) == null);

								/*
								 * Ajout d'un noeud et d'un arc dont
								 * l'identifiant existe deja
								 */
								try {
									INode nodeCorrupt = new Node("node");
									int alea = (int) (Math.random() * model
											.getListOfId().size());
									int id = model.getListOfId().get(alea);
									nodeCorrupt.setId(id);
									model.addNode(nodeCorrupt);
									assertTrue(false);
								} catch (SyntaxErrorException e) {
									System.out.println(e.toString());
									assertTrue(true);
								}

								try {
									int alea = (int) (Math.random() * model
											.getListOfId().size());
									int id = model.getListOfId().get(alea);
									IArc arcCorrupt = new Arc("arc", id);

									alea = (int) (Math.random() * model
											.getListOfNodeSize());
									INode node1 = model.getNthNode(alea);

									alea = (int) (Math.random() * model
											.getListOfNodeSize());
									INode node2 = model.getNthNode(alea);

									arcCorrupt.setStartingNode(node1);

									arcCorrupt.setEndingNode(node2);

									model.addArc(arcCorrupt);
									assertTrue(false);
								} catch (SyntaxErrorException e) {
									System.out.println(e.toString());
									assertTrue(true);
								}

							} else {
								System.out.println("ListOfNode vide\n");
							}

						}
					}
				}
			}
			tour++;
		}
		System.out.println("FIN DES TESTS!");
	}
}
