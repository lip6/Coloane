package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.INode;

/**
 * cette classe represente un noeud.
 * @author kahoo & UU
 *
 */
public class Node implements INode{

	  /** le tableau des attributs de notre noeud*/
    private ArrayList<IAttribute> attribute;

    /** l'identifiant du noeud*/
    private int nodeID;

    /** le type de notre noeud*/
    private String nodeType;

    /**
    * le constructeur de notre classe .
    * @param attribute
    * @param nodeID
    * @param nodeType
    */
    public Node(ArrayList<IAttribute> attribute,int nodeID,String nodeType){
    	this.attribute = attribute;
    	this.nodeID = nodeID;
    	this.nodeType = nodeType;
    }

    /**
     * le constructeur par defaut.
     */
    public  Node(){
    	this.attribute = null;
    	this.nodeID = -1;
    	this.nodeType = null;
    }
	public ArrayList<IAttribute> getAttribute() {
     return this.attribute;
	}

	public int getId() {
    return this.getId();
	}

	public String getNodeType() {
    return this.getNodeType();
	}

}
