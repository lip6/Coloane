package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption d'une erreur.
 *
 * @author Monir CHAOUKI
 */
public class BrutalInterruptObservable implements IBrutalInterruptObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IBrutalInterruptObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public BrutalInterruptObservable() {
		this.listObservers = new ArrayList<IBrutalInterruptObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public BrutalInterruptObservable(boolean createThread) {
		this.listObservers = new ArrayList<IBrutalInterruptObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IBrutalInterruptObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(String e) {
		LOGGER.finest("Notification de la récéption d'une erreur grave aux observateurs");
		if (!createThread) {
			for (IBrutalInterruptObserver o : listObservers) {
				o.update(e);
			}
		} else {
			ThreadNotifier threadNotifier = new ThreadNotifier(listObservers, e);
			threadNotifier.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void removeObserver(IBrutalInterruptObserver o) {
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

		private List<IBrutalInterruptObserver> listObservers;

		private String error;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param error le message d'erreur à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IBrutalInterruptObserver> listObservers, String error) {
			this.listObservers = listObservers;
			this.error = error;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IBrutalInterruptObserver o : listObservers) {
				o.update(error);
			}
		}
	}
}
