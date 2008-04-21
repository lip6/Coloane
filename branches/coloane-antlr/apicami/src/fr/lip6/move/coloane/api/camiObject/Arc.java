package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.INode;

/**
 *cette classe implemente linterface IArc.
 * @author kahoo & UU
 *
 */
public class Arc implements IArc{
	 /** le type de l'arc*/
	private String arcType;

	 /** les attributs*/
	private ArrayList<String> attribute;

	 /** l'inode de la fin*/
	private INode endingNode;

	 /** identifiant*/
	private int id ;

	 /** l'inode du debut*/
	private INode startingNode;

/**
 * le constructeur de notre classe
 */

	public Arc(String arcType,ArrayList<String> attribute,INode endingNode,int id,INode startingNode){

		     this.arcType= arcType;
		     this.attribute  = attribute;
		     this.endingNode = endingNode;
		     this.startingNode = startingNode;
		     this.id = id ;

	}

	/**
	 * le constructeur par defaut.
	 */

	public Arc(){
     this.arcType= null;
     this.attribute  = new ArrayList<String>();
     this.endingNode = null;
     this.startingNode = null;
     this.id = 0;

	}

	/**
	 * retourne le type de larc
	 */
	public String getArcType() {
		return this.arcType;
	}

	/**
	 * les attributs de notre arc.
	 */
	public ArrayList<String> getAttribute(){
		return this.attribute;
	}

	/**
	 * le noeud de fin.
	 */

	public INode getEndingNode() {
		return this.endingNode;
	}
/**
 * lidentifiant de larc;
 */
	public int getId() {
		return this.id;
	}


	/**
	 * l noeud de debut.
	 */
	public INode getStartingNode() {
		return this.startingNode;
	}

}
