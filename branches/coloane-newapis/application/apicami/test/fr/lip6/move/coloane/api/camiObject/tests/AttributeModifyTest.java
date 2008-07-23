package fr.lip6.move.coloane.api.camiObject.tests;

import fr.lip6.move.coloane.api.camiObject.AttributeModify;
import fr.lip6.move.coloane.api.interfaces.IAttributeModify;
import junit.framework.TestCase;

public class AttributeModifyTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAttributeModify(){
		String attributeType = "guard";
		String newContent= "true";
		int objectID = 5;
		IAttributeModify af = new AttributeModify(attributeType, newContent,
			objectID);

		this.assertEquals("guard", af.getAttributeType());
		this.assertEquals("true", af.getNewContent());
		this.assertEquals(5, af.getObjectID());
	}
}
