package fr.lip6.move.coloane.api.FkCommunication;

import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

/**
 * 
 * @author UU && KAHOO
 *	
 * Cette classe construit les objets 
 * 	- Listner
 *  - Speaker
 *  
 *  et renvoie leurs interfaces
 */

public class FkComFactory {
	
	/**
	 * Fabrique un objet Listner
	 * @return retourne une interface IListener
	 */
	public static IListener getFkComListener(){
		return new Listener();
	}
	
	/**
	 * Fabrique un objet Speaker
	 * @return retourne l'interface ISpeaker
	 */
	public static ISpeaker getFkComSpeaker(){
		return new Speaker();
	}
	
}
