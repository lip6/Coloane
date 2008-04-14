package fr.lip6.move.coloane.api.FkCommunication;

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
	 */
	public Listener(){};
}
