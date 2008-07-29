package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IMyReceptErrorObservable;
import fr.lip6.move.coloane.apiws.interfaces.observer.IMyReceptErrorObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représent l'événement observable: récéption d'une 'FATAL ERROR' de la part du wrapper
 */
public class MyReceptErrorObservable implements IMyReceptErrorObservable {

	private List<IMyReceptErrorObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public MyReceptErrorObservable() {
		this.listObservers = new ArrayList<IMyReceptErrorObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public MyReceptErrorObservable(boolean createThread) {
		this.listObservers = new ArrayList<IMyReceptErrorObserver>();
		this.createThread = createThread;

	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IMyReceptErrorObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(String e) {
		if (!createThread) {
			for (IMyReceptErrorObserver o : listObservers) {
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
	public final void removeObserver(IMyReceptErrorObserver o) {
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

		private List<IMyReceptErrorObserver> listObservers;

		private String error;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param error le message d'erreur reçu
		 */
		public ThreadNotifier(List<IMyReceptErrorObserver> listObservers, String error) {
			this.listObservers = listObservers;
			this.error = error;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IMyReceptErrorObserver o : listObservers) {
				o.update(error);
			}
		}
	}

}
