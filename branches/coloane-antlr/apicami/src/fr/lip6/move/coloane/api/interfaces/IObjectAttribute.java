package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface decrit un attribut dans la table des domaine.
 * @author KAHOO & UU
 *
 */
public interface IObjectAttribute {

	 /**
	    * Retourne le nom de l'attribut.
	    * @return String
	    */
	String getAttributeName();

	 /**
	    * Retourne la nomination.
	    * @return String
	    */
	String getNomination();

	 /**
	    * si unique ou pas.
	    * @return bool.
	    */
	boolean isUnique();

	 /**
	    * si permanent ou pas.
	    * @return bool.
	    */
	boolean isPermanent();

	 /**
	    * Retourne la valeur pas default.
	    * @return String
	    */
	String getDefaultValue();
}
