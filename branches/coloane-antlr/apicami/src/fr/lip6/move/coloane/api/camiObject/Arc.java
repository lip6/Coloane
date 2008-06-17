package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;
import java.util.Vector;

import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.api.interfaces.IInflexPoint;
import fr.lip6.move.coloane.api.camiObject.InflexPoint;


/**
 *cette classe implemente linterface IArc.
 * @author kahoo & UU
 *
 */
public class Arc implements IArc{

	/** Type de l'arc */
	private String type;

	/** Identifiant de l'arc */
	private int id;

	/** Vecteur contenant l'ensemble des objets de type Attribut de l'arc. */
	private Vector<IAttribute> listOfAttr;

	/** Vecteur contenant l'ensemble des points intermediaire de type Position. */
	private Vector<IInflexPoint> listOfPI;
	
	/** Noeud d'entree de l'arc. */
	private int startingNode;

	/** Noeud de sortie de l'arc. */
	private int endingNode;

	/**
	 * Constructeur de la classe Arc.
	 * @param arcType Type de l'arc
	 * @param arcId Identifant unique de l'arc
	 */
	public Arc(String arcType, int arcId,int i,int j,Vector<IAttribute> listOfAttr,Vector<IInflexPoint> listOfPI7) {
		this.type = arcType;
		this.id = arcId;
		this.listOfAttr = listOfAttr;
        this.listOfPI = listOfPI7;
		this.startingNode = i;
		this.endingNode = j;
	}



	public final String getArcType() {
		return this.type;
	}




	public final int getId() {
		return this.id;
	}



	public final int getStartingNode() {
		return this.startingNode;
	}


	public final int getEndingNode() {
		return this.endingNode;
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

	  public final Vector<IInflexPoint> getListOfPI() {
	 	   return this.listOfPI;
    }
	  
	  public final void addPI(IInflexPoint p)  {
		   	
		  	       listOfPI.add( p);
		       }
	  public final void addPI(IInflexPoint p,int index)  {
		   	
 	       listOfPI.add(index, p);
      }



	
}
