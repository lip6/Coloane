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
	private int endingNode;

	 /** identifiant*/
	private int idArc ;

	 /** l'inode du debut*/
	private int startingNode;

/**
 * le constructeur de notre classe
 */

	public Arc(String arcType,int id,int endingNode,int startingNode,ArrayList<String> attribute){

		     this.arcType= arcType;
		     this.attribute  = attribute;
		     this.endingNode = endingNode;
		     this.startingNode = startingNode;
		     this.idArc = id ;

	}

	/**
	 * le constructeur par defaut.
	 */

	public Arc(){
     this.arcType= null;
     this.attribute  = new ArrayList<String>();
     this.endingNode = 0;
     this.startingNode = 0;
     this.idArc = 0;

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

	public int getEndingNode() {
		return this.endingNode;
	}
/**
 * lidentifiant de larc;
 */
	public int getId() {
		return this.idArc;
	}


	/**
	 * l noeud de debut.
	 */
	public int getStartingNode() {
		return this.startingNode;
	}

}
