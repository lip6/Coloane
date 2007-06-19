package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

public class TestModel_Simple extends TestCase {

	/*Ajout d'un noeud dans le model*/
	public void testAddNode() {
		int id_node;
		Model model = new Model();
		INode node= new Node("Test_addNode");

		
		model.addNode(node);
		id_node = node.getId();
		assertEquals(node, model.getANode(id_node));
		
	}
	/*Ajout d'un arc dans le model
	 * cas 1 - l'arc n'a ni cible ni 
	 * cas 2 - l'arc a une source mais pas de cible
	 * cas 3 - l'arc a une cible et une source*/
	public void testAddArc() {
		int id_arc;
		Model model = new Model();
		IArc arc= new Arc("Test_addArc");
		INode nodeOut= new Node("Test_addArcOut");
		INode nodeIn= new Node("Test_addArcIn");
		
		
		model.addArc(arc);
		id_arc = arc.getId();
		assertTrue(model.getAnArc(id_arc)==null);
		
		
		arc.setStartingNode(nodeOut);
		model.addNode(nodeOut);
		model.addArc(arc);
		id_arc = arc.getId();
		assertEquals(arc.getStartingNode(),nodeOut);
		assertTrue(model.getAnArc(id_arc)==null);
	

		arc.setEndingNode(nodeIn);
		model.addNode(nodeIn);
		model.addArc(arc);
		id_arc = arc.getId();
		assertEquals(arc.getEndingNode(),nodeIn);
		assertFalse(model.getAnArc(id_arc)==null);
	}
	
	/*Test si un arc relié à 2 noeuds non présent dans le modèle peut être ajoutté*/
	public void testAddArc1() {
		int id_arc;
		Model model = new Model();
		Arc arc= new Arc("Test_addArc");
		Node nodeOut= new Node("Test_addArcOut");
		Node nodeIn= new Node("Test_addArcIn");
		
		arc.setStartingNode(nodeOut);
		arc.setEndingNode(nodeIn);
		model.addArc(arc);
		id_arc = arc.getId();
		assertTrue(model.getAnArc(id_arc)==null);
		
		
	}

	
	public void testRemoveNode() {
		int id_arc,id_node;
		Vector<IArc> listOfInArc, listOfOutArc;
		Model model = new Model();
		INode node= new Node("Test_removeNode");
		INode nodeTmp = new Node("Noeud Temporaire");
		IArc arcCourant= new Arc("Arc Courant");
		
		id_node=node.getId();
		
		listOfInArc=node.getListOfInputArc();
		listOfOutArc=node.getListOfInputArc();
		
		model.removeNode(node);
		
		/*noeud plus présent dans la liste des noeuds du modele*/
		assertTrue(model.getANode(id_node)==null);
		
		for(int i=0;i<listOfInArc.size();i++){
			
			arcCourant=listOfInArc.get(i);
			id_arc = arcCourant.getId();
			nodeTmp = arcCourant.getEndingNode();
			
			/*arc entrant plus présent dans la liste des noeuds du modele
			 * et de sa cible*/
			assertTrue(model.getAnArc(id_arc)==null);
			assertFalse(nodeTmp.getListOfInputArc().contains(arcCourant));
		}
		
		for(int i=0;i<listOfOutArc.size();i++){
			
			arcCourant = listOfOutArc.get(i);
			id_arc = arcCourant.getId();
			nodeTmp = arcCourant.getStartingNode();
			/*arc sortant plus present dans la liste des noeuds du modele
			 * et de sa source*/
			assertTrue(model.getAnArc(id_arc)==null);
			assertFalse(nodeTmp.getListOfOutputArc().contains(arcCourant));
		}
		
	}

	public void testRemoveArc() {
		int id_arc;
		Model model = new Model();
		Arc arc= new Arc("Test_addArc");
		INode nodeOut= new Node("Test_addArcOut");
		INode nodeIn= new Node("Test_addArcIn");
		
		id_arc=arc.getId();
		model.removeArc(arc);
		
		/*arc plus present dans la liste des noeuds du modele*/
		assertTrue(model.getAnArc(id_arc)==null);
		
		/*arc plus present dans la liste des noeuds de sa cible et de sa source*/
		assertFalse(nodeOut.getListOfOutputArc().contains(arc));		
		assertFalse(nodeIn.getListOfInputArc().contains(arc));	
		
	}

}
