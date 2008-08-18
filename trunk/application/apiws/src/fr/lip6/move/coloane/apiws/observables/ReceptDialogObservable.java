package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent l'événement observable: récéption d'une boîte de dialogue.
 *
 * @author Monir CHAOUKI
 */
public class ReceptDialogObservable implements IReceptDialogObservable {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IReceptDialogObserver> listObservers;

	private boolean createThread;

	/**
	 * Constructeur
	 */
	public ReceptDialogObservable() {
		this.listObservers = new ArrayList<IReceptDialogObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur
	 * @param createThread définie s'il faut ou non, un thread pour la notification
	 */
	public ReceptDialogObservable(boolean createThread) {
		this.listObservers = new ArrayList<IReceptDialogObserver>();
		this.createThread = createThread;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IReceptDialogObserver o) {
		this.listObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(IDialog dialog) {
		LOGGER.finest("Notification de la récéption d'une boîte de dialogue aux observateurs");
		if (!createThread) {
			for (IReceptDialogObserver o : listObservers) {
				o.update(dialog);
			}
		} else {
			ThreadNotifier threadNotifier  = new ThreadNotifier(listObservers, dialog);
			threadNotifier.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void removeObserver(IReceptDialogObserver o) {
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

		private List<IReceptDialogObserver> listObservers;

		private IDialog dialog;

		/**
		 * Constructeur
		 * @param listObservers la liste des observateurs à mettre à jours.
		 * @param dialog la boîte de dialogue à fournir aux observateurs.
		 */
		public ThreadNotifier(List<IReceptDialogObserver> listObservers, IDialog dialog) {
			this.listObservers = listObservers;
			this.dialog = dialog;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptDialogObserver o : listObservers) {
				o.update(dialog);
			}
		}
	}

}
