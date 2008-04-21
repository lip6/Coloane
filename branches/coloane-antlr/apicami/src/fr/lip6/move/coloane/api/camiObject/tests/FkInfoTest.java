package fr.lip6.move.coloane.api.camiObject.tests;

import fr.lip6.move.coloane.api.camiObject.FKVersion;
import fr.lip6.move.coloane.api.camiObject.FkInfo;
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

			FkInfo fk =	new FkInfo (nameService,aboutService,incremental,resultatCalcule );
	     this.assertEquals("FrameKit environment, by F.Kordon, LIP6", fk.getAboutService());
	     this.assertEquals("3", fk.getIncremental());
	     this.assertEquals("FrameKit environment", fk.getNameService());
	     this.assertEquals("2", fk.getResultatCalcule());
	}
}
