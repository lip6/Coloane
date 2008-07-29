package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable de la fin de réception des résultats
 *
 * @author Jean-Baptiste Voron
 */
public class ReceptResultObservable {
	/** Le point de synchronisation */
	private List<IReceptResultObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptResultObservable() {
		this.observers = new ArrayList<IReceptResultObserver>();
	}

	/**
	 * Indique que les observer doivent être notifiés dans une thread à part
	 * @param createThread <code>true</code> si un thread doit être créé
	 */
	public final void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Ajout d'un observer sur cet observable
	 * @param o L'observer
	 */
	public final void addObserver(IReceptResultObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 */
	public final void notifyObservers() {
		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptResultObserver o : this.observers) {
				o.update(null);
			}

		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observers);
			new Thread(thread, "threadConnectionSpecialMessage").start();
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
		private List<IReceptResultObserver> observers;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 */
		public ThreadNotifier(List<IReceptResultObserver> observers) {
			this.observers = observers;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptResultObserver o : this.observers) {
				o.update(null);
			}
		}
	}
}
