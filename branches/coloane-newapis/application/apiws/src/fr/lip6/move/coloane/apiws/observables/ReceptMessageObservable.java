package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption de messages.
 *
 * @author Monir CHAOUKI
 */
public class ReceptMessageObservable implements IReceptMessageObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IReceptMessageObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public ReceptMessageObservable() {
		this.listObservers = new ArrayList<IReceptMessageObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public ReceptMessageObservable(boolean createThread) {
		this.listObservers = new ArrayList<IReceptMessageObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IReceptMessageObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(IReceptMessage e) {
		LOGGER.finest("Notification de la récéption d'un message aux observateurs");
		if (!createThread) {
			for (IReceptMessageObserver o : listObservers) {
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
	public final void removeObserver(IReceptMessageObserver o) {
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

		private List<IReceptMessageObserver> listObservers;

		private IReceptMessage message;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param message le message à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IReceptMessageObserver> listObservers, IReceptMessage message) {
			this.listObservers = listObservers;
			this.message = message;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptMessageObserver o : listObservers) {
				o.update(message);
			}
		}
	}

}
