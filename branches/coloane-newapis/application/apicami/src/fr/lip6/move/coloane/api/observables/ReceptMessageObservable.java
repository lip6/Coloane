package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO : A documenter
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public class ReceptMessageObservable implements IReceptMessageObservable {

	/** liste des observateurs */
	private List<IReceptMessageObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public SpecialMessageObservable() {
		list = new ArrayList<IReceptMessageObserver>();
	}

	/**
	 * Set de la variable createThread
	 * @param createThread notification avec ou sans création de thread
	 */
	public final void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Ajoute un observer
	 * @param o L'observer à ajouter
	 */
	public final void addObserver(IReceptMessageObserver o) {
		this.list.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void removeObserver(IReceptMessageObserver o) {
		this.list.remove(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(IReceptMessage arg) {
		// Option sans création de thread
		if (!this.createThread) {
			for (int i = 0; i < this.list.size(); i++) {
				this.list.get(i).update(arg);
			}
		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.list, arg);
			new Thread(thread, "threadConnectionSpecialMessage").start();
		}
	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification, si cette option est active.
	 */
	private class ThreadNotifier implements Runnable {
		private List<IReceptMessageObserver> listObservers;
		private IReceptMessage version;

		/**
		 * TODO : A documenter
		 * @param list
		 * @param arg
		 */
		public ThreadNotifier(List<IReceptMessageObserver> list, IReceptMessage arg) {
			this.listObservers = list;
			this.version = arg;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++) {
				this.listObservers.get(i).update(version);
			}
		}

	}
}
