package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption de menus.
 *
 * @author Monir CHAOUKI
 */
public class ReceptMenuObservable implements IReceptMenuObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IReceptMenuObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public ReceptMenuObservable() {
		this.listObservers = new ArrayList<IReceptMenuObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public ReceptMenuObservable(boolean createThread) {
		this.listObservers = new ArrayList<IReceptMenuObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IReceptMenuObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(IReceptMenu e) {
		LOGGER.finest("Notification de la récéption d'un menu aux observateurs");
		if (!createThread) {
			for (IReceptMenuObserver o : listObservers) {
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
	public final void removeObserver(IReceptMenuObserver o) {
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

		private List<IReceptMenuObserver> listObservers;

		private IReceptMenu menu;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param menu le menu à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IReceptMenuObserver> listObservers, IReceptMenu menu) {
			this.listObservers = listObservers;
			this.menu = menu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptMenuObserver o : listObservers) {
				o.update(menu);
			}
		}
	}

}
