package fr.lip6.move.coloane.api.camiObject.tests;

import fr.lip6.move.coloane.api.camiObject.FKVersion;
import junit.framework.TestCase;

public class FKVersionTest extends TestCase {


	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFKVersion() {
		String FkName = "FrameKit";
	     int FKMajor = 2 ;
	     int FKMinor = 1 ;
	     FKVersion fk = new FKVersion(FkName,FKMajor,FKMinor);
	     this.assertEquals("FrameKit", fk.getFkName());
	     this.assertEquals(2, fk.getFkMajor());
	     this.assertEquals(1, fk.getFkMinor());
	}
}
