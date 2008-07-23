package fr.lip6.move.coloane.api.cami.tests;

import junit.framework.TestCase;
import fr.lip6.move.coloane.api.cami.CamiGenerator;

public class CamiGeneratorTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * tester la generation des commandes de
	 * l'ouverture de la session
	 */
	public void testOpenSessionCmd(){

		byte[] s = CamiGenerator.generateCmdOS("model_1.petri", "123456", "AMI-NET");
		System.out.println(new String(s , 4, s.length-4));

		s= CamiGenerator.generateCmdDI();
        System.out.println(new String(s , 4, s.length-4));

		s= CamiGenerator.generateCmdCI("interlocutor", 1);
        System.out.println(new String(s , 4, s.length-4));

		s= CamiGenerator.generateCmdFI();
        System.out.println(new String(s, 4, s.length-4));

	}


}
