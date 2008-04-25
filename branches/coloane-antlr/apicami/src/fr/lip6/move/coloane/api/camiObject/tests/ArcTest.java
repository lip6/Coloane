package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;

import junit.framework.TestCase;



import fr.lip6.move.coloane.api.camiObject.Arc;
import fr.lip6.move.coloane.api.camiObject.Attribute;
import fr.lip6.move.coloane.api.interfaces.IAttribute;

public class ArcTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testArc(){
		 String arcType= "arc";

	     ArrayList<IAttribute> attribute = new ArrayList<IAttribute>();
	     attribute.add((IAttribute)(new Attribute()));
	     int endingNode = 2;
	     int startingNode = 8;
	     int idArc = 13;
      Arc arc = new Arc (arcType,idArc,endingNode,startingNode,attribute);
}

}