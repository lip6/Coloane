package fr.lip6.move.coloane.api.FkCommunication.tests;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import fr.lip6.move.coloane.api.communications.FkInitCom;
import fr.lip6.move.coloane.api.communications.Listener;
import fr.lip6.move.coloane.api.communications.ComObjects;
import fr.lip6.move.coloane.api.communications.Speaker;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

import junit.framework.TestCase;

/**
 *
 * @author uu & kahoo
 * Classe de tests unitaires pour la classe Speaker
 */

// TODO tests effectués sans le listener, donc ça teste juste l'écriture.

public class SpeakerTest extends TestCase {

	/** Adresse Ip de la machine à contacter */
	private final static String IP_ADRESS = "127.0.0.1" ;

	/** Port d'écoute de FrameKit */
	private final static int PORT = 7001;

	/** Login */
	private final static String LOGIN = "Administrator";

	/** Password */
	private final static String PASSWORD = "123456";

	/** nom du client */
	private final static String UINAME = "uiName";

	/** nom du client */
	private final static String UIVERSION = "uiVersion";

	Speaker sp;
	Listener ls;

	/** setup */
	protected void setUp() throws Exception {
		ComObjects<ISpeaker, IListener> p = FkInitCom.initCom(this.IP_ADRESS, this.PORT, new LinkedBlockingQueue());
		this.sp = (Speaker) p.speaker;
		this.ls = (Listener)p.listener;

	}

	/** tearDown */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	public void testSpeaker() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * test startCommunication : SC
	 */
	public void testStartCommunication() throws IOException {
		this.sp.startCommunication(this.LOGIN, this.PASSWORD);
	}


	/**
	 * test openCommunication : OC
	 * @throws IOException
	 */
	public void testOpenConnection() throws IOException {
		this.sp.openConnection(this.UINAME, this.UIVERSION, this.LOGIN);
	}

}
