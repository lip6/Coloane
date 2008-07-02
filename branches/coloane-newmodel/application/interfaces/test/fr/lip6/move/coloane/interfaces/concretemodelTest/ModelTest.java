package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.interfaces.model.interfaces.IArc;
import fr.lip6.move.coloane.interfaces.model.interfaces.IAttribute;
import fr.lip6.move.coloane.interfaces.model.interfaces.INode;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import junit.framework.TestCase;

/**
 * Test des methodes du modele generique
 */
public class ModelTest extends TestCase {

	private Model cm;

	/**
	 * Constructeur de la classe de test
	 * @param name
	 */
	public ModelTest(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected final void setUp() throws Exception {
		super.setUp();
		this.cm = new Model(new CamiTranslator());
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
		ICoreNode node = new Node(TYPENODE);
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
		ICoreArc arc = new Arc(TYPEARC);
		ICoreNode begin = new Node(TYPEBEGIN);
		ICoreNode end = new Node(TYPEEND);

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
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#addAttribute(fr.lip6.move.coloane.interfaces.model.interfaces.ICoreAttribute)}.
	 */
	public final void testAddAttribute() {
		ICoreAttribute at0 = new Attribute("Attribut", "concret", 0);
		ICoreAttribute at1 = new Attribute("Attribut", "concret", 0);
		cm.addAttribute(at1);
		assertTrue(cm.getListOfAttrSize() == 1);
		cm.addAttribute(at0);
		assertTrue(cm.getListOfAttrSize() == 2);
	}

	/**
	 * Test method for
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeNode(fr.lip6.move.coloane.interfaces.model.interfaces.ICoreNode)}.
	 */
	public final void testRemoveNode() {
		ICoreNode node = new Node(TYPENODE);
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
	 * {@link fr.lip6.move.coloane.interfaces.model.Model#removeArc(fr.lip6.move.coloane.interfaces.model.interfaces.ICoreArc)}.
	 */
	public final void testRemoveArc() {
		ICoreArc arc = new Arc(TYPEARC);
		ICoreNode begin = new Node(TYPEBEGIN);
		ICoreNode end = new Node(TYPEEND);
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
		ICoreAttribute at0 = new Attribute("Attribut", "concret", 0);
		ICoreAttribute at1 = new Attribute("Attribut", "concret", 1);
		ICoreAttribute at2 = new Attribute("Attribut", "concret", 2);

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
		ICoreArc arc1 = new Arc(TYPEARC);
		ICoreArc arc2 = new Arc(TYPEARC);
		ICoreNode begin = new Node(TYPEBEGIN);
		ICoreNode end = new Node(TYPEEND);
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
			assertTrue(cm.getNthNode(1).equals(end));
		} catch (ModelException e) {
			fail(e.toString());
		}
	}
}
