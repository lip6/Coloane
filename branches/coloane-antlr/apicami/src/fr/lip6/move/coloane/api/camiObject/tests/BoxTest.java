package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.Attribute;
import fr.lip6.move.coloane.api.camiObject.Box;
import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.IBox;
import junit.framework.TestCase;

public class BoxTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

public void testBox(){
	ArrayList<IAttribute> attribute = new ArrayList<IAttribute>();
	attribute.add((IAttribute)new Attribute());
	int boxID = 4;
	int boxPage = 5;
	String boxType = "evaluation";

	IBox box = new Box(attribute,boxID,boxPage,boxType);
}
}
