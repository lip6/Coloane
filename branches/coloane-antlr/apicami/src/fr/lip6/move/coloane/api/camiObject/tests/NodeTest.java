package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;



import fr.lip6.move.coloane.api.camiObject.Attribute;
import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.api.camiObject.Node;
import junit.framework.TestCase;

public class NodeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNode(){
		ArrayList<IAttribute> attribute = new ArrayList<IAttribute>();
		attribute.add((IAttribute)new Attribute());
		int nodeID = 4;
		String nodeType = "evaluation";

		Node node = new Node( attribute,nodeID, nodeType);
	}
}
