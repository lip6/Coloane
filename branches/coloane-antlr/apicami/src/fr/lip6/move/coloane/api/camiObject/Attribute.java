package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IAttribute;

/**
 * cette classe represente un attribut.
 * @author kahoo & UU.
 *
 */
public class Attribute implements IAttribute {

    /** le type de l'attribut*/
    private String type;

    /** l'identifiant de l'objet à qui il appartient*/
    private int objectId;

    /** le contenu de l'attribut*/
    private String contenu;

    /**
     * le constructeur de notre classe.
     * @param type
     * @param objectId
     * @param contenu
     */

    public Attribute(String type,int objectId,String contenu) {
    	this.type= type;
    	this.objectId = objectId;
    	this.contenu = contenu;
    }

    /**
     * le constructeur par defaut
     */

    public Attribute() {
    	this.type= null;
    	this.objectId = -1;
    	this.contenu = null;
    }

    /**
     * retourne le contenu de l'attribut.
     * @return le contenu.
     */
	public String getContains() {

		return this.contenu;
	}

    /**
     * retourne l'identifiant de l'objet auquel l'atribut appartient.
     * @return le numero de cet objet.
     */
	public int getObjectId() {
		return this.objectId;
	}

    /**
     * retourne le type de l'attribut.
     * @return son type.
     */
	public String getType() {
		return this.type;
	}

}
