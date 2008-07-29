package fr.lip6.move.coloane.api.observables;


import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Observable des événements en rapport avec un service state
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptServiceStateObservable {

	/** Liste des observeurs */
	private List<IReceptServiceStateObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptServiceStateObservable() {
		observers = new ArrayList<IReceptServiceStateObserver>();
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
	public final void addObserver(IReceptServiceStateObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(IReceptServiceState arg) {
		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptServiceStateObserver o : this.observers) {
				o.update(arg);
			}

		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observers, arg);
			new Thread(thread, "threadConnectionSpecialServiceState").start();
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
		private List<IReceptServiceStateObserver> observers;

		/** L'objet qui doit être envoyés aux observers */
		private IReceptServiceState receptServiceState;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param receptMenu L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IReceptServiceStateObserver> observers, IReceptServiceState receptMenu) {
			this.observers = observers;
			this.receptServiceState = receptMenu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptServiceStateObserver o : this.observers) {
				o.update(this.receptServiceState);
			}
		}
	}
}

