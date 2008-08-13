package fr.lip6.move.coloane.apicami.communications;

import fr.lip6.move.coloane.apicami.parse.CamiLexer;
import fr.lip6.move.coloane.apicami.parse.CamiParser;
import fr.lip6.move.coloane.apicami.parse.ICamiParserState;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
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
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Le Parser */
	private CamiParser parser;

	/** Le flux d'entrée */
	private FkInputStream input;

	/** La table des observers */
	private Map<String, Object> hash;

	/**
	 * Constructeur du listener
	 * @param socket La socket de connexion ouverte sur FrameKit
	 * @throws IOException En cas de problème de connexion entre la socket et la plate-forme
	 */
	public Listener(Socket socket) throws IOException {
		this.input = new FkInputStream(socket.getInputStream());
	}

	/**
	 * {@inheritDoc}
	 */
	public final void run() {
		LOGGER.finer("Lancement du parser");

		/* Boucle de récupération des InputStream sur les chaines de commandes */
		while (true) {
			String toParse = null;
			try {
				int state = ICamiParserState.DEFAULT_STATE;
				if (this.parser != null) { state = this.parser.getState(); }
				toParse = this.input.getCommands(state);
			} catch (IOException ioe) {
				break;
			}
			CharStream antlrStream = new ANTLRStringStream(toParse);
			CamiLexer lexer = new CamiLexer(antlrStream);
			this.parser = new CamiParser(new CommonTokenStream(lexer));
			this.parser.setObservers(this.hash);

			try {
				LOGGER.finer("Invocation de la methode main du CamiParser (" + toParse + ")");

				// Lecture de l'heuristique pour accélerer le parsing
				switch (this.parser.getState()) {
				case ICamiParserState.RESULT_STATE:
					LOGGER.finest("Le parser est invoque sur les resultats");
					parser.results();
					break;
				case ICamiParserState.OPENSESSION_STATE:
					LOGGER.finest("Le parser est invoque sur l'ouverture de session");
					parser.results();
					break;
				default:
					parser.main();
					break;
				}
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
		this.hash = hash;
	}

	/**
	 * Fermeture
	 */
	public final void stop() {
		// TODO : Virer ca...
		System.err.println("Fermeture ??");
	}
}
