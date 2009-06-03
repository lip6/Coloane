package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable des messages en provenance de FrameKit
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptMessageObservable {
	/** Liste des observeurs */
	private List<IReceptMessageObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptMessageObservable() {
		observers = new ArrayList<IReceptMessageObserver>();
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
	public final void addObserver(IReceptMessageObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(IReceptMessage arg) {
		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptMessageObserver o : this.observers) {
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
		private List<IReceptMessageObserver> observers;

		/** L'objet qui doit être envoyés aux observers */
		private IReceptMessage receptMessage;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param receptMenu L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IReceptMessageObserver> observers, IReceptMessage receptMenu) {
			this.observers = observers;
			this.receptMessage = receptMenu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptMessageObserver o : this.observers) {
				o.update(this.receptMessage);
			}
		}
	}
}
