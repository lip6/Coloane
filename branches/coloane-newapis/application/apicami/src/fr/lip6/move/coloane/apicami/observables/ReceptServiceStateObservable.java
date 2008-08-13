package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;

import java.util.logging.Logger;

/**
 * Observable des événements en rapport avec un service state
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptServiceStateObservable {
	/** Le Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Liste des observeurs */
	private IReceptServiceStateObserver observer;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * {@inheritDoc}
	 */
	public final void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Ajout d'un observer sur cet observable
	 * @param o L'observer
	 */
	public final void setObserver(IReceptServiceStateObserver o) {
		this.observer = o;
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(IReceptServiceState arg) {
		LOGGER.finer("Envoie d'un message d'etat : " + arg.getMessage());
		// Option sans création de thread
		if (!this.createThread) {
			observer.update(arg);

		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observer, arg);
			new Thread(thread, "threadConnectionSpecialServiceState").start();
		}
	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification, si cette option est active.<br>
	 * Cette classe est interne.
	 *
	 * @author Kahina Bouarab
	 * @author Youcef Belattaf
	 *
	 */
	private class ThreadNotifier implements Runnable {
		/** Liste des observeurs */
		private IReceptServiceStateObserver observer;

		/** L'objet qui doit être envoyés aux observers */
		private IReceptServiceState receptServiceState;

		/**
		 * Constructeur
		 * @param observer L'unique observateur de ces événements
		 * @param receptMenu L'objet à transmettre aux observers
		 */
		public ThreadNotifier(IReceptServiceStateObserver observer, IReceptServiceState receptMenu) {
			this.observer = observer;
			this.receptServiceState = receptMenu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			this.observer.update(this.receptServiceState);
		}
	}
}

