package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.Arc;

import junit.framework.TestCase;

public class ArcTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testArc(){
		 String arcType= "arc";
	     ArrayList<String> attribute  = attribute;
	     int endingNode = 2;
	     int startingNode = 8;
	     int idArc = 13;
      Arc arc = new Arc (arcType,idArc,endingNode,startingNode,attribute);
}

}