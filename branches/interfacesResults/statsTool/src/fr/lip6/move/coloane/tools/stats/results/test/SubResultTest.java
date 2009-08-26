package fr.lip6.move.coloane.tools.stats.results.test;

import fr.lip6.move.coloane.tools.stats.results.SubResult;
import junit.framework.TestCase;

public class SubResultTest extends TestCase {
	private SubResult subres1, subres2, subres3, subres4;
	
	
	public void testSubResultString() {
		assertTrue(subres1.getSubResultName().equals("Subresult 1"));
		assertTrue(subres1.getInformation().equals(""));
		assertTrue(subres1.getSubResults() != null);
		assertTrue(subres1.getObjectsDesignation() != null);
		assertTrue(subres1.getObjectsOutline() != null);
		assertTrue(subres1.getAttributesOutline() != null);
		assertTrue(subres1.getTextualResults() != null);

	}

	public void testSubResultStringString() {
		assertTrue(subres2.getSubResultName().equals("Subresult 2"));
		assertTrue(subres2.getInformation().equals("Information"));
		assertTrue(subres2.getSubResults() != null);
		assertTrue(subres2.getObjectsDesignation() != null);
		assertTrue(subres2.getObjectsOutline() != null);
		assertTrue(subres2.getAttributesOutline() != null);
		assertTrue(subres2.getTextualResults() != null);
	}

	public void testAddTextualResult() {
		subres1.addTextualResult("","","","");
		System.out.println(subres1.getTextualResults().get(0).get(0));
		assertTrue(subres1.getTextualResults().get(0).get(0).equals("No result"));
		
		subres2.addTextualResult("");
		assertTrue(subres2.getTextualResults().get(0).get(0).equals("No result"));
		
		subres3.addTextualResult("","Text","");
		assertTrue(subres3.getTextualResults().get(0).get(0).equals(""));
		assertTrue(subres3.getTextualResults().get(0).get(1).equals("Text"));
		assertTrue(subres3.getTextualResults().get(0).get(2).equals(""));
		
		subres4.addTextualResult("Text 1","Text 2");
		subres4.addTextualResult("","");
		subres4.addTextualResult("","Text 2");	
		assertTrue(subres4.getTextualResults().get(0).get(0).equals("Text 1"));
		assertTrue(subres4.getTextualResults().get(0).get(1).equals("Text 2"));
		assertTrue(subres4.getTextualResults().get(1).get(0).equals("No result"));
		assertTrue(subres4.getTextualResults().get(2).get(0).equals(""));
		assertTrue(subres4.getTextualResults().get(2).get(1).equals("Text 2"));
	}

	public void testAddAttributeOutlineIntegerString() {
		String att1 = "Attribute 1-1";
		String att2 = "Attribute 1-2";
		String att3 = "Attribute 1-3";
		String att4 = "Attribute 42-1";
		String att5 = "Attribute 33-1";
		
		subres1.addAttributeOutline(1, att1);
		subres1.addAttributeOutline(1, att2);
		subres1.addAttributeOutline(1, att3);
		subres1.addAttributeOutline(42, att4);
		subres1.addAttributeOutline(33, att5);
		
		assertTrue(subres1.getAttributesOutline().get(1).get(0).equals(att1));
		assertTrue(subres1.getAttributesOutline().get(1).get(1).equals(att2));
		assertTrue(subres1.getAttributesOutline().get(1).get(2).equals(att3));
		assertTrue(subres1.getAttributesOutline().get(42).get(0).equals(att4));
		assertTrue(subres1.getAttributesOutline().get(33).get(0).equals(att5));
		
	}

	public void testAddObjectDesignationInteger() {
		subres1.addObjectDesignation(1);
		subres1.addObjectDesignation(33);
		subres1.addObjectDesignation(42);
		
		assertTrue(subres1.getObjectsDesignation().get(0).equals(1));
		assertTrue(subres1.getObjectsDesignation().get(1).equals(33));
		assertTrue(subres1.getObjectsDesignation().get(2).equals(42));
	}

	public void testAddObjectOutlineInteger() {
		subres1.addObjectOutline(1);
		subres1.addObjectOutline(33);
		subres1.addObjectOutline(42);
		
		assertTrue(subres1.getObjectsOutline().get(0).equals(1));
		assertTrue(subres1.getObjectsOutline().get(1).equals(33));
		assertTrue(subres1.getObjectsOutline().get(2).equals(42));
	}

	public void testAddSubResult() {
		subres1.addSubResult(subres2);
		subres1.addSubResult(subres3);
		
		assertTrue(subres1.getSubResults().get(0).equals(subres2));
		assertTrue(subres1.getSubResults().get(1).equals(subres3));
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		subres1 = new SubResult("Subresult 1");
		subres2 = new SubResult("Subresult 2","Information");
		subres3 = new SubResult("Subresult 3");
		subres4 = new SubResult("Subresult 4");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		subres1 = null;
		subres2 = null;
		subres3 = null;
		subres4 = null;
	}
}
