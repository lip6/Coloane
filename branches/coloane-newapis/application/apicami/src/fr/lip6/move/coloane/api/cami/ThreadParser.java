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

/**
 * Cette classe lance un thread qui récupère les commandes
 * à parser par l'intermediaire du thread Listener et delègue
 * le parsing à un parser ANTLR
 *
 * @author kahoo & uu
 *
 */

public class ThreadParser extends Thread {

	/** file de lecture */
	LinkedBlockingQueue fifo;

	/** parser ANTLR */
	CamiParser parser;

	/** SessionController*/
	ISessionController sessionCont;

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
	public ThreadParser(ISessionController sessionController, LinkedBlockingQueue queue, Map<String, Object> hm){
		this.fifo = queue;
		this.parser = new CamiParser(null, sessionController, hm);
		this.sessionCont=sessionController;
	}


	/**
	 * Code exécuté par le thread
	 */
	public void run(){

		/* Boucle de récupération des InputStream sur les chaines de
		 * commandes */
		while(true){
			/* se bloquer en attente d'un ensemble de commandes */
			try {
			
				InputStream is;
				
				is = (InputStream)this.fifo.take();
			
				

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
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (RecognitionException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}
	}

}
