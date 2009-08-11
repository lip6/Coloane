package fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN");
		//$JUnit-BEGIN$
		suite.addTestSuite(ExportToImplTest.class);
		//$JUnit-END$
		return suite;
	}

}
