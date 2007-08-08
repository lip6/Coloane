package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import java.util.Vector;

import junit.framework.TestCase;

/**
 * Test des arcs du modele generique
 */

public class ConcreteArcTest extends TestCase {

	private ConcreteArc caShort;
	private ConcreteArc caLong;
	private ConcreteArc ca;

	static final int IDARC = 6;
	static final int IDARC2 = 10;
	static final String TYPEARC = "Arc concret";

	public ConcreteArcTest(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected final void setUp() throws Exception {
		super.setUp();
		caShort = new ConcreteArc(TYPEARC);
		caLong = new ConcreteArc(TYPEARC, IDARC);
		ca = caShort;
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected final void tearDown() throws Exception {
		super.tearDown();
		caShort = null;
		caLong = null;
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#Arc(String, int)}
	 */
	public final void testConcreteArcStringInt() {
		assertTrue(caLong.getId() == IDARC);
		assertTrue(caLong.getArcType().equals(TYPEARC));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#Arc(String)}
	 */
	public final void testConcreteArcString() {
		assertTrue(caShort.getArcType().equals(TYPEARC));
		assertTrue(caShort.getId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getArcType()
	 */
	public final void testGetArcType() {
		assertTrue(caLong.getArcType().equals(TYPEARC));
		assertTrue(caShort.getArcType().equals(TYPEARC));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getId()}
	 */
	public final void testGetId() {
		assertTrue(caLong.getId() == IDARC);
		assertTrue(caShort.getId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setId(int)}
	 */
	public final void testSetId() {
		caShort.setId(IDARC);
		caLong.setId(IDARC2);
		assertTrue(caShort.getId() == IDARC);
		assertTrue(caLong.getId() == IDARC2);
	}


	static final String TYPEBEGIN = "Begin";
	static final String TYPEEND = "End";

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setStartingNode(INode)}
	 */
	public final void testSetStartingNode() {
		INode node = new ConcreteNode(TYPEBEGIN);
		ca.setStartingNode(node);
		assertTrue(ca.getStartingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setEndingNode(INode)}
	 */
	public final void testSetEndingNode() {
		INode node = new ConcreteNode(TYPEEND);
		ca.setEndingNode(node);
		assertTrue(ca.getEndingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getStartingNode()}
	 */
	public final void testGetStartingNode() {
		assertTrue(ca.getStartingNode() == null);
		INode node = new ConcreteNode(TYPEBEGIN);
		ca.setStartingNode(node);
		assertTrue(ca.getStartingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getEndingNode()}
	 */
	public final void testGetEndingNode() {
		assertTrue(ca.getEndingNode() == null);
		INode node = new ConcreteNode(TYPEEND);
		ca.setEndingNode(node);
		assertTrue(ca.getEndingNode().equals(node));
	}

	static final String ATTNAME = "Attribute";
	static final String ATTVALUE = "Hello";
	static final String ATTVALUE2 = "Bye";
	static final String ATTVALUE3 = "See you !";
	static final int ATTREFID = 0;

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#addAttribute(IAttribute)}
	 */
	public final void testAddAttribute() {
		IAttribute attribute = new ConcreteAttribute(ATTNAME, ATTVALUE, ATTREFID);
		ca.addAttribute(attribute);
		assertTrue(ca.getListOfAttrSize() == 1);
		assertTrue(ca.getNthAttr(0).equals(attribute));
		assertTrue(ca.getId() == attribute.getRefId());
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removeAttribute(IAttribute)}
	 */
	public final void testRemoveAttributeIAttribute() {
		IAttribute attribute = new ConcreteAttribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute attribute2 = new ConcreteAttribute(ATTNAME, ATTVALUE2, ATTREFID);
		ca.addAttribute(attribute2);
		ca.addAttribute(attribute);
		assertTrue(ca.getListOfAttrSize() == 2);
		try {
			ca.removeAttribute(attribute);
			assertTrue(ca.getListOfAttrSize() == 1);
			ca.removeAttribute(attribute2);
			assertTrue(ca.getListOfAttrSize() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removeAttribute(int)}
	 */
	public final void testRemoveAttributeInt() {
	IAttribute attribute = new ConcreteAttribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute attribute2 = new ConcreteAttribute(ATTNAME, ATTVALUE2, ATTREFID);
		ca.addAttribute(attribute2);
		ca.addAttribute(attribute);
		try {
			ca.removeAttribute(0);
		} catch (ModelException e) {
			fail(e.toString());
		}

		assertTrue(ca.getListOfAttrSize() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getListOfAttr()}
	 */
	public final void testGetListOfAttr() {
		Vector<IAttribute> via;
		IAttribute at1 = new ConcreteAttribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute at2 = new ConcreteAttribute(ATTNAME, ATTVALUE2, ATTREFID);
		IAttribute at3 = new ConcreteAttribute(ATTNAME, ATTVALUE3, ATTREFID);

		ca.addAttribute(at1);
		ca.addAttribute(at2);
		ca.addAttribute(at3);

		via = ca.getListOfAttr();
		assertTrue(via.elementAt(0).equals(at1));
		assertTrue(via.elementAt(1).equals(at2));
		assertTrue(via.elementAt(2).equals(at3));

		// Pour la recuperation d'un attribut en particulier
		assertTrue(ca.getNthAttr(2).equals(at3));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#addPI(int, int)}
	 */
	public final void testAddPIIntInt() {
		try {
			ca.addPI(0, 0);
			ca.addPI(1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}

		// Ajout d'un PI qui existe deja... Doit lever une exception
		try {
			ca.addPI(1, 1);
			fail("Erreur : Il ne doit pas etre possible d'ajouter un PI deja existant");
		} catch (Exception e) {
			assertTrue(e instanceof ModelException);
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#addPI(int, int, int)}
	 */
	public final void testAddPIIntIntInt() {
		try {
			ca.addPI(0, 0, 0);
			ca.addPI(1, 1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}

		// Ajout d'un PI qui existe deja... Doit lever une exception
		try {
			ca.addPI(1, 1, 0);
			fail("Erreur : Il ne doit pas etre possible d'ajouter un PI deja existant");
		} catch (Exception e) {
			assertTrue(e instanceof ModelException);
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removePI(int, int)}
	 */
	public final void testRemovePIIntInt() {
		try {
			ca.addPI(0, 0);
			ca.addPI(1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
			ca.removePI(0, 0);
			assertTrue(ca.getListOfPI().size() == 1);

			// On essaye d'ajouter un 0,0 ... Ca doti passer
			try {
				ca.addPI(0, 0);
				assertTrue(ca.getListOfPI().size() == 2);
			} catch (ModelException e) {
				fail(e.toString());
			}

			ca.removePI(0, 0);
			ca.removePI(1, 1);
			assertTrue(ca.getListOfPI().size() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removePI(int)}
	 */
	public final void testRemovePIInt() {
		try {
			ca.addPI(0, 1);
			ca.addPI(1, 0);
			assertTrue(ca.getListOfPI().size() == 2);
			ca.removePI(0);
			assertTrue(ca.getListOfPI().size() == 1);

			// On essaye d'ajouter un 0,1 ... Ca doti passer
			try {
				ca.addPI(0, 1);
				assertTrue(ca.getListOfPI().size() == 2);
			} catch (ModelException e) {
				fail(e.toString());
			}

			ca.removePI(0);
			ca.removePI(0);
			assertTrue(ca.getListOfPI().size() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}

	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#modifyPI(int, int, int)}
	 */
	public final void testModifyPI() {
		try {
			ca.addPI(0, 0, 0);
			ca.addPI(1, 1, 1);
			ca.addPI(0, 1, 2);

			ca.modifyPI(0, 2, 2);
			assertTrue(ca.getNthPI(2).getXPosition() == 0);
			assertTrue(ca.getNthPI(0).getYPosition() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}
}
