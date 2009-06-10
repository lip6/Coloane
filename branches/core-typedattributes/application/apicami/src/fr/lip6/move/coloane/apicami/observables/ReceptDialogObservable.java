package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable des boites de dialogues en provenance de FrameKit
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptDialogObservable {
	/** Liste des observeurs */
	private List<IReceptDialogObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptDialogObservable() {
		observers = new ArrayList<IReceptDialogObserver>();
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
	public final void addObserver(IReceptDialogObserver o) {
		this.observers.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public final void notifyObservers(IDialog arg) {
		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptDialogObserver o : this.observers) {
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
		private List<IReceptDialogObserver> observers;

		/** L'objet qui doit être envoyés aux observers */
		private IDialog receptMessage;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param receptMenu L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IReceptDialogObserver> observers, IDialog receptMenu) {
			this.observers = observers;
			this.receptMessage = receptMenu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptDialogObserver o : this.observers) {
				o.update(this.receptMessage);
			}
		}
	}
}
