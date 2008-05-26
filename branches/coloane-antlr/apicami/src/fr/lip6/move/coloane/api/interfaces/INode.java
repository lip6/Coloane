package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;
import java.util.Vector;

/**
 * cette interface decrit un noeud.
 * @author KAHOO & UU
 *
 */

public interface INode {

	 /**
	    * Retourne le type du noeud.
	    * @return String
	    */
	  public String getNodeType();


    /**
	 * Retourne l'identifiant unique du noeud.
	 * @return int
	 */
     public int getId();

     public int getXPosition() ;


 	public int getYPosition() ;


 	public void addAttribute(IAttribute attribute) ;


 	public Vector<IAttribute> getListOfAttr() ;



 }


