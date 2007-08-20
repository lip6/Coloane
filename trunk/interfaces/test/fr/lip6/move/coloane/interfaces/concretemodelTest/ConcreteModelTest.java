package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteModel;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import junit.framework.TestCase;

/**
 * Test des methodes du modele generique
 */
public class ConcreteModelTest extends TestCase {

	private ConcreteModel cm;

	/**
	 * Constructeur de la classe de test
	 * @param name
	 */
	public ConcreteModelTest(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected final void setUp() throws Exception {
		super.setUp();
		this.cm = new ConcreteModel();
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected final void tearDown() throws Exception {
		super.tearDown();
		this.cm = null;
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteModel#ConcreteModel()}.
	 */
	public final void testConcreteModel() {
		assertTrue(cm.getMaxId() == 1);
	}

	static final String TYPEARC = "arc";
	static final String TYPENODE = "node";

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getANode(int)}.
	 */
	public final void testGetANode() {
		INode node = new ConcreteNode(TYPENODE);
		try {
			cm.addNode(node);
			assertTrue(cm.getANode(node.getId()).equals(node));
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getAnArc(int)}.
	 */

	static final String TYPEBEGIN = "begin";
	static final String TYPEEND = "end";

	public final void testGetAnArc() {
		IArc arc = new ConcreteArc(TYPEARC);
		INode begin = new ConcreteNode(TYPEBEGIN);
		INode end = new ConcreteNode(TYPEEND);

		try {
			cm.addNode(begin);
			assertTrue(cm.getListOfNodeSize() == 1);
			cm.addNode(end);
			assertTrue(cm.getListOfNodeSize() == 2);
			arc.setStartingNode(begin);
			arc.setEndingNode(end);
			cm.addArc(arc);
			assertTrue(cm.getListOfArcSize() == 1);
			assertTrue(cm.getAnArc(arc.getId()).equals(arc));
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)}.
	 */
	public final void testAddAttribute() {
		IAttribute at0 = new ConcreteAttribute("Attribut", "concret", 0);
		IAttribute at1 = new ConcreteAttribute("Attribut", "concret", 0);
		cm.addAttribute(at1);
		assertTrue(cm.getListOfAttrSize() == 1);
		cm.addAttribute(at0);
		assertTrue(cm.getListOfAttrSize() == 2);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeNode(fr.lip6.move.coloane.interfaces.model.INode)}.
	 */
	public final void testRemoveNode() {
		INode node = new ConcreteNode(TYPENODE);
		try {
			cm.addNode(node);
			assertTrue(cm.getListOfNodeSize() == 1);
			cm.removeNode(node);
			assertTrue(cm.getListOfNodeSize() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeArc(fr.lip6.move.coloane.interfaces.model.IArc)}.
	 */
	public final void testRemoveArc() {
		IArc arc = new ConcreteArc(TYPEARC);
		INode begin = new ConcreteNode(TYPEBEGIN);
		INode end = new ConcreteNode(TYPEEND);
		try {
			cm.addNode(begin);
			cm.addNode(end);
			arc.setStartingNode(begin);
			arc.setEndingNode(end);
			cm.addArc(arc);
			assertTrue(cm.getListOfArcSize() == 1);
			cm.removeArc(arc);
			assertTrue(cm.getListOfArcSize() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getNthAttr(int)}.
	 */
	public final void testGetNthAttr() {
		IAttribute at0 = new ConcreteAttribute("Attribut", "concret", 0);
		IAttribute at1 = new ConcreteAttribute("Attribut", "concret", 1);
		IAttribute at2 = new ConcreteAttribute("Attribut", "concret", 2);

		cm.addAttribute(at0);
		cm.addAttribute(at1);
		cm.addAttribute(at2);

		assertTrue(cm.getNthAttr(2).equals(at2));
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#getNthArc(int)}.
	 */
	public final void testGetNthArc() {
		IArc arc1 = new ConcreteArc(TYPEARC);
		IArc arc2 = new ConcreteArc(TYPEARC);
		INode begin = new ConcreteNode(TYPEBEGIN);
		INode end = new ConcreteNode(TYPEEND);
		try {
			arc1.setStartingNode(begin);
			arc1.setEndingNode(end);
			arc2.setStartingNode(end);
			arc2.setEndingNode(begin);
			cm.addNode(begin);
			cm.addNode(end);
			cm.addArc(arc1);
			cm.addArc(arc2);
			assertTrue(cm.getNthArc(0).getArcType().equals(TYPEARC));
			assertTrue(cm.getNthArc(1).equals(arc2));
			assertTrue(cm.getNthNode(1).equals(begin));
		} catch (ModelException e) {
			fail(e.toString());
		}
	}
}
