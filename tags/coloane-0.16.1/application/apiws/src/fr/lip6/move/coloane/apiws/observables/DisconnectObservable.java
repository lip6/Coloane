package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption de messages.
 *
 * @author Monir CHAOUKI
 */
public class DisconnectObservable implements IDisconnectObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IDisconnectObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public DisconnectObservable() {
		this.listObservers = new ArrayList<IDisconnectObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public DisconnectObservable(boolean createThread) {
		this.listObservers = new ArrayList<IDisconnectObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IDisconnectObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers() {
		LOGGER.finest("Notification de la récéption d'une demande de deconnexion aux observateurs");
		if (!createThread) {
			for (IDisconnectObserver o : listObservers) {
				o.update();
			}
		} else {
			ThreadNotifier threadNotifier = new ThreadNotifier(listObservers);
			threadNotifier.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void removeObserver(IDisconnectObserver o) {
		this.listObservers.remove(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Classe privé représentant un thread pour la notification des observateurs.
	 */
	private class ThreadNotifier extends Thread {

		private List<IDisconnectObserver> listObservers;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 */
		public ThreadNotifier(List<IDisconnectObserver> listObservers) {
			this.listObservers = listObservers;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IDisconnectObserver o : listObservers) {
				o.update();
			}
		}
	}
}
