package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Node;

import java.util.Vector;

import junit.framework.TestCase;

/**
 * Test des arcs du modele generique
 */

public class ArcTest extends TestCase {

	private Arc aShort;
	private Arc aLong;
	private Arc a;

	static final int IDARC = 6;
	static final int IDARC2 = 10;
	static final String TYPEARC = "Arc concret";

	public ArcTest(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected final void setUp() throws Exception {
		super.setUp();
		aShort = new Arc(TYPEARC);
		aLong = new Arc(TYPEARC, IDARC);
		a = aShort;
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected final void tearDown() throws Exception {
		super.tearDown();
		aShort = null;
		aLong = null;
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#Arc(String, int)}
	 */
	public final void testConcreteArcStringInt() {
		assertTrue(aLong.getId() == IDARC);
		assertTrue(aLong.getArcType().equals(TYPEARC));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#Arc(String)}
	 */
	public final void testConcreteArcString() {
		assertTrue(aShort.getArcType().equals(TYPEARC));
		assertTrue(aShort.getId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getArcType()
	 */
	public final void testGetArcType() {
		assertTrue(aLong.getArcType().equals(TYPEARC));
		assertTrue(aShort.getArcType().equals(TYPEARC));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getId()}
	 */
	public final void testGetId() {
		assertTrue(aLong.getId() == IDARC);
		assertTrue(aShort.getId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setId(int)}
	 */
	public final void testSetId() {
		aShort.setId(IDARC);
		aLong.setId(IDARC2);
		assertTrue(aShort.getId() == IDARC);
		assertTrue(aLong.getId() == IDARC2);
	}


	static final String TYPEBEGIN = "Begin";
	static final String TYPEEND = "End";

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setStartingNode(INode)}
	 */
	public final void testSetStartingNode() {
		INode node = new Node(TYPEBEGIN);
		a.setStartingNode(node);
		assertTrue(a.getStartingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#setEndingNode(INode)}
	 */
	public final void testSetEndingNode() {
		INode node = new Node(TYPEEND);
		a.setEndingNode(node);
		assertTrue(a.getEndingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getStartingNode()}
	 */
	public final void testGetStartingNode() {
		assertTrue(a.getStartingNode() == null);
		INode node = new Node(TYPEBEGIN);
		a.setStartingNode(node);
		assertTrue(a.getStartingNode().equals(node));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getEndingNode()}
	 */
	public final void testGetEndingNode() {
		assertTrue(a.getEndingNode() == null);
		INode node = new Node(TYPEEND);
		a.setEndingNode(node);
		assertTrue(a.getEndingNode().equals(node));
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
		IAttribute attribute = new Attribute(ATTNAME, ATTVALUE, ATTREFID);
		a.addAttribute(attribute);
		assertTrue(a.getListOfAttrSize() == 1);
		assertTrue(a.getNthAttr(0).equals(attribute));
		assertTrue(a.getId() == attribute.getRefId());
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removeAttribute(IAttribute)}
	 */
	public final void testRemoveAttributeIAttribute() {
		IAttribute attribute = new Attribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute attribute2 = new Attribute(ATTNAME, ATTVALUE2, ATTREFID);
		a.addAttribute(attribute2);
		a.addAttribute(attribute);
		assertTrue(a.getListOfAttrSize() == 2);
		try {
			a.removeAttribute(attribute);
			assertTrue(a.getListOfAttrSize() == 1);
			a.removeAttribute(attribute2);
			assertTrue(a.getListOfAttrSize() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removeAttribute(int)}
	 */
	public final void testRemoveAttributeInt() {
	IAttribute attribute = new Attribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute attribute2 = new Attribute(ATTNAME, ATTVALUE2, ATTREFID);
		a.addAttribute(attribute2);
		a.addAttribute(attribute);
		try {
			a.removeAttribute(0);
		} catch (ModelException e) {
			fail(e.toString());
		}

		assertTrue(a.getListOfAttrSize() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#getListOfAttr()}
	 */
	public final void testGetListOfAttr() {
		Vector<IAttribute> via;
		IAttribute at1 = new Attribute(ATTNAME, ATTVALUE, ATTREFID);
		IAttribute at2 = new Attribute(ATTNAME, ATTVALUE2, ATTREFID);
		IAttribute at3 = new Attribute(ATTNAME, ATTVALUE3, ATTREFID);

		a.addAttribute(at1);
		a.addAttribute(at2);
		a.addAttribute(at3);

		via = a.getListOfAttr();
		assertTrue(via.elementAt(0).equals(at1));
		assertTrue(via.elementAt(1).equals(at2));
		assertTrue(via.elementAt(2).equals(at3));

		// Pour la recuperation d'un attribut en particulier
		assertTrue(a.getNthAttr(2).equals(at3));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#addPI(int, int)}
	 */
	public final void testAddPIIntInt() {
		try {
			a.addPI(0, 0);
			a.addPI(1, 1);
			assertTrue(a.getListOfPI().size() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}

		// Ajout d'un PI qui existe deja... Doit lever une exception
		try {
			a.addPI(1, 1);
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
			a.addPI(0, 0, 0);
			a.addPI(1, 1, 1);
			assertTrue(a.getListOfPI().size() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}

		// Ajout d'un PI qui existe deja... Doit lever une exception
		try {
			a.addPI(1, 1, 0);
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
			a.addPI(0, 0);
			a.addPI(1, 1);
			assertTrue(a.getListOfPI().size() == 2);
			a.removePI(0, 0);
			assertTrue(a.getListOfPI().size() == 1);

			// On essaye d'ajouter un 0,0 ... Ca doti passer
			try {
				a.addPI(0, 0);
				assertTrue(a.getListOfPI().size() == 2);
			} catch (ModelException e) {
				fail(e.toString());
			}

			a.removePI(0, 0);
			a.removePI(1, 1);
			assertTrue(a.getListOfPI().size() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#removePI(int)}
	 */
	public final void testRemovePIInt() {
		try {
			a.addPI(0, 1);
			a.addPI(1, 0);
			assertTrue(a.getListOfPI().size() == 2);
			a.removePI(0);
			assertTrue(a.getListOfPI().size() == 1);

			// On essaye d'ajouter un 0,1 ... Ca doti passer
			try {
				a.addPI(0, 1);
				assertTrue(a.getListOfPI().size() == 2);
			} catch (ModelException e) {
				fail(e.toString());
			}

			a.removePI(0);
			a.removePI(0);
			assertTrue(a.getListOfPI().size() == 0);
		} catch (ModelException e) {
			fail(e.toString());
		}

	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Arc#modifyPI(int, int, int)}
	 */
	public final void testModifyPI() {
		try {
			a.addPI(0, 0, 0);
			a.addPI(1, 1, 1);
			a.addPI(0, 1, 2);

			a.modifyPI(0, 2, 2);
			assertTrue(a.getNthPI(2).getXPosition() == 0);
			assertTrue(a.getNthPI(0).getYPosition() == 2);
		} catch (ModelException e) {
			fail(e.toString());
		}
	}
}
