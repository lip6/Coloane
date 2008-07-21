package fr.lip6.move.coloane.api.camiObject.tests;

import fr.lip6.move.coloane.api.camiObject.ConnectionVersion;
import fr.lip6.move.coloane.api.camiObject.SessionInfo;
import junit.framework.TestCase;

public class FkInfoTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFKInfo() {
		    String aboutService = "FrameKit environment, by F.Kordon, LIP6";
			String incremental = "3";
			String nameService = "FrameKit environment";
			String resultatCalcule = "2";

			SessionInfo fk =	new SessionInfo (nameService,aboutService,incremental,resultatCalcule );
	     this.assertEquals("FrameKit environment, by F.Kordon, LIP6", fk.getAboutService());
	     this.assertEquals("3", fk.getIncremental());
	     this.assertEquals("FrameKit environment", fk.getNameService());
	     this.assertEquals("2", fk.getResultatCalcule());
	}
}
