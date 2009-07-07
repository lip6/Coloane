package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

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
	 * @param result L'objet contenant tous les résultats
	 */
	public final void notifyObservers(IResult result) {
		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptResultObserver o : this.observers) {
				o.update(result);
			}

		// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observers, result);
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

		/** Les résultats */
		private IResult result;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param result Les objets contenant tous les résultats
		 */
		public ThreadNotifier(List<IReceptResultObserver> observers, IResult result) {
			this.observers = observers;
			this.result = result;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptResultObserver o : this.observers) {
				o.update(this.result);
			}
		}
	}
}
