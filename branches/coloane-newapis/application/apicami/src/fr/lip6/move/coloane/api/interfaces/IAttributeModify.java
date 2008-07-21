package fr.lip6.move.coloane.api.interfaces;


/**
 * cette interface decrit un attribut modifié.
 * @author KAHOO & UU
 *
 */
public interface IAttributeModify {

	 /**
	    * Retourne l'identifiant de l'attribut.
	    * @return int.
	    */
	public int getObjectID();

	 /**
	    * Retourne le type de l'attribut.
	    * @return String
	    */
	public String getAttributeType();

	 /**
	    * Retourne le nouveau contenu de l'attribut.
	    * @return String
	    */
	public String getNewContent();
}
