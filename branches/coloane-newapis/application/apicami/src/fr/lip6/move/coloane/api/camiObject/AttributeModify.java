package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IAttributeModify;

/**
 * cette classe represente une modification d'un attribut.
 * @author kahoo & UU
 *
 */
public class AttributeModify implements IAttributeModify{

	/** le type de l'attribut*/
	String attributeType;

	/** le nouveau contenu de l'attribut*/
	String newContent;

	/** l'identifiant de l'objet auquel il appartient*/
	int objectID;


	/**
	 * le constructeur de notre classe
	 * @param attributeType
	 * @param newContent
	 * @param objectID
	 */
	 public AttributeModify(String attributeType, String newContent,
			int objectID) {
		this.attributeType = attributeType;
			this.newContent= newContent;
			this.objectID = objectID;
	}


	 /**
	    * Retourne le type de l'attribut.
	    * @return String
	    */
	public String getAttributeType() {

		return this.attributeType;
	}

	/**
	    * Retourne le nouveau contenu de l'attribut.
	    * @return String
	    */
	public String getNewContent() {

		return this.newContent;
	}


	/**
	    * Retourne l'identifiant de l'objet ou il est.
	    * @return int.
	    */
	public int getObjectID() {
		return this.objectID;
	}


}
