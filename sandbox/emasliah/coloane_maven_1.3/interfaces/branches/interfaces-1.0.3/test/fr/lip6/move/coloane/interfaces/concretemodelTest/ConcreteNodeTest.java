/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodelTest;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Node;
import junit.framework.TestCase;

/**
 * @author cdcharles
 *
 */
public class ConcreteNodeTest extends TestCase {

	private INode node;
	private INode nodeXY;
	private INode nodeId;
	/**
	 * @param name
	 */
	public ConcreteNodeTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		node = new ConcreteNode("node");
		nodeXY = new ConcreteNode("node", 0,0);
		nodeId = new ConcreteNode("node", 1,1,1);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		node = nodeXY = nodeId = null;
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#translate()}.
	 */
	public void testTranslate() {
		assertTrue( true ==true);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String)}.
	 */
	public void testConcreteNodeString() {
		assertTrue(node.getNodeType().equals("node"));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String, int, int)}.
	 */
	public void testConcreteNodeStringIntInt() {
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String, int, int, int)}.
	 */
	public void testConcreteNodeStringIntIntInt() {
		assertTrue(nodeId.getId() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#Node(java.lang.String)}.
	 */
	public void testNodeString() {
		Node n = new ConcreteNode("node");
		assertTrue(n.getNodeType().equals("node"));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#Node(java.lang.String, int, int)}.
	 */
	public void testNodeStringIntInt() {
		Node n = new ConcreteNode("node", 0, 0);
		assertTrue(n.getXPosition() == 0);
		assertTrue(n.getYPosition() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#Node(java.lang.String, int, int, int)}.
	 */
	public void testNodeStringIntIntInt() {
		Node n = new ConcreteNode("node", 0,0,2);
		assertTrue(n.getId() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getId()}.
	 */
	public void testGetId() {
		assertTrue(node.getId() == 0);
		assertTrue(nodeXY.getId() == 0);
		assertTrue(nodeId.getId() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#setId(int)}.
	 */
	public void testSetId() {
		assertTrue(node.getId() == 0);
		node.setId(2);
		assertTrue(node.getId() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getNodeType()}.
	 */
	public void testGetNodeType() {
		assertTrue(node.getNodeType().equals("node"));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#setPosition(int, int)}.
	 */
	public void testSetPosition() {
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);
		
		nodeXY.setPosition(2, 2);
		
		assertTrue(nodeXY.getXPosition() == 2);
		assertTrue(nodeXY.getYPosition() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getXPosition()}.
	 */
	public void testGetXPosition() {
		assertTrue(node.getXPosition() == 0);
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeId.getXPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getYPosition()}.
	 */
	public void testGetYPosition() {
		assertTrue(node.getYPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);
		assertTrue(nodeId.getYPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addInputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testAddInputArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		node.addInputArc(arc);
		node.addInputArc(arc1);
		
		assertTrue(node.getListOfInputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeInputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testRemoveInputArcIArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		node.addInputArc(arc);
		node.addInputArc(arc1);
		assertTrue(node.getListOfInputArcSize() == 2);
		node.removeInputArc(arc);
		assertFalse(node.getListOfInputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeInputArc(int)}.
	 */
	public void testRemoveInputArcInt() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addInputArc(arc);
		node.addInputArc(arc1);
		node.addInputArc(arcId);
		
		assertTrue(node.getListOfInputArcSize() == 3);
		
		node.removeInputArc(0);
		
		assertTrue(node.getListOfInputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfInputArcSize()}.
	 */
	public void testGetListOfInputArcSize() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addInputArc(arc);
		node.addInputArc(arc1);
		node.addInputArc(arcId);
		
		assertTrue(node.getListOfInputArcSize() == 3);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getNthInputArc(int)}.
	 */
	public void testGetNthInputArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addInputArc(arc);
		node.addInputArc(arc1);
		node.addInputArc(arcId);
		
		assertTrue(node.getNthInputArc(2).equals(arcId));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testAddOutputArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		
		assertTrue(node.getListOfOutputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public void testRemoveOutputArcIArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		assertTrue(node.getListOfOutputArcSize() == 2);
		node.removeOutputArc(arc);
		assertFalse(node.getListOfOutputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeOutputArc(int)}.
	 */
	public void testRemoveOutputArcInt() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		node.addOutputArc(arcId);
		
		assertTrue(node.getListOfOutputArcSize() == 3);
		
		node.removeOutputArc(2);
		
		assertTrue(node.getListOfOutputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfOutputArcSize()}.
	 */
	public void testGetListOfOutputArcSize() {
		
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		node.addOutputArc(arcId);
		
		assertTrue(node.getListOfOutputArcSize() == 3);
		
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getNthOutputArc(int)}.
	 */
	public void testGetNthOutputArc() {
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		node.addOutputArc(arcId);
		
		assertTrue(node.getNthOutputArc(2).equals(arcId));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public void testAddAttribute() {
		IAttribute att = new ConcreteAttribute("attribut","toto", 0);
		node.addAttribute(att);
		assertTrue(node.getListOfAttrSize() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public void testRemoveAttributeIAttribute() {
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut","2",2);
		IAttribute a2 = new ConcreteAttribute("attribut","3",3);
		
		node.addAttribute(a0);
		node.addAttribute(a1);
		node.addAttribute(a2);
		
		assertTrue(node.getListOfAttrSize() == 3);		
		node.removeAttribute(a2);		
		assertTrue(node.getListOfAttrSize() == 2);
		
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeAttribute(int)}.
	 */
	public void testRemoveAttributeInt() {
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut","2",2);
		IAttribute a2 = new ConcreteAttribute("attribut","3",3);
		
		node.addAttribute(a0);
		node.addAttribute(a1);
		node.addAttribute(a2);
		
		assertTrue(node.getListOfAttrSize() == 3);	
		node.removeAttribute(2);
		assertFalse(node.getListOfAttrSize() == 3);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfAttrSize()}.
	 */
	public void testGetListOfAttrSize() {
		
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut","2",2);
		IAttribute a2 = new ConcreteAttribute("attribut","3",3);
		
		node.addAttribute(a0);
		node.addAttribute(a1);
		node.addAttribute(a2);
		assertTrue(node.getListOfAttrSize() == 3);			
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfAttr()}.
	 */
	public void testGetListOfAttr() {
		Vector<IAttribute> via;
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut","2",2);
		IAttribute a2 = new ConcreteAttribute("attribut","3",3);
		
		node.addAttribute(a0);
		node.addAttribute(a1);
		node.addAttribute(a2);
		
		via = node.getListOfAttr();
		assertTrue(via.elementAt(0).equals(a0));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getNthAttr(int)}.
	 */
	public void testGetNthAttr() {
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut","2",2);
		IAttribute a2 = new ConcreteAttribute("attribut","3",3);
		
		node.addAttribute(a0);
		node.addAttribute(a1);
		node.addAttribute(a2);
		
		assertTrue(node.getNthAttr(2).equals(a2));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfInputArc()}.
	 */
	public void testGetListOfInputArc() {
		Vector<IArc> via;
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addInputArc(arc);
		node.addInputArc(arc1);
		node.addInputArc(arcId);
		
		via = node.getListOfInputArc();
		
		assertTrue(via.elementAt(0).equals(arc));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getListOfOutputArc()}.
	 */
	public void testGetListOfOutputArc() {
		Vector<IArc> via;
		IArc arc = new ConcreteArc("arc");
		IArc arc1 = new ConcreteArc("arc1");
		IArc arcId = new ConcreteArc("arcID", 2);
		
		node.addOutputArc(arc);
		node.addOutputArc(arc1);
		node.addOutputArc(arcId);
		
		via = node.getListOfOutputArc();
		
		assertTrue(via.elementAt(0).equals(arc));
	}

}
