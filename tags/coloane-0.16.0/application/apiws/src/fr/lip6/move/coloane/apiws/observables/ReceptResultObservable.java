package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption de résultats.
 *
 * @author Monir CHAOUKI
 */
public class ReceptResultObservable implements IReceptResultObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IReceptResultObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public ReceptResultObservable() {
		this.listObservers = new ArrayList<IReceptResultObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public ReceptResultObservable(boolean createThread) {
		this.listObservers = new ArrayList<IReceptResultObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IReceptResultObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(IResult e) {
		LOGGER.finest("Notification de la récéption d'un résultat aux observateurs");
		if (!createThread) {
			for (IReceptResultObserver o : listObservers) {
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
	public final void removeObserver(IReceptResultObserver o) {
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

		private List<IReceptResultObserver> listObservers;

		private IResult result;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param result le resultat à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IReceptResultObserver> listObservers, IResult result) {
			this.listObservers = listObservers;
			this.result = result;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptResultObserver o : listObservers) {
				o.update(result);
			}
		}
	}

}
