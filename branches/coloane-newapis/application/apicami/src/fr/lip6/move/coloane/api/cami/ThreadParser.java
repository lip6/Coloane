package fr.lip6.move.coloane.api.cami;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.session.SessionController;

/**
 * Cette classe lance un thread qui récupère les commandes
 * à parser par l'intermediaire du thread listener et délègue
 * le parsing à un parser ANTLR
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */

public class ThreadParser extends Thread {

	/** file de lecture */
	LinkedBlockingQueue<InputStream> fifo;

	/** parser ANTLR */
	CamiParser parser;

	/**
	 * @param sessionController
	 * @param queue file de messages d'ou le Thread
	 * 		  parser reçoit les InoutStream sur les
	 * 		  commandes
	 *
	 * @param hm HashMap contenant les observables
	 * 		  Cette HashMap sert non seulement à contenir les
	 * 		  Observable, en plus elle est utilisée comme moniteur
	 * 		  de synchronisation
	 */
	public ThreadParser(LinkedBlockingQueue<InputStream> queue, Map<String, Object> hm){
		this.fifo = queue;
		this.parser = new CamiParser(null, SessionController.getInstance(), hm);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run() {
		/* Boucle de récupération des InputStream sur les chaines de commandes */
		while (true) {
			/* se bloquer en attente d'un ensemble de commandes */
			try {
				InputStream is = (InputStream) this.fifo.take();

				//TODO enlever
				//System.out.println("lecture .......... de la file .....");
				//Create an input character stream from standard in
				ANTLRInputStream input = new ANTLRInputStream(is);
				// Create an ExprLexer that feeds from that stream
				CamiLexer lexer = new CamiLexer(input);
				// Create a stream of tokens fed by the lexer
				CommonTokenStream tokens = new CommonTokenStream(lexer);

				// réinitialiser le parser
				parser.setTokenStream(tokens);

				parser.command();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RecognitionException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}
