/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodelTest;


import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import junit.framework.TestCase;

/**
 * @author cdcharles
 *
 */
public class ConcreteAttributeTest extends TestCase {

	private ConcreteAttribute ca;
	/**
	 * @param name
	 */
	public ConcreteAttributeTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ca = new ConcreteAttribute("Attribut","Concret",0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		ca = null;
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute#translate()}.
	 */
	public void testTranslate() {
		assertTrue( true == true);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute#ConcreteAttribute(java.lang.String, java.lang.String, int)}.
	 */
	public void testConcreteAttribute() {
		assertTrue(ca.getName().equals("Attribut"));
		assertTrue(ca.getValue().equals("Concret"));
		assertTrue(ca.getRefId() == 0);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#Attribute(java.lang.String, java.lang.String, int)}.
	 */
	public void testAttribute() {
		Attribute a = new ConcreteAttribute("Attribut","abstrait",1);
		assertTrue(a.getName().equals("Attribut"));
		assertTrue(a.getValue().equals("abstrait"));
		assertTrue(a.getRefId() == 1);
	}
	

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#getName()}.
	 */
	public void testGetName() {
		ConcreteAttribute ca = new ConcreteAttribute("Attribut","concret",0);
		assertTrue(ca.getName().equals("Attribut"));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setPosition(int, int)}.
	 */
	public void testSetPosition() {
		assertTrue(ca.getXPosition() == 0);
		assertTrue(ca.getYPosition() == 0);
		ca.setPosition(1,1);
		assertTrue(ca.getXPosition() == 1);
		assertTrue(ca.getYPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#getXPosition()}.
	 */
	public void testGetXPosition() {
		assertTrue(ca.getXPosition() == 0);
		ca.setPosition(1,1);
		assertTrue(ca.getXPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#getYPosition()}.
	 */
	public void testGetYPosition() {
		assertTrue(ca.getYPosition() == 0);
		ca.setPosition(1,1);
		assertTrue(ca.getYPosition() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#getRefId()}.
	 */
	public void testGetRefId() {
		assertTrue(ca.getRefId() == 0);
		
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setRefId(int)}.
	 */
	public void testSetRefId() {
		assertTrue(ca.getRefId() == 0);		
		ca.setRefId(1);
		assertTrue(ca.getRefId() == 1);
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#setValue(java.lang.String)}.
	 */
	public void testSetValue() {
		assertTrue(ca.getValue().equals("Concret"));
		ca.setValue("c");
		assertTrue(ca.getValue().equals("c"));
	}

	/**
	 * Test method for {@link fr.lip6.move.coloane.interfaces.model.Attribute#getValue()}.
	 */
	public void testGetValue() {
		assertTrue(ca.getValue().equals("Concret"));
	}

}
