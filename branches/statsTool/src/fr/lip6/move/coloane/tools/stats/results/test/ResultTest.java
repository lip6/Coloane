package fr.lip6.move.coloane.tools.stats.results.test;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.core.results.SubResult;
import fr.lip6.move.coloane.interfaces.model.command.CreateArcCommand;
import junit.framework.TestCase;

public class ResultTest extends TestCase {
	private Result res1, res2, res3, res4;
	private SubResult subres1, subres2, subres3;
	
	public void testResult() {
		assertTrue(res1.getResultName().equals("Result Test 1"));
		assertTrue(res1.getModificationsOnCurrentGraph() != null);
		assertTrue(res1.getSubResults() != null);
		assertTrue(res1.getTextualResults() != null);
		assertTrue(res1.getTips() != null);
	}

	public void testAddSubResult() {
		res1.addSubResult(subres1);
		res1.addSubResult(subres2);
		res1.addSubResult(subres3);
		
		assertTrue(subres1.equals(res1.getSubResults().get(0)));
		assertTrue(subres2.equals(res1.getSubResults().get(1)));
		assertTrue(subres3.equals(res1.getSubResults().get(2)));
	}

	public void testAddCommand() {
		CreateArcCommand command = new CreateArcCommand(0, "Plop", 0, 0);
		res1.addCommand(command);
		res1.getModificationsOnCurrentGraph().get(0).equals(command);
	}

	public void testAddTextualResult() {
		res1.addTextualResult("","","","");
		System.out.println(res1.getTextualResults().get(0).get(0));
		assertTrue(res1.getTextualResults().get(0).get(0).equals("No result"));
		
		res2.addTextualResult("");
		assertTrue(res2.getTextualResults().get(0).get(0).equals("No result"));
		
		res3.addTextualResult("","Text","");
		assertTrue(res3.getTextualResults().get(0).get(0).equals(""));
		assertTrue(res3.getTextualResults().get(0).get(1).equals("Text"));
		assertTrue(res3.getTextualResults().get(0).get(2).equals(""));
		
		res4.addTextualResult("Text 1","Text 2");
		res4.addTextualResult("","");
		res4.addTextualResult("","Text 2");	
		assertTrue(res4.getTextualResults().get(0).get(0).equals("Text 1"));
		assertTrue(res4.getTextualResults().get(0).get(1).equals("Text 2"));
		assertTrue(res4.getTextualResults().get(1).get(0).equals("No result"));
		assertTrue(res4.getTextualResults().get(2).get(0).equals(""));
		assertTrue(res4.getTextualResults().get(2).get(1).equals("Text 2"));
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		res1 = new Result("Result Test 1", null);
		res2 = new Result("Result Test 2", null);
		res3 = new Result("Result Test 3", null);
		res4 = new Result("Result Test 4", null);
		subres1 = new SubResult("Subres 1");
		subres2 = new SubResult("Subres 2");
		subres3 = new SubResult("Subres 3");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		res1 = null;
		res2 = null;
		subres1 = null;
		subres2 = null;
		subres3 = null;
	}
}
