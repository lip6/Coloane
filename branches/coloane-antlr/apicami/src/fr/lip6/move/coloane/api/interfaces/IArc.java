package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;

/**
 * cette interface decrit un arc.
 * @author KAHOO & UU
 *
 */
public interface IArc {

	   /**
	    * Retourne le type de l'arc.
	    * @return String
	    */
	    String getArcType();

	    /**
		 * Retourne l'identifiant unique de l'arc.
		 * @return int
		 */
	    int getId();


	    /**
		 * Retourne le noeud d'entree de l'arc.
		 * @return INode
		 * @see INode
		 */
		INode getStartingNode();


		/**
		 * Retourne le noeud de sortie de l'arc.
		 * @return INode
		 * @see INode
		 */
		INode getEndingNode();

		/**
		 * Retourne les attributs de l'arc.
		 * @return un tableau de attributs(String).
		 */
		ArrayList<String> getAttribute();

	}

