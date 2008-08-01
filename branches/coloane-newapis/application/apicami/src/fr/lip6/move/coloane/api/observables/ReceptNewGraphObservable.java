package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IRequestNewGraphObserver;

/**
 * Observable des boites de dialogues en provenance de FrameKit
 *
 * @author Jean-Baptiste Voron
 *
 */
public class ReceptNewGraphObservable {
	/** L'unique observer pour cet observable */
	private IRequestNewGraphObserver observer;

	/**
	 * Ajout d'un observer sur cet observable
	 * @param o L'observer
	 */
	public final void setObserver(IRequestNewGraphObserver o) {
		this.observer = o;
	}

	/**
	 * Notifier tous les observers
	 * @param formalism Le formalisme demandée pour le nouveau modèle
	 */
	public final void notifyObservers(String formalism) {
		ThreadNotifier thread = new ThreadNotifier(this.observer, formalism);
		new Thread(thread, "threadConnectionSpecialMessage").start();
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
		private IRequestNewGraphObserver observer;

		/** Le formalisme demandé */
		private String formalism;

		/**
		 * Constructeur
		 * @param observer L'observer qui doit être notifé
		 * @param formalism Le formalisme que doit respecter le nouveau modèle
		 */
		public ThreadNotifier(IRequestNewGraphObserver observer, String formalism) {
			this.observer = observer;
			this.formalism = formalism;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			this.observer.update(this.formalism);
		}
	}
}
