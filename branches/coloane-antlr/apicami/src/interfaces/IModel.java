package interfaces;

import java.util.ArrayList;

/**
 * cette interface représente un model.
 * @author KAHOO & UU
 *
 */
public interface IModel {

	/**
	 * Donne les noeuds de notre modéle.
	 * @return le tableau des noeuds.
	 */
	ArrayList<INode> getNodes();
	
	/**
	 * Donne les box de notre modéle.
	 * @return le tableau des box.
	 */
	ArrayList<IBox>   getBoxes();
	
	/**
	 * Donne les arcs de notre modéle.
	 * @return le tableau des arcs.
	 */
	ArrayList<IArc>   getArcs();
}
