package fr.lip6.move.coloane.api.camiObject.tests;

import fr.lip6.move.coloane.api.camiObject.UpdateItem;
import junit.framework.TestCase;

public class UpdateItemTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testUpdateItem(){
		  String rootName = "AMI-NET";
		  String serviceName="Modelling facilities";
          boolean state =true;
          UpdateItem tq = new UpdateItem(rootName,serviceName,state);
          this.assertEquals("AMI-NET", tq.getRootName());
          this.assertEquals("Modelling facilities", tq.getServiceName());
          this.assertTrue(tq.getState());

	}
}
