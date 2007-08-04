package fr.lip6.move.coloane.model.tests;

import junit.framework.TestCase;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.model.Arc;
import fr.lip6.move.coloane.model.Model;
import fr.lip6.move.coloane.model.Node;

/*Scenario de test:
 * Ajout d'un arc de facon correcte ou non aléatoirement
 * Tous les 8 tours, un ou plusieurs arcs sont ajoutés à la liste des arcs à retirer 
 * les arcs présents dans la liste sont alors supprimés
 * Le nombre de tour effectué est spécifié par la variable "max_tour"
 * Le nombre d'arc à supprimer est spécifié par la variable "nb_remove"
 * */

public class TestModel_Arc extends TestCase {

	int max_tour = 500;

	int nb_remove = 2;

	int tour = 1;

	Model model = new Model();

	int id_node1, id_node2, id_arc;

	public void switchArcOK(IArc arc) {

		int action_ok;

		action_ok = (int) (Math.random() * 4);
		try {
			switch(action_ok){
			// Ajout de 2 noeuds reliés par un arc
				case 0: {
					INode node1 = new Node("Node");
					INode node2 = new Node("Node");
	
					arc.setStartingNode(node1);
					arc.setEndingNode(node2);
	
					model.addNode(node1);
					model.addNode(node2);
	
					id_node1 = node1.getId();
					id_node2 = node2.getId();
	
					assertEquals(node1, model.getANode(id_node1));
					assertEquals(node2, model.getANode(id_node2));
	
					model.addArc(arc);
					break;
				}
				// Ajout d'un noeud source relié à un noeud déjà existant
				case 1: {
					if (model.getListOfNodeSize() == 0) {
						break;
					}
	
					INode node1 = new Node("Node");
	
					int i = (int) (Math.random() * model.getListOfNodeSize());
	
					arc.setStartingNode(node1);
					arc.setEndingNode(model.getNthNode(i));
	
					model.addNode(node1);
	
					id_node1 = node1.getId();
	
					assertEquals(node1, model.getANode(id_node1));
	
					model.addArc(arc);
					break;
				}
	
				// Ajout d'un noeud cible relie a un noeud deja present dans le modele
				case 2: {
	
					if (model.getListOfNodeSize() == 0) {
						break;
					}
	
					INode node2 = new Node("Node");
	
					int i = (int) (Math.random() * model.getListOfNodeSize());
	
					arc.setStartingNode(model.getNthNode(i));
					arc.setEndingNode(node2);
	
					model.addNode(node2);
	
					id_node2 = node2.getId();
					assertEquals(node2, model.getANode(id_node2));
	
					model.addArc(arc);
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
	
					model.addArc(arc);
					break;
				}
	
				default: {
					break;
				}
			}
		} catch (SyntaxErrorException e) {
			System.out.println(e.toString());
		}

	}

	/* Cas où l'ajout de l'arc est impossible */
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
	
				// Ajout d'un arc dont le noeud source n'est pas présent
				// dans le
				// modèle
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
				// Ajout d'un arc dont le noeud cible n'est pas présent dans
				// le
				// modèle
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
			System.out.println(e.toString());
		}
	}

	public void testScenarioArc() {

		Vector<IArc> listOfArcToRemove;
		listOfArcToRemove = new Vector<IArc>();

		int action_alea;

		while (tour <= max_tour) {
			action_alea = (int) (Math.random() * 2);
			IArc arc = new Arc("Arc");

			if (tour % 8 == 0 && !(model.getListOfArcSize() == 0)) {

				int ind = 0;
				int i = 0;

				while (i < nb_remove) {

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
						 * arc plus présent dans la liste des noeuds de sa cible
						 * et de sa source
						 */
						assertFalse(node1.getListOfOutputArc().contains(arc));
						assertFalse(node2.getListOfInputArc().contains(arc));

						/* arc plus présent dans la liste des identifiants */
						assertFalse(model.getListOfId().contains(
								Integer.valueOf(id_arc)));

						/* Test translate/buildmodel en cas de model differents */

						System.out.println("Retrait d'arcs\narc_id:" + id_arc
								+ "\nid_node1:" + id_node1 + " id_node2:"
								+ id_node2 + "\nMaxId:" + model.getMaxId()
								+ "\n");
						i = i + 1;

					}

				}

				for (int j = 0; j < listOfArcToRemove.size(); j++) {

					arc = listOfArcToRemove.get(j);
					id_arc = arc.getId();

					INode node1 = arc.getStartingNode();
					INode node2 = arc.getEndingNode();
					id_node1 = node1.getId();
					id_node2 = node2.getId();
					model.removeArc(arc);

					/* arc plus présent dans la liste des arcs du modèle */
					assertTrue(model.getAnArc(id_arc) == null);

					/*
					 * arc plus présent dans la liste des noeuds de sa cible et
					 * de sa source
					 */
					assertFalse(node1.getListOfOutputArc().contains(arc));
					assertFalse(node2.getListOfInputArc().contains(arc));
					System.out.println("REMOVE tour:" + tour + "\narc_id:"
							+ id_arc + "\nid_node1:" + id_node1 + " id_node2:"
							+ id_node2 + "\nMaxId:" + model.getMaxId() + "\n");

				}

				listOfArcToRemove.clear();
			} else {
				// Ajout autorisé
				if (action_alea == 0) {

					switchArcOK(arc);
					if (!(model.getListOfNodeSize() == 0)) {

						INode node1 = arc.getStartingNode();
						INode node2 = arc.getEndingNode();
						id_node1 = arc.getStartingNode().getId();
						id_node2 = arc.getEndingNode().getId();
						id_arc = arc.getId();

						assertTrue(model.getAnArc(id_arc) != null);
						assertTrue(node1.getListOfOutputArc().contains(arc));
						assertTrue(node2.getListOfInputArc().contains(arc));
						System.out.println("ADD tour:" + tour + "\narc_id:"
								+ id_arc + "\nid_node1:" + id_node1
								+ " id_node2:" + id_node2 + "\nMaxId:"
								+ model.getMaxId() + "\n");
					} else {
						System.out.println("ListOfNode vide - tour:" + tour
								+ "\n");
					}
				} else {
					// Action non autorisée
					System.out.println("Ajout incorrect - tour :" + tour);
					switchArcNull(arc);
					if (!(model.getListOfNodeSize() == 0)) {
						id_arc = arc.getId();
						assertTrue(model.getAnArc(id_arc) == null);
					} else {
						System.out.println("ListOfNode vide - tour:" + tour
								+ "\n");
					}
				}
			}
			tour++;
		}
	}
}
