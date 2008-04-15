package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IAttributeModify;

public class AttributeModify implements IAttributeModify{

	/** le type de l'attribut*/
	String attributeType;

	/** le nouveau contenu de l'attribut*/
	String newContent;

	/** l'identifiant de l'attribut*/
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
	    * Retourne l'identifiant de l'attribut.
	    * @return int.
	    */

	public String getAttributeType() {

		return this.attributeType;
	}

	 /**
	    * Retourne le type de l'attribut.
	    * @return String
	    */
	public String getNewContent() {

		return this.newContent;
	}

	/**
	    * Retourne le nouveau contenu de l'attribut.
	    * @return String
	    */
	public int getObjectID() {
		return this.objectID;
	}


}
