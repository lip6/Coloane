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
	    public String getArcType();

	    /**
		 * Retourne l'identifiant unique de l'arc.
		 * @return int
		 */
	    public int getId();


	    /**
		 * Retourne le noeud d'entree de l'arc.
		 * @return numero du INode
		 * @see INode
		 */
		public int getStartingNode();


		/**
		 * Retourne le noeud de sortie de l'arc.
		 * @return numero du INode
		 * @see INode
		 */
		public int getEndingNode();

		/**
		 * Retourne les attributs de l'arc.
		 * @return un tableau de attributs(String).
		 */
		public ArrayList<IAttribute> getAttribute();

	}

