package fr.lip6.move.coloane.api.FkCommunication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import fr.lip6.move.coloane.api.interfaces.IListener;


/**
 *
 * @author uu & KAHOO
 *
 * Le rôle de cette classe est de lancer dans un thread à part
 * un parser qui va écouter les commandes qui arrivent de FrameKit.
 *
 *
 */

public class Listener extends Thread implements IListener {
	/**
	 * Constructeur
	 * @param lowLevel résponsable des léctures bas niveau
	 */

	/* file */
	private LinkedBlockingQueue fifo;

	/** objet de communication bas niveau */
	private FkComLowLevel fkll;


	/**
	 *
	 * @param lowLevel lowLevel Objet de communication bas niveau
	 * @param pipe Stream ou ecrira Listener les commandes formatées
	 */
	public Listener(FkComLowLevel lowLevel, LinkedBlockingQueue queue){

		fkll = lowLevel;
		this.fifo = queue;
	}

	/**
	 * Code exécuté par le thread Listener
	 */
	@SuppressWarnings("unchecked")
	public void run(){

		/*TODO On écrit d'abord le parser */

		/** ensemble de commandes reçus de Fk lors de chaque lecture */
		String commandSuite;

		try {
			/** boucle de lecture des commandes */
			while(true){
				commandSuite = this.fkll.readCommand();

				/* Ecrire les commandes a dans le pipe + EOF pour commancer
				 * à parser */
               System.out.println("|je recois:  " + commandSuite + "|");
				InputStream is = new ByteArrayInputStream(commandSuite.getBytes());
				
					fifo.put(is);


			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}