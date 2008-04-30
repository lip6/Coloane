package fr.lip6.move.coloane.api.interfaces;

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
	public String getNodeType();


    /**
	 * Retourne l'identifiant unique du noeud.
	 * @return int
	 */
    public int getId();

    /**
	 * Retourne les attributs du noeud.
	 * @return un tableau de attributs(String).
	 */
	public ArrayList<IAttribute> getAttribute();

}
