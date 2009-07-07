package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.apicami.ApiConnection;
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

	/** L'ApiConnection qui doit être avertie au moment de la réceptiond'un message de déconnexion */
	private ApiConnection api;

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
	 * @param api L'API qui doit être prévenu de la fermeture de connexion
	 */
	public final void addObserver(IBrutalInterruptObserver o, ApiConnection api) {
		this.observers.add(o);
		this.api = api;
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(String arg) {
		// On prévient l'API qu'il se passe quelque chose de potentiellement mauvais
		this.api.notifyBrutalDisconnection();

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
