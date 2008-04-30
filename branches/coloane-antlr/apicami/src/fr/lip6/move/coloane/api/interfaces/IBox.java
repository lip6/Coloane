package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;

public interface IBox {

	/**
	* Retourne le type du box.
	* @return String
	*/
    public String getBoxType();

    /**
	 * Retourne l'identifiant unique du box.
	 * @return int
	 */
	public int getBoxID();

	  /**
	 * Retourne le nombre de pages du box.
	 * @return int
	 */
	public int getBoxPage();

	 /**
	 * Retourne les attributs du box.
	 * @return un tableau d'attributs .
	 */
	public ArrayList<IAttribute> getAttribute();


}
