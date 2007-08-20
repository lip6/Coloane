package fr.lip6.move.coloane.interfaces.concretemodelTest;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import junit.framework.TestCase;

/**
 * Test des attributs du modele generique
 */

public class ConcreteAttributeTest extends TestCase {

	private ConcreteAttribute ca;

	static final String ATTNAME = "Attribute";
	static final String ATTVALUE = "hello";
	static final int ATTREFID = 0;

	/**
	 * Constructeur de la classe de test
	 * @param name
	 */
	public ConcreteAttributeTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected final void setUp() throws Exception {
		super.setUp();
		ca = new ConcreteAttribute(ATTNAME, ATTVALUE, ATTREFID);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected final void tearDown() throws Exception {
		super.tearDown();
		ca = null;
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute#translate()}.
	 */
	public void testTranslate() {
		//assertTrue( true == true);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute#ConcreteAttribute(java.lang.String, java.lang.String, int)}.
	 */
	public final void testConcreteAttribute() {
		assertTrue(ca.getName().equals(ATTNAME));
		assertTrue(ca.getValue().equals(ATTVALUE));
		assertTrue(ca.getRefId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setPosition(int, int)}.
	 */
	public final void testSetPosition() {
		assertTrue(ca.getXPosition() == 0);
		assertTrue(ca.getYPosition() == 0);
		ca.setPosition(1, 1);
		assertTrue(ca.getXPosition() == 1);
		assertTrue(ca.getYPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setRefId(int)}.
	 */
	public final void testSetRefId() {
		assertTrue(ca.getRefId() == 0);
		ca.setRefId(1);
		assertTrue(ca.getRefId() == 1);
	}

	static final String ATTVALUE2 = "Bye";
	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setValue(java.lang.String)}.
	 */
	public final void testSetValue() {
		assertTrue(ca.getValue().equals(ATTVALUE));
		ca.setValue(ATTVALUE2);
		assertTrue(ca.getValue().equals(ATTVALUE2));
	}
}
