package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;

public interface INode {

	/**
	 * Retourne l'identifiant du noeud 
	 * @return int
	 */
	public int getId();

	/**
	 * Indique l'identifiant du noeud 
	 * @param id L'identifiant a affecter au noeud
	 */
	public void setId(int id);
	
	/**
	 * Retourne le type du noeud
	 * @return String
	 */
	public String getNodeType();

	/**
	 * This method sets the x and y position of the node
	 * 
	 * @param x	Position x
	 * @param y Position y
	 * 
	 */
	public void setPosition(int x, int y);

	/**
	 * Retourne la position x du noeud
	 * @return int
	 */
	public int getXPosition();

	/**
	 * Retourne la position y noeud
	 * @return int
	 */
	public int getYPosition();

	/**
	 * Ajout d'un arc entrant 
	 * @param arc Arc a ajouter
	 * @see IArc
	 */
	public void addInputArc(IArc arc);

	/**
	 * Supprime un arc entrant
	 * @param arc Arc a supprimer
	 * @see IArc
	 */
	public void removeInputArc(IArc arc);

	/**
	 * Supprime un arc entrant
	 * @param index	Index de l'arc a supprimer
	 * @see IArc
	 */
	public void removeInputArc(int index);

	/**
	 * Retourne le nombre d'arc entrants
	 * @return int
	 */
	public int getListOfInputArcSize();

	/**
	 * Retourne l'arc designe par son index dans la liste des arcs entrants
	 * @param index Index de l'arc entrant a supprimer
	 * @return IArc
	 */
	public IArc getNthInputArc(int index);

	/**
	 * Ajoute un arc sortant
	 * @param arc Arc a ajouter
	 * @see IArc
	 */
	public void addOutputArc(IArc arc);

	/**
	 * Supprime un arc sortant
	 * @param arc Arc a supprimer
	 * @see IArc
	 */
	public void removeOutputArc(IArc arc);

	/**
	 * Supprime un arc sortant
	 * @param index Index de l'arc a supprimer
	 * @see IArc
	 */
	public void removeOutputArc(int index);

	/**
	 * Retourne le nombre d'arcs sortants
	 * @return int
	 */
	public int getListOfOutputArcSize();

	/**
	 * Retourne l'arc designe par son idex dans la liste des arcs sortants
	 * @param index Index de l'arc dans la liste
	 * @return IArc L'arc designe
	 * @see IArc
	 */
	public IArc getNthOutputArc(int index);

	/**
	 * Ajoute un attribut au noeud 
	 * @param attribute Attribut a ajouter
	 * @see IAttribute
	 */
	public void addAttribute(IAttribute attribute);

	/**
	 * Suppression d'un attribut
	 * @param attribute	Attribut
	 * @see IAttribute
	 */
	public void removeAttribute(IAttribute attribute);

	/**
	 * Suppression d'un attribut en fonction de son index dans la liste des attributs
	 * @param index Index de l'attribut dans la liste
	 */
	public void removeAttribute(int index);

	/**
	 * Retourne le nombre d'attributs du noeud
	 * @return int
	 */
	public int getListOfAttrSize();

	/**
	 * Retourne la liste des attributs du noeud
	 * @return La liste des attributs du noeud
	 * @see IAttribute
	 */
	public Vector<IAttribute> getListOfAttr();

	/**
	 * Retourne le nieme attribut de la liste
	 * @param index Index de l'attribut
	 * @return IAttribute
	 * @see IAttribute
	 */
	public IAttribute getNthAttr(int index);

	/**
	 * Traduit un noeud en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	public String[] translate();

}