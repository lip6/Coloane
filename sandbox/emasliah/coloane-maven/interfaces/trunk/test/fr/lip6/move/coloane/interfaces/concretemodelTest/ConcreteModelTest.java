/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodelTest;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteModel;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import junit.framework.TestCase;

/**
 * @author cdcharles
 * 
 */
public class ConcreteModelTest extends TestCase {

	private ConcreteModel cm;

	/**
	 * @param name
	 */
	public ConcreteModelTest(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		cm = new ConcreteModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		cm = null;
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteModel#translate()}.
	 */
	/*
	 * public void testTranslate() { assertTrue(true == true); }
	 */

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteModel#ConcreteModel()}.
	 */
	public void testConcreteModel() {
		cm = new ConcreteModel();
		assertTrue(cm.getMaxId() == 1);
		assertTrue(cm.getXPosition() == 20);
		assertTrue(cm.getYPosition() == 20);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#Model()}.
	 */
	public void testModel() {
		Model m = new ConcreteModel();
		assertTrue(m.getMaxId() == 1);
		assertTrue(m.getXPosition() == 20);
		assertTrue(m.getYPosition() == 20);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getANode(int)}.
	 */
	public void testGetANode() {
		INode node = new ConcreteNode("node");
		try {
			cm.addNode(node);
			assertTrue(cm.getANode(node.getId()).equals(node));
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getAnArc(int)}.
	 */
	public void testGetAnArc() {
		IArc arc = new ConcreteArc("arc", 4);
		INode nod = new ConcreteNode("deb");
		INode node = new ConcreteNode("fin");

		try {
			cm.addNode(node);
			cm.addNode(nod);
			arc.setStartingNode(nod);
			arc.setEndingNode(node);
			cm.addArc(arc);
			assertTrue(cm.getAnArc(4).equals(arc));
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#addNode(fr.lip6.move.coloane.interfaces.model.INode)}.
	 */
	public void testAddNode() {
		INode node = new ConcreteNode("node");
		INode node1 = new ConcreteNode("node1");
		try {
			cm.addNode(node);
			assertTrue(cm.getListOfNodeSize() == 1);
			cm.addNode(node1);
			assertTrue(cm.getListOfNodeSize() == 2);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#addArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testAddArc() {
		IArc arc = new ConcreteArc("arc");
		Node deb = new ConcreteNode("deb");
		Node fin = new ConcreteNode("fin");
		try {
			cm.addNode(deb);
			cm.addNode(fin);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		arc.setStartingNode(deb);
		arc.setEndingNode(fin);
		try {
			cm.addArc(arc);
			assertTrue(cm.getListOfArcSize() == 1);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public void testAddAttribute() {
		IAttribute at0 = new ConcreteAttribute("Attribut", " concret", 0);
		IAttribute at1 = new ConcreteAttribute("Attribut", " concret", 0);
		cm.addAttribute(at1);
		assertTrue(cm.getListOfAttrSize() == 1);
		cm.addAttribute(at0);
		assertTrue(cm.getListOfAttrSize() == 2);

	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeNode(fr.lip6.move.coloane.interfaces.model.INode)}.
	 */
	public void testRemoveNode() {
		INode node = new ConcreteNode("node");
		try {
			cm.addNode(node);
			assertTrue(cm.getListOfNodeSize() == 1);
			cm.removeNode(node);
			assertTrue(cm.getListOfNodeSize() == 0);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testRemoveArc() {
		IArc arc = new ConcreteArc("arc");
		Node deb = new ConcreteNode("deb");
		Node fin = new ConcreteNode("fin");
		try {
			cm.addNode(deb);
			cm.addNode(fin);
			arc.setStartingNode(deb);
			arc.setEndingNode(fin);
			cm.addArc(arc);
			assertTrue(cm.getListOfArcSize() == 1);
			cm.removeArc(arc);
			assertTrue(cm.getListOfArcSize() == 0);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getListOfAttrSize()}.
	 */
	public void testGetListOfAttrSize() {
		IAttribute at0 = new ConcreteAttribute("Attribut", " concret", 0);
		IAttribute at1 = new ConcreteAttribute("Attribut", " concret", 0);
		cm.addAttribute(at0);
		assertTrue(cm.getListOfAttrSize() == 1);
		cm.addAttribute(at1);
		assertTrue(cm.getListOfAttrSize() == 2);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getListOfArcSize()}.
	 */
	public void testGetListOfArcSize() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		Node deb = new ConcreteNode("debut");
		Node fin = new ConcreteNode("fin");
		try {
			arc.setStartingNode(deb);
			arc.setEndingNode(fin);
			arc1.setStartingNode(fin);
			arc1.setEndingNode(deb);
			cm.addNode(deb);
			cm.addNode(fin);
			cm.addArc(arc);
			cm.addArc(arc1);
			assertTrue(cm.getListOfArcSize() == 2);
			cm.removeArc(arc);
			assertTrue(cm.getListOfArcSize() == 1);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getListOfNodeSize()}.
	 */
	public void testGetListOfNodeSize() {
		INode node = new ConcreteNode("node");
		INode node1 = new ConcreteNode("node1");
		try {
			cm.addNode(node);
			assertTrue(cm.getListOfNodeSize() == 1);
			cm.addNode(node1);
			assertTrue(cm.getListOfNodeSize() == 2);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getNthAttr(int)}.
	 */
	public void testGetNthAttr() {
		IAttribute at0 = new ConcreteAttribute("Attribut", " concret", 0);
		IAttribute at1 = new ConcreteAttribute("Attribut", " concret", 1);
		IAttribute at2 = new ConcreteAttribute("Attribut", " concret", 2);

		cm.addAttribute(at0);
		cm.addAttribute(at1);
		cm.addAttribute(at2);

		assertTrue(cm.getNthAttr(2).equals(at2));
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getNthArc(int)}.
	 */
	public void testGetNthArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		Node deb = new ConcreteNode("debut");
		Node fin = new ConcreteNode("fin");
		try {
			arc.setStartingNode(deb);
			arc.setEndingNode(fin);
			arc1.setStartingNode(fin);
			arc1.setEndingNode(deb);
			cm.addNode(deb);
			cm.addNode(fin);
			cm.addArc(arc);
			cm.addArc(arc1);
			assertTrue(cm.getNthArc(0).getArcType().equals("arc"));
			assertTrue(cm.getNthArc(1).equals(arc1));
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getNthNode(int)}.
	 */
	public void testGetNthNode() {
		INode node = new ConcreteNode("node");
		INode node1 = new ConcreteNode("node1");
		try {
			cm.addNode(node);
			cm.addNode(node1);
			assertTrue(cm.getNthNode(1).equals(node1));
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getXPosition()}.
	 */
	public void testGetXPosition() {
		assertTrue(cm.getXPosition() == 20);

	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getYPosition()}.
	 */
	public void testGetYPosition() {
		assertTrue(cm.getYPosition() == 20);

	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#setPosition(int, int)}.
	 */
	public void testSetPosition() {
		cm.setPosition(50, 50);
		assertTrue(cm.getXPosition() == 50);
		assertTrue(cm.getYPosition() == 50);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#setFormalism(java.lang.String)}.
	 */
	public void testSetFormalism() {
		assertTrue(cm.getFormalism().equals(""));
		cm.setFormalism("toto");
		assertTrue(cm.getFormalism().equals("toto"));
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getFormalism()}.
	 */
	public void testGetFormalism() {
		assertTrue(cm.getFormalism().equals(""));
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getMaxId()}.
	 */
	public void testGetMaxId() {
		assertTrue(cm.getMaxId() == 1);
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		Node deb = new ConcreteNode("debut");
		Node fin = new ConcreteNode("fin");
		try {
			arc.setStartingNode(deb);
			arc.setEndingNode(fin);
			arc1.setStartingNode(fin);
			arc1.setEndingNode(deb);
			cm.addNode(deb);
			cm.addNode(fin);
			cm.addArc(arc);
			cm.addArc(arc1);
			int max = cm.setMaxId(10);
			assertFalse(cm.getMaxId() == max);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#setMaxId(int)}.
	 */
	public void testSetMaxId() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		Node deb = new ConcreteNode("debut");
		Node fin = new ConcreteNode("fin");
		try {
			arc.setStartingNode(deb);
			arc.setEndingNode(fin);
			arc1.setStartingNode(fin);
			arc1.setEndingNode(deb);
			cm.addNode(deb);
			cm.addNode(fin);
			cm.addArc(arc);
			cm.addArc(arc1);
			assertTrue((cm.getListOfArcSize() + cm.getListOfNodeSize()) + 1 == cm.getMaxId());
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getListOfId()}.
	 */
	public void testGetListOfId() {
		Vector<Integer> vi = new Vector<Integer>();
		vi = cm.getListOfId();
		assertTrue(!vi.isEmpty());
	}

}
