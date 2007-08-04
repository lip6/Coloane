package fr.lip6.move.coloane.interfaces.concretemodelTest;

import java.util.Vector;

import junit.framework.TestCase;

import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteArc;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteAttribute;
import fr.lip6.move.coloane.interfaces.concretemodel.ConcreteNode;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.IPosition;
import fr.lip6.move.coloane.interfaces.objects.Position;

public class ConcreteArcTest extends TestCase {

	private ConcreteArc ca;

	private ConcreteArc caId;

	public ConcreteArcTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ca = new ConcreteArc("Arc concret");
		caId = new ConcreteArc("Arc concret", 1);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ca = caId = null;
	}

	public void testTranslate() {
		assertTrue(true == true);
	}

	public void testConcreteArcStringInt() {
		ConcreteArc ca = new ConcreteArc("Arc concret", 5);
		assertTrue(ca.getId() == 5);
		assertTrue(ca.getArcType().equals("Arc concret"));
	}

	public void testConcreteArcString() {
		ConcreteArc ca = new ConcreteArc("Arc concret");
		assertTrue(ca.getArcType().equals("Arc concret"));
		assertTrue(ca.getId() == 0);
	}

	public void testArcStringInt() {
		Arc a = new ConcreteArc("Arc abstrait", 3);
		assertTrue(a.getArcType().equals("Arc abstrait"));
		assertTrue(a.getId() == 3);
	}

	public void testArcString() {
		Arc a = new ConcreteArc("Arc abstrait");
		assertTrue(a.getArcType().equals("Arc abstrait"));
		assertTrue(a.getId() == 0);
	}

	public void testGetArcType() {
		assertTrue(ca.getArcType().equals("Arc concret"));
		assertTrue(caId.getArcType().equals("Arc concret"));
	}

	public void testGetId() {
		assertTrue(caId.getId() == 1);
		assertTrue(ca.getId() == 0);
	}

	public void testSetId() {
		ca.setId(3);
		caId.setId(5);
		assertTrue(ca.getId() == 3);
		assertTrue(caId.getId() == 5);
	}

	public void testSetStartingNode() {
		INode node = new ConcreteNode("Debut");
		ca.setStartingNode(node);
		assertTrue(ca.getStartingNode().equals(node));
	}

	public void testSetEndingNode() {
		INode node = new ConcreteNode("Fin");
		ca.setEndingNode(node);
		assertTrue(ca.getEndingNode().equals(node));
	}

	public void testGetStartingNode() {
		assertTrue(ca.getStartingNode() == null);
		INode node = new ConcreteNode("debut");
		ca.setStartingNode(node);
		assertTrue(ca.getStartingNode().equals(node));
	}

	public void testGetEndingNode() {
		assertTrue(ca.getEndingNode() == null);
		INode node = new ConcreteNode("fin");
		ca.setEndingNode(node);
		assertTrue(ca.getEndingNode().equals(node));
	}

	public void testAddAttribute() {
		IAttribute attribute = new ConcreteAttribute("Attribute", "0", 1);
		ca.addAttribute(attribute);
		assertTrue(ca.getListOfAttrSize() == 1);
		assertTrue(ca.getNthAttr(0).equals(attribute));
	}

	public void testRemoveAttributeIAttribute() {
		IAttribute attribute = new ConcreteAttribute("Attribute", "0", 1);
		IAttribute attr = new ConcreteAttribute("Attribute", "0", 2);
		ca.addAttribute(attr);
		ca.addAttribute(attribute);
		assertTrue(ca.getListOfAttrSize() == 2);
		ca.removeAttribute(attribute);
		assertTrue(ca.getListOfAttrSize() == 1);
		ca.removeAttribute(attr);
		assertTrue(ca.getListOfAttrSize() == 0);
	}

	public void testRemoveAttributeInt() {
		IAttribute attribute = new ConcreteAttribute("Attribute", "0", 1);
		IAttribute attr = new ConcreteAttribute("Attribute", "0", 2);
		ca.addAttribute(attr);
		ca.addAttribute(attribute);
		ca.removeAttribute(0);
		assertTrue(ca.getListOfAttrSize() == 1);
	}

	public void testGetListOfAttrSize() {
		IAttribute at1 = new ConcreteAttribute("Attribute", "0", 1);
		IAttribute at2 = new ConcreteAttribute("Attribute", "0", 2);
		IAttribute at3 = new ConcreteAttribute("Attribut", "0", 3);
		ca.addAttribute(at1);
		ca.addAttribute(at2);
		ca.addAttribute(at3);
		assertTrue(ca.getListOfAttrSize() == 3);
	}

	public void testGetListOfAttr() {
		Vector<IAttribute> via;
		IAttribute at1 = new ConcreteAttribute("Attribute", "0", 1);
		IAttribute at2 = new ConcreteAttribute("Attribute", "0", 2);
		IAttribute at3 = new ConcreteAttribute("Attribut", "0", 3);
		ca.addAttribute(at1);
		ca.addAttribute(at2);
		ca.addAttribute(at3);
		via = ca.getListOfAttr();
		assertTrue(via.elementAt(0).equals(at1));
		assertFalse(via.elementAt(0).equals(at2));
		assertTrue(via.elementAt(1).equals(at2));
		assertFalse(via.elementAt(0).equals(at3));
		assertTrue(via.elementAt(2).equals(at3));
	}

	public void testGetNthAttr() {
		IAttribute at1 = new ConcreteAttribute("Attribute", "0", 1);
		IAttribute at2 = new ConcreteAttribute("Attribute", "0", 2);
		IAttribute at3 = new ConcreteAttribute("Attribut", "0", 3);
		ca.addAttribute(at1);
		ca.addAttribute(at2);
		ca.addAttribute(at3);

		assertTrue(ca.getNthAttr(2).equals(at3));
	}

	public void testGetListOfPI() {
		Vector<IPosition> vip;
		IPosition ip0 = new Position(0, 0);
		IPosition ip1 = new Position(1, 1);
		IPosition ip2 = new Position(2, 2);

		try {
			ca.addPI(0, 0);
			ca.addPI(1, 1);
			ca.addPI(2, 2);

			vip = ca.getListOfPI();
			assertTrue(vip.elementAt(0).getXPosition() == ip0.getXPosition());
			assertTrue(vip.elementAt(0).getYPosition() == ip0.getYPosition());

			assertTrue(vip.elementAt(1).getXPosition() == ip1.getXPosition());
			assertTrue(vip.elementAt(1).getYPosition() == ip1.getYPosition());

			assertTrue(vip.elementAt(2).getXPosition() == ip2.getXPosition());
			assertTrue(vip.elementAt(2).getYPosition() == ip2.getYPosition());

		} catch (SyntaxErrorException e) { 
			System.err.println("Erreur");
		}
	}

	public void testAddPIIntInt() {
		try {
			ca.addPI(0, 0);
			ca.addPI(1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
		} catch (SyntaxErrorException e) {
			System.out.println("Erreur dans testAddPIIntInt");
		}

	}

	public void testAddPIIntIntInt() {
		try {
			ca.addPI(0, 0, 0);
			ca.addPI(1, 1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
		} catch (SyntaxErrorException e) {
			System.out.println("Erreur dans testAddPIIntInt");
		}

	}

	public void testRemovePIIntInt() {
		try {
			ca.addPI(0, 0);
			ca.addPI(1, 1);
			assertTrue(ca.getListOfPI().size() == 2);
			ca.removePI(0, 0);
			assertTrue(ca.getListOfPI().size() == 1);
			ca.removePI(1, 1);
			assertTrue(ca.getListOfPI().size() == 0);
		} catch (SyntaxErrorException e) {
			System.out.println("Erreur dans testRemovePIIntInt");
		}
	}

	public void testRemovePIInt() {
		try {
			ca.addPI(0, 1);
			ca.addPI(1, 0);
			assertTrue(ca.getListOfPI().size() == 2);
			ca.removePI(0);
			assertTrue(ca.getListOfPI().size() == 1);
			ca.removePI(0);
			assertTrue(ca.getListOfPI().size() == 0);
		} catch (SyntaxErrorException e) {
			System.out.println("Erreur dans testRemovePIIntInt");
		}

	}

	public void testModifyPI() {
		try {
			IPosition pi = new Position(0, 0);
			ca.addPI(0, 0, 0);
			ca.addPI(1, 1, 1);
			ca.addPI(3, 5, 2);
			assertTrue(ca.getNthPI(0).getXPosition() == pi.getXPosition());
			assertTrue(ca.getNthPI(0).getYPosition() == pi.getYPosition());
			ca.modifyPI(0, 2, 2);
			assertTrue(ca.getNthPI(0).getXPosition() != pi.getXPosition());
			assertTrue(ca.getNthPI(0).getYPosition() != pi.getYPosition());
		} catch (SyntaxErrorException e) {
			System.out.println("Erreur dans testModifyPI");
		}
	}

	public void testGetNthPI() {
		try {
			IPosition pi = new Position(0, 0);
			ca.addPI(0, 0, 0);
			ca.addPI(1, 1, 1);
			ca.addPI(3, 5, 2);
			assertTrue(ca.getNthPI(0).getXPosition() == pi.getXPosition());
			assertTrue(ca.getNthPI(0).getYPosition() == pi.getYPosition());

		} catch (SyntaxErrorException e) { 
			System.err.println("Erreur");
		}
	}
}
