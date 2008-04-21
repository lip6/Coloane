package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testTest() throws IOException{
		Pair<ISpeaker, IListener> pair = FkInitCom.initCom("127.0.0.1" ,7001);
		pair.speaker.startCommunication("Administrator", "123456");
		pair.speaker.openConnection("blabla", "blabla", "Administrator");
	}

}
