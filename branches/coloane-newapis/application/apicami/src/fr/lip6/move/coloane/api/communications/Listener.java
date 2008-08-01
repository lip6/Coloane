package fr.lip6.move.coloane.api.communications;

import fr.lip6.move.coloane.api.cami.CamiLexer;
import fr.lip6.move.coloane.api.cami.CamiParser;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;

/**
 * Cette classe lance un thread qui récupère les commandes
 * à parser par l'intermediaire du thread listener et délègue
 * le parsing à un parser ANTLR
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */

public class Listener implements Runnable {

	/** Parser ANTLR */
	private CamiParser parser;

	/**
	 * Constructeur du listener
	 * @param socket La socket de connexion ouverte sur FrameKit
	 * @throws IOException En cas de problème de connexion entre la socket et la plate-forme
	 */
	public Listener(Socket socket) throws IOException {
		FkInputStream fin = new FkInputStream(socket.getInputStream());
		CharStream antlrSocket = new ANTLRSocketStream(fin);
		CamiLexer lexer = new CamiLexer(antlrSocket);
		this.parser = new CamiParser(new DynamicTokenStream(lexer));
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run() {
		/* Boucle de récupération des InputStream sur les chaines de commandes */
		while (true) {
			try {
				parser.main();
			} catch (RecognitionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Transmets les observers au parser pourqu'il puisse renvoyer les objets à la session
	 * @param hash La map des observers
	 */
	public final void setObservers(Map<String, Object> hash) {
		this.parser.setObservers(hash);
	}

	/**
	 * Fermeture
	 */
	public final void stop() {
		// TODO : Virer ca...
		System.err.println("Fermeture ??");
	}
}
