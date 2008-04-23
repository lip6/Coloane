package fr.lip6.move.coloane.api.cami.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;

import fr.lip6.move.coloane.api.cami.ThreadParser;

import junit.framework.TestCase;

/**
 * Classe de test du thread parser
 * @author 2760587
 *
 */

public class ThreadParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * test du thread parser
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void testThreadParser() throws InterruptedException, IOException{
		/* créer la file + le thread */
		LinkedBlockingQueue fifo = new LinkedBlockingQueue();
		ThreadParser tp = new ThreadParser(fifo);

		/* Lancer le thread */
		tp.start();

		for(int i=0; i<1 ; i++){
			InputStream is = new ByteArrayInputStream("SC(52:FrameKit version 2.1.0 on hephaistos.lip6.fr (Linux))".getBytes());

			fifo.put(is);
			is = new ByteArrayInputStream("OC(2,1)".getBytes());
			fifo.put(is);
			System.out.println("test" + i);
		}

	}

}
