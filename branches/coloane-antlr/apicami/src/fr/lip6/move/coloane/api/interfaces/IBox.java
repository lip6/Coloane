package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;

public interface IBox {

	/**
	* Retourne le type du box.
	* @return String
	*/
    String getBoxType();

    /**
	 * Retourne l'identifiant unique du box.
	 * @return int
	 */
	int getBoxID();

	  /**
	 * Retourne le nombre de pages du box.
	 * @return int
	 */
	int getBoxPage();

	 /**
	 * Retourne les attributs du box.
	 * @return un tableau d'attributs .
	 */
	ArrayList<IAttribute> getAttribute();


}
