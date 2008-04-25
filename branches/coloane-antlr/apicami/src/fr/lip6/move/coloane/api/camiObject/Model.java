package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;

/**
 * cette classe represente un model (ensemble de noeuds + arcs + boxs).
 * @author 2565290
 *
 */
public class Model implements IModel{

	/** l'ensemble des arcs*/
	private ArrayList<IArc> arcs;

	/** l'ensemble des boxes*/
	private ArrayList<IBox> boxes;

	/** l'ensemble des noeuds*/
	private ArrayList<INode> nodes;

	/**
	 * notre constructeur.
	 * @param arcs
	 * @param boxes
	 * @param nodes
	 */
	public Model(ArrayList<IArc> arcs,ArrayList<IBox> boxes,ArrayList<INode> nodes){
		this.arcs = arcs;
		this.boxes = boxes;
		this.nodes = nodes;
	}

	/***
	 * le constructeur par defaut.
	 */
	public Model(){
		this.arcs = null;
		this.boxes = null;
		this.nodes = null;
	}
    /**
    * retourne l'ensemble des arcs contenus dans le model.
    * @return les arcs.
    */
	public ArrayList<IArc> getArcs() {

		return this.arcs;
	}

	/**
	 * retourne l'ensemble des boxes contenus dans le model.
	 * @return les boxes.
	 */
	public ArrayList<IBox> getBoxes() {

		return this.boxes;
	}


	/**
	 * retourne l'ensemble des noeuds contenus dans le model.
	 * @return les noeuds.
	 */
	public ArrayList<INode> getNodes() {

		return this.nodes;
	}

}
