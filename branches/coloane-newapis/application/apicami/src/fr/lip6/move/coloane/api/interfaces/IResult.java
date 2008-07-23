package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;


/**
 * cette interface decrit les resultats.
 * @author KAHOO & UU
 *
 */

public interface IResult {

	 /**
	    * Retourne les resultats textuels RT.
	    * @return le tableau des resultats textuels.
	    */
	public ArrayList<String> getTextualResults();

	/**
	    * Retourne les attributs modifiés XA.
	    * @return le tableau des attributs modifiés.
	    */
	public ArrayList<IAttributeModify> getModifiedAttributes();

	/**
	    * Retourne les objets designés RO.
	    * @return le tableau des objets designés.
	    */
	public ArrayList<String> getObjectsDesigned();


	/**
	    * Retourne les objets mis en evidence ME.
	    * @return le tableau des objets mis en evidence .
	    */
	public ArrayList<String> getObjectsOutlined();


	/**
	    * Retourne les attributs mis en evidence MT.
	    * @return le tableau des attributs mis en evidence par objet.
	    */
	public ArrayList<ArrayList<String>> getAttributesOutlined();

	 /**
	    * Retourne les noeuds créés CN.
	    * @return le tableau des noeuds créés.
	    */
	public ArrayList<INode> getNodesToCreate();

	 /**
	    * Retourne les box créés CB.
	    * @return le tableau des box créés.
	    */
	public ArrayList<IBox> getBoxesToCreate();

	/**
	    * Retourne les arcs créés CA.
	    * @return le tableau des arcs créés.
	    */
	public ArrayList<IArc> getArcsToCreate();

	 /**
	    * Retourne les objets a supprimer SU.
	    * @return le tableau des identifiants de ces objets.
	    */
	public ArrayList<Integer> getObjectsToDelete();


	 /**
	    * Retourne les sous-resultats de notre resultat.
	    * @return le tableau des sous-resultats.
	    */
	public ArrayList<IResult> getSubResult();
}

