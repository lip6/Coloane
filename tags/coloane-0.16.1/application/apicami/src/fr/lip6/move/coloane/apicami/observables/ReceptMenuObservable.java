package fr.lip6.move.coloane.apicami.observables;

import fr.lip6.move.coloane.apicami.objects.ReceptMenu;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable des événements en rapport avec la session
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptMenuObservable {
	/** Liste des observeurs */
	private List<IReceptMenuObserver> observers;

	/** Création de thread nécessaire ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ReceptMenuObservable() {
		observers = new ArrayList<IReceptMenuObserver>();
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
	public final void addObserver(IReceptMenuObserver o) {
		this.observers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers(List<ISubMenu> menus, List<IUpdateMenu> updateMenus) {
		IReceptMenu receptMenu = new ReceptMenu(menus, updateMenus);

		// Option sans création de thread
		if (!this.createThread) {
			for (IReceptMenuObserver o : this.observers) {
				o.update(receptMenu);
			}

			// Option avec création de thread
		} else {
			ThreadNotifier thread = new ThreadNotifier(this.observers, receptMenu);
			new Thread(thread, "threadSession").start();
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
		private List<IReceptMenuObserver> observers;

		/** L'objet qui doit être envoyés aux observers */
		private IReceptMenu receptMenu;

		/**
		 * Constructeur
		 * @param observers La liste des observers
		 * @param receptMenu L'objet à transmettre aux observers
		 */
		public ThreadNotifier(List<IReceptMenuObserver> observers, IReceptMenu receptMenu) {
			this.observers = observers;
			this.receptMenu = receptMenu;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run() {
			for (IReceptMenuObserver o : this.observers) {
				o.update(this.receptMenu);
			}
		}
	}
}
