package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.observables.IDialogObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IDialogObserver;


public class DialogObservable implements IDialogObservable{

	/** liste des observateurs */
	private ArrayList<IDialogObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public DialogObservable() {
		list = new ArrayList<IDialogObserver>();
	}

	/**
	 * set de la variable createThread
	 *
	 * @param createThread
	 *            notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Ajoute un observer
	 *
	 * @param o
	 *            L'observer à ajouter
	 */
	public void addObserver(IDialogObserver o) {
		this.list.add(o);
	}

	/**
	 * Notifier tous les observers
	 *
	 * @param arg
	 *            argument de la notification.
	 */
	public void notifyObservers() {


	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification,
	 * si cette option est active.
	 *
	 * @author kahoo & uu
	 *
	 */
	private class ThreadNotifier implements Runnable {
		private ArrayList<IDialogObserver> listObservers;
		private IFkVersion version;

		public ThreadNotifier(ArrayList<IDialogObserver> list
				) {
			this.listObservers = list;
			this.version = null;
		}

		public void run() {


		}

	}

}

