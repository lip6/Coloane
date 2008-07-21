package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.IDialogObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IDialogObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

/**
 * Observable des évènements en rapport avec la session.
 *
 * @author kahoo & uu
 *
 */

public class DialogObservable implements IDialogObservable {

	/** liste des observeurs */
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
	public void notifyObservers(IDialog dialog,Integer in) {

		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update(dialog,in);
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(list,dialog,in);
			new Thread(thread, "threadDialog").start();
		}

	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification,
	 * si cette option est active. cette classe est interne.
	 *
	 * @author kahoo & uu
	 *
	 */
	class ThreadNotifier implements Runnable {
		private ArrayList<IDialogObserver> listObservers;
		private IDialog dialog;
        private Integer in;
		
		public ThreadNotifier(ArrayList<IDialogObserver> list, IDialog dialog,Integer in) {
			this.listObservers = list;
			this.dialog = dialog;
			this.in = in;
		}

		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update(this.dialog,this.in);
		}

	}

}


