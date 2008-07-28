package fr.lip6.move.coloane.api.observables;


import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;



/**
 * l'observable associé aux boites de dialogues.
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */

public class ReceptDialogObservable  {

	/** liste des observeurs */
	private List<IReceptDialogObserver> list;

	/** par defaut , ne pas créer de thread */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptDialogObservable() {
		list = new ArrayList<IReceptDialogObserver>();
	}

	/**
	 * notifier avec ou sans creation d'un thread?
	 * @param createThread true or false
	 */
	public final void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * abonner des observateurs a cet evenement
	 * @param o l'observateur.
	 */
	public final void addObserver(IReceptDialogObserver o) {
		this.list.add(o);
	}
	/**
	 * Notifier tous les observers
	 * @param dialog argument de la notification.
	 */
	public final void notifyObservers(IDialog dialog) {
		// Option sans création de thread
		if (!this.createThread) {
			for (int i = 0; i < this.list.size(); i++) {
				this.list.get(i).update(dialog);
			}
         //Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(list, dialog);
			new Thread(thread, "threadDialog").start();
		}

	}
	/**
	 * effacer lobservateur
	 * @param o lobservateur
	 */
	public final void removeObserver(IReceptDialogObserver o) {
     this.list.remove(o);

	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification,
	 * si cette option est active. cette classe est interne.
	 *
	 * @author Kahina Bouarab
     * @author Youcef Belattaf
	 *
	 */
	class ThreadNotifier implements Runnable {
		/** Liste des observeurs */
		private List<IReceptDialogObserver> listObservers;

		/** L'objet qui doit être envoyés aux observers */
		private IDialog dialog;

		/**
		 * Constructeur
		 * @param list La liste des observers
		 * @param dialog L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IReceptDialogObserver> list, IDialog dialog) {
			this.listObservers = list;
			this.dialog = dialog;
		}
		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++) {
				this.listObservers.get(i).update(this.dialog);
			}
		}

	}


}


