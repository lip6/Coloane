package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.interfaces.model.IAttribute;


/**
 * cette classe represente un type particulier de noeud BOX
 * @author kahoo & UU
 *
 */
public class Box implements IBox {
    /** le tableau des attributs de notre box*/
    private ArrayList<IAttribute> attribute;

    /** l'identifiant du box*/
    private int boxID;

    /** le numero de page du box*/
    private int boxPage;

    /** le type de notre box*/
    private String boxType;

    /**
    * le constructeur de notre classe .
    * @param attribute
    * @param boxID
    * @param boxPage
    * @param boxType
    */
    public Box(ArrayList<IAttribute> attribute,int boxID,int boxPage,String boxType){
    	this.attribute = attribute;
    	this.boxID = boxID;
    	this.boxPage = boxPage;
    	this.boxType = boxType;
    }

    /**
     * le constructeur par defaut.
     */
    public Box(){
    	this.attribute = null;
    	this.boxID = -1;
    	this.boxPage = -1;
    	this.boxType = null;
    }
    /**
     * retourne les attributs de notre box.
     * @return nos attributs ( tableau de IAttribute).
     */
	public ArrayList<IAttribute> getAttribute() {
		return this.attribute;
	}

	/**
	 * retourne l'identifiant de notre box.
	 * @retourn l'id
	 */
	public int getBoxID() {
		return this.boxID;
	}

	/**
	 * retourne le numero de page de notre box.
	 * @return ce numero.
	 */
	public int getBoxPage() {
		return this.boxPage;
	}

	/**
	 * retourne le type de notre box.
	 * @return le type.
	 */
	public String getBoxType() {
		return this.boxType;
	}

}
