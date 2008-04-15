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



	public String getArcType() {
		return this.arcType;
	}

	public ArrayList<String> getAttribute(){
		return this.attribute;
	}

	public INode getEndingNode() {
		return this.endingNode;
	}

	public int getId() {
		return this.id;
	}

	public INode getStartingNode() {
		return this.startingNode;
	}

}
