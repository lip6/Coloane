package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;
import java.util.Vector;

import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.INode;

/**
 * cette classe represente un noeud.
 * @author kahoo & UU
 *
 */
public class Node implements INode{

	/** Type du noeud */
	private String type;

	/** Identificateur du noeud */
	private int nodeId;

	/** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
	private int xPosition;

	/** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
	private int yPosition;

	/** Liste des attributs du noeud*/
	private Vector<IAttribute> listOfAttr;

    /**
    * le constructeur de notre classe .
    * @param attribute
    * @param nodeID
    * @param nodeType
    */
    public Node(String type,int nodeId,int xPosition,int yPosition,Vector<IAttribute> listOfAttr){
    	this.type = type;
    	this.nodeId = nodeId;
    	this.xPosition = xPosition;
    	this.yPosition = yPosition;
    	this.listOfAttr = listOfAttr;
    }

    /**
     * le constructeur par defaut.
     */
    public  Node(){
    	this.type = null;
    	this.nodeId = -1;
    	this.xPosition = -1;
    	this.yPosition = -1;
    	this.listOfAttr = new Vector<IAttribute>();
    }

    public final int getId() {
		return nodeId;
	}

    public final String getNodeType() {
		return this.type;
	}

    public final int getXPosition() {
		return this.xPosition;
	}


	public final int getYPosition() {
		return yPosition;
	}

	public final void addAttribute(IAttribute attribute) {
		if (!(attribute.getValue() == "")) {
			attribute.setRefId(this.getId());
			this.listOfAttr.addElement(attribute);
		}

	}

	public final Vector<IAttribute> getListOfAttr() {
		return this.listOfAttr;
	}

}


