package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;


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
	ArrayList<String> getTextualResults();

	/**
	    * Retourne les attributs modifiés XA.
	    * @return le tableau des attributs modifiés.
	    */
	ArrayList<IAttributeModify> getModifiedAttributes();

	/**
	    * Retourne les objets designés RO.
	    * @return le tableau des objets designés.
	    */
	ArrayList<String> getObjectsDesigned();


	/**
	    * Retourne les objets mis en evidence ME.
	    * @return le tableau des objets mis en evidence .
	    */
	ArrayList<String> getObjectsOutlined();


	/**
	    * Retourne les attributs mis en evidence MT.
	    * @return le tableau des attributs mis en evidence par objet.
	    */
	ArrayList<ArrayList<String>> getAttributesOutlined();

	 /**
	    * Retourne les noeuds créés CN.
	    * @return le tableau des noeuds créés.
	    */
	ArrayList<INode> getNodesToCreate();

	 /**
	    * Retourne les box créés CB.
	    * @return le tableau des box créés.
	    */
	ArrayList<IBox> getBoxesToCreate();

	/**
	    * Retourne les arcs créés CA.
	    * @return le tableau des arcs créés.
	    */
	ArrayList<IArc> getArcsToCreate();

	 /**
	    * Retourne les objets a supprimer SU.
	    * @return le tableau des identifiants de ces objets.
	    */
	ArrayList<Integer> getObjectsToDelete();


	 /**
	    * Retourne les sous-resultats de notre resultat.
	    * @return le tableau des sous-resultats.
	    */
	ArrayList<IResult> getSubResult();
}

