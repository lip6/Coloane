package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable d'une déconnexion <b>brutale</b> de la part de la plate-forme
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class BrutalInterruptObservable {
	/** Liste des observeurs */
	private List<IBrutalInterruptObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public BrutalInterruptObservable() {
		observers = new ArrayList<IBrutalInterruptObserver>();
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
	public final void addObserver(IBrutalInterruptObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(String arg) {
		// Option sans création de thread
		if (!this.createThread) {
			for (IBrutalInterruptObserver o : this.observers) {
				o.update(arg);
			}

		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observers, arg);
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
		private List<IBrutalInterruptObserver> observers;

		/** L'objet qui doit être envoyés aux observers */
		private String message;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param message L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IBrutalInterruptObserver> observers, String message) {
			this.observers = observers;
			this.message = message;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IBrutalInterruptObserver o : this.observers) {
				o.update(this.message);
			}
		}
	}
}
