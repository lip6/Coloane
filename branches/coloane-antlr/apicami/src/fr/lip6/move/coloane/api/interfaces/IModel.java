package fr.lip6.move.coloane.api.interfaces;

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
	public ArrayList<INode> getNodes();

	/**
	 * Donne les box de notre modéle.
	 * @return le tableau des box.
	 */
	public ArrayList<IBox> getBoxes();

	/**
	 * Donne les arcs de notre modéle.
	 * @return le tableau des arcs.
	 */
	public ArrayList<IArc> getArcs();
}
