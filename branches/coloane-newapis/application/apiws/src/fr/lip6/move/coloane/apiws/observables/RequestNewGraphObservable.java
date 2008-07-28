package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IRequestNewGraphObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IRequestNewGraphObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représent l'événement observable: demande d'un nouveau graph au core de Coloane.
 */
public class RequestNewGraphObservable implements IRequestNewGraphObservable {

	private List<IRequestNewGraphObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public RequestNewGraphObservable() {
		this.listObservers = new ArrayList<IRequestNewGraphObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public RequestNewGraphObservable(boolean createThread) {
		this.listObservers = new ArrayList<IRequestNewGraphObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IRequestNewGraphObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(String e) {
		if (!createThread) {
			for (IRequestNewGraphObserver o : listObservers) {
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
	public final void removeObserver(IRequestNewGraphObserver o) {
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

		private List<IRequestNewGraphObserver> listObservers;

		private String formalism;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param formalism le message à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IRequestNewGraphObserver> listObservers, String formalism) {
			this.listObservers = listObservers;
			this.formalism = formalism;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IRequestNewGraphObserver o : listObservers) {
				o.update(formalism);
			}
		}
	}

}
