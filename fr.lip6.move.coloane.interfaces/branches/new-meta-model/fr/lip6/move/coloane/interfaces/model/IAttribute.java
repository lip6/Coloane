package fr.lip6.move.coloane.interfaces.model;

public interface IAttribute {

	/**
	 * Retourne le nom de l'attribut.
	 * @return String
	 */
	public String getName();

	/**
	 * Cette methode permet de fixer les coordonnees spatiales de l'attribut
	 * @param x est la valeur de la coordonee x
	 * @param y est la valeur de la coordonee y
	 */
	public void setPosition(int x, int y);

	/**
	 * Retourne l'abscisse de l'attribut.
	 * @return int
	 */
	public int getXPosition();

	/**
	 * Retourne l'ordonnee de l'attribut.
	 * @return la coordonnee y de l'attribut
	 */
	public int getYPosition();

	/**
	 * Retourne l'identifiant du noeud parent de cet attribut 
	 * @return int
	 */
	public int getRefId();
	
	/**
	 * Change la valeur du noeud referent.
	 * @param ref Le nouveau noeud referent
	 */
	public void setRefId(int ref);

	/**
	 * Cette methode modifie la valeur de la premiere ligne de l'attribut.
	 * @param value Valeur de l'attribut
	 */
	public void setValue(String value);

	/**
	 * Modifie la valeur de la ligne line de l'attribut.
	 * @param value la valeur de l'attribut
	 * @param numLine la ligne de la valeur de l'attribut
	 * @throws Exception si ce numuro de ligne n'existe pas
	 */
	public void setValue(String value, int numLine) throws Exception;

	/**
	 * Augmente le nombre de lignes de la valeur de l'attribut.
	 * @param nb Nombre de lignes a rajouter a la valeur de l'attribut
	 * @throws Exception si le nombre a ajouter est negatif
	 */
	public void riseNbLine(int nb) throws Exception;

	/**
	 * Cette methode retire la ligne line de la valeur de l'attribut. 
	 * La derniere ligne ne peut pas etre retiree.
	 * @param line Ligne a retirer
	 */
	public void removeLine(int line);

	/**
	 * Retourne la valeur de la premiere ligne de l'attribut.
	 * @return String
	 */
	public String getValue();

	/**
	 * Retourne la valeur de la ligne line de l'attribut.
	 * @param line la ligne de la valeur de l'attribut (0 pour la 1er ligne)
	 * @return String
	 */
	public String getValue(int line);

	/**
	 * Retourne le nombre de lignes de l'attribut.
	 * @return int
	 */
	public int getSize();

	/**
	 * Traduit un attribut en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	public String[] translate();

}