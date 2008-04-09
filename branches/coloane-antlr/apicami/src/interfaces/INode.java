package interfaces;

import java.util.ArrayList;

/**
 * cette interface decrit un noeud.
 * @author KAHOO & UU
 *
 */

public interface INode {

	 /**
	    * Retourne le type du noeud.
	    * @return String
	    */
	String getNodeType();
	

    /**
	 * Retourne l'identifiant unique du noeud.
	 * @return int
	 */
    int getId();
	
    /**
	 * Retourne les attributs du noeud.
	 * @return un tableau de attributs(String).
	 */
	ArrayList<String> getAttribute(); 
	 
}
