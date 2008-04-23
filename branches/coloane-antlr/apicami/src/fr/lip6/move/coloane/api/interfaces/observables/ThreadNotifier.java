package fr.lip6.move.coloane.api.interfaces.observables;

import java.util.Observable;

/**
 * Cette classe s'occupe de créer un thread pour notifier
 * Coloane des différents évènements.
 *
 * @author uu & kahoo
 *
 */

public class ThreadNotifier extends Thread {

	/* L'observable pour lequel on appelle notify */
	Observable obs;

	/* L'argument de la notification */
	Object arg;

	/**
	 * Constructeur : initialise l'observable et l'argument,
	 * et lance le thread
	 * @param o 	Observable
	 * @param arg 	argument
	 */

	public ThreadNotifier(Observable o, Object arg){
		this.obs = o;
		this.arg = arg;
		/* Lancer la notification */
		this.start();
	}

	/**
	 * run
	 */
	public void run(){
		obs.notifyObservers(arg);
	}

}
