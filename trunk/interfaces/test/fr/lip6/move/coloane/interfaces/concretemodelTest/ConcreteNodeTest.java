package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import junit.framework.TestCase;

/**
 * Test des noeuds du modele generique
 */
public class ConcreteNodeTest extends TestCase {

	private INode node;
	private INode nodeXY;
	private INode nodeId;

	static final String TYPENODE = "node";

	public ConcreteNodeTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected final void setUp() throws Exception {
		super.setUp();
		node = new ConcreteNode(TYPENODE);
		nodeXY = new ConcreteNode(TYPENODE, 0, 0);
		nodeId = new ConcreteNode(TYPENODE, 1, 1, 1);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected final void tearDown() throws Exception {
		super.tearDown();
		node = null;
		nodeId = null;
		nodeXY = null;
	}


	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String)}.
	 */
	public final void testConcreteNodeString() {
		assertTrue(node.getNodeType().equals(TYPENODE));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String, int, int)}.
	 */
	public final void testConcreteNodeStringIntInt() {
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode#ConcreteNode(java.lang.String, int, int, int)}.
	 */
	public final void testConcreteNodeStringIntIntInt() {
		assertTrue(nodeId.getId() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getId()}.
	 */
	public final void testGetId() {
		assertTrue(node.getId() == 0);
		assertTrue(nodeXY.getId() == 0);
		assertTrue(nodeId.getId() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#setId(int)}.
	 */
	public final void testSetId() {
		assertTrue(node.getId() == 0);
		node.setId(2);
		assertTrue(node.getId() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#setPosition(int, int)}.
	 */
	public final void testSetPosition() {
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);

		nodeXY.setPosition(2, 2);

		assertTrue(nodeXY.getXPosition() == 2);
		assertTrue(nodeXY.getYPosition() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getXPosition()}.
	 */
	public final void testGetXPosition() {
		assertTrue(node.getXPosition() == 0);
		assertTrue(nodeXY.getXPosition() == 0);
		assertTrue(nodeId.getXPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#getYPosition()}.
	 */
	public final void testGetYPosition() {
		assertTrue(node.getYPosition() == 0);
		assertTrue(nodeXY.getYPosition() == 0);
		assertTrue(nodeId.getYPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addInputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public final void testAddInputArc() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");
		node.addInputArc(arc1);
		node.addInputArc(arc2);

		assertTrue(node.getListOfInputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeInputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public final void testRemoveInputArcIArc() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");
		node.addInputArc(arc1);
		node.addInputArc(arc2);
		assertTrue(node.getListOfInputArcSize() == 2);

		try {
			node.removeInputArc(arc1);
		} catch (ModelException e) {
			fail(e.toString());
		}

		assertFalse(node.getListOfInputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeInputArc(int)}.
	 */
	public final void testRemoveInputArcInt() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");

		node.addInputArc(arc1);
		node.addInputArc(arc2);

		assertTrue(node.getListOfInputArcSize() == 2);

		try {
			node.removeInputArc(0);
		} catch (ModelException e) {
			fail(e.toString());
		}

		assertTrue(node.getListOfInputArcSize() == 1);

		try {
			node.removeInputArc(2);
			fail();
		} catch (ModelException e) {
			assertTrue(node.getListOfInputArcSize() == 1);
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public final void testAddOutputArc() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");
		node.addOutputArc(arc1);
		node.addOutputArc(arc2);
		assertTrue(node.getListOfOutputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public final void testRemoveOutputArcIArc() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");
		node.addOutputArc(arc1);
		node.addOutputArc(arc2);
		assertTrue(node.getListOfOutputArcSize() == 2);
		try {
			node.removeOutputArc(arc1);
		} catch (ModelException e) {
			fail(e.toString());
		}
		assertFalse(node.getListOfOutputArcSize() == 2);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeOutputArc(int)}.
	 */
	public final void testRemoveOutputArcInt() {
		IArc arc1 = new ConcreteArc("arc");
		IArc arc2 = new ConcreteArc("arc");

		node.addOutputArc(arc1);
		node.addOutputArc(arc2);

		assertTrue(node.getListOfOutputArcSize() == 2);

		try {
			node.removeOutputArc(0);
		} catch (ModelException e) {
			fail(e.toString());
		}

		assertTrue(node.getListOfOutputArcSize() == 1);

		try {
			node.removeOutputArc(2);
			fail();
		} catch (ModelException e) {
			assertTrue(node.getListOfOutputArcSize() == 1);
		}

	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public final void testAddAttribute() {
		IAttribute att = new ConcreteAttribute("attribut", "toto", 0);
		node.addAttribute(att);
		assertTrue(node.getListOfAttrSize() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public final void testRemoveAttributeIAttribute() {
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut", "2", 2);

		node.addAttribute(a0);
		node.addAttribute(a1);

		assertTrue(node.getListOfAttrSize() == 2);
		try {
			node.removeAttribute(a1);
		} catch (ModelException e) {
			fail(e.toString());
		}
		assertTrue(node.getListOfAttrSize() == 1);

	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Node#removeAttribute(int)}.
	 */
	public final void testRemoveAttributeInt() {
		IAttribute a0 = new ConcreteAttribute("attribut", "1", 1);
		IAttribute a1 = new ConcreteAttribute("attribut", "2", 2);

		node.addAttribute(a0);
		node.addAttribute(a1);

		assertTrue(node.getListOfAttrSize() == 2);
		try {
			node.removeAttribute(1);
		} catch (ModelException e) {
			fail(e.toString());
		}

		try {
			node.removeAttribute(2);
			fail("The attribute should not be removed");
		} catch (ModelException e) {
			assertFalse(node.getListOfAttrSize() == 0);
		}
	}
}
