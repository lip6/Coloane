package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;

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
	
	
	/** objet de communication bas niveau */
	 
	private FkComLowLevel fkll;
	private PipedOutputStream pipe;
	
	/**
	 * 
	 * @param lowLevel lowLevel Objet de communication bas niveau
	 * @param pipe Stream ou ecrira Listener les commandes formatées 
	 */
	public Listener(FkComLowLevel lowLevel, PipedOutputStream pipeParam){
		
		fkll = lowLevel;
		this.pipe = pipeParam;
		
		/** Commencer à recevoir les commandes */
		this.start();
	}
	
	/**
	 * Code exécuté par le thread Listener
	 */
	public void run(){
		
		/*TODO On écrit d'abord le parser */
		
		/** ensemble de commandes reçus de Fk lors de chaque lecture */
		ArrayList<String> commandSuite;
		
		try {
			/** boucle de lecture des commandes */
			while(true){
				commandSuite = this.fkll.readCommand();
				
				/* Ecrire les commandes a dans le pipe + EOF à chaque fin 
				 * de commande */
				
				for(int i=0; i < commandSuite.size(); i++){
					byte[] command = commandSuite.get(i).getBytes(); 
					this.pipe.write(command, 0, command.length);
					/* Ecrire EOF dans après la commande */
					
					
				}
				
			}	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}