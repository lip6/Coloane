package fr.lip6.move.coloane.interfaces.model;

public interface IAttribute extends Cloneable {

	/**
	 * Retourne le nom de l'attribut.
	 * @return String
	 */
	String getName();

	/**
	 * Cette methode permet de fixer les coordonnees spatiales de l'attribut
	 * @param x est la valeur de la coordonee x
	 * @param y est la valeur de la coordonee y
	 */
	void setPosition(int x, int y);

	/**
	 * Retourne l'abscisse de l'attribut.
	 * @return int
	 */
	int getXPosition();

	/**
	 * Retourne l'ordonnee de l'attribut.
	 * @return la coordonnee y de l'attribut
	 */
	int getYPosition();

	/**
	 * Retourne l'identifiant du noeud parent de cet attribut
	 * @return int
	 */
	int getRefId();

	/**
	 * Change la valeur du noeud referent.
	 * @param ref Le nouveau noeud referent
	 */
	void setRefId(int ref);

	/**
	 * Cette methode modifie la valeur de la premiere ligne de l'attribut.
	 * @param value Valeur de l'attribut
	 */
	void setValue(String value);

	/**
	 * Retourne la valeur de la premiere ligne de l'attribut.
	 * @return String
	 */
	String getValue();

	Object clone() throws CloneNotSupportedException;
}
