package fr.lip6.move.coloane.api.FkCommunication;

import fr.lip6.move.coloane.api.interfaces.IListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Le rôle de cette classe est de lancer dans un thread à part un parser qui va écouter les commandes qui arrivent de FrameKit.
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */

public class Listener extends Thread implements IListener {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** File des messages qui seront transmis au parser */
	private LinkedBlockingQueue<InputStream> fifo;

	/** Objet de communication bas niveau */
	private FkComLowLevel lowLevel;

	/**
	 * Constructeur
	 * @param lowLevel lowLevel Objet de communication bas niveau
	 * @param queue Stream ou le listener écrira les commandes formatées
	 */
	public Listener(FkComLowLevel lowLevel, LinkedBlockingQueue<InputStream> queue) {
		this.lowLevel = lowLevel;
		this.fifo = queue;
	}

	/**
	 * Code exécuté par le thread Listener
	 */
	@Override
	public final void run() {

		// Ensemble de commandes reçues de Fk lors de chaque lecture */
		String commandSuite;

		try {
			// Boucle de lecture des commandes
			while (true) {
				commandSuite = this.lowLevel.readCommand();

				// Ecrire les commandes dans la queue (EOF est automatiquement ajoute) pour commancer à parser */
				commandSuite = commandSuite.replace("\n\n", "");
				InputStream is = new ByteArrayInputStream(commandSuite.getBytes());
				fifo.put(is);
			}
		} catch (IOException e) {
			LOGGER.warning("Tentative de lecture sur une socket fermee");
			lowLevel.closeCommunication();
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
