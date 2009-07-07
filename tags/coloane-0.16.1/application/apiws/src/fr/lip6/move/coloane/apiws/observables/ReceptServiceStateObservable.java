package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptServiceStateObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption d'informations le service en cours d'exécution.
 *
 * @author Monir CHAOUKI
 */
public class ReceptServiceStateObservable implements IReceptServiceStateObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IReceptServiceStateObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public ReceptServiceStateObservable() {
		this.listObservers = new ArrayList<IReceptServiceStateObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public ReceptServiceStateObservable(boolean createThread) {
		this.listObservers = new ArrayList<IReceptServiceStateObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IReceptServiceStateObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(IReceptServiceState e) {
		LOGGER.finest("Notification de la récéption d'une information sur l'execution d'un service aux observateurs");
		if (!createThread) {
			for (IReceptServiceStateObserver o : listObservers) {
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
	public final void removeObserver(IReceptServiceStateObserver o) {
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

		private List<IReceptServiceStateObserver> listObservers;

		private IReceptServiceState info;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param info l'information sur le service en cours d'exécution.
		 */
		public ThreadNotifier(List<IReceptServiceStateObserver> listObservers, IReceptServiceState info) {
			this.listObservers = listObservers;
			this.info = info;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptServiceStateObserver o : listObservers) {
				o.update(info);
			}
		}
	}

}
