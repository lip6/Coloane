package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable d'une déconnexion de la plate-forme
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class DisconnectObservable {
	/** Liste des observeurs */
	private List<IDisconnectObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public DisconnectObservable() {
		observers = new ArrayList<IDisconnectObserver>();
	}

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
	public final void addObserver(IDisconnectObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 */
	public final void notifyObservers() {
		// Option sans création de thread
		if (!this.createThread) {
			for (IDisconnectObserver o : this.observers) {
				o.update();
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
		private List<IDisconnectObserver> observers;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 */
		public ThreadNotifier(List<IDisconnectObserver> observers) {
			this.observers = observers;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IDisconnectObserver o : this.observers) {
				o.update();
			}
		}
	}
}
