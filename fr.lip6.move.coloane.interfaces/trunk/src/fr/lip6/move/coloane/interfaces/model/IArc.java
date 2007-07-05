package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;

public interface IArc {

	/**
	 * Retourne le type de l'arc.
	 * @return String
	 */
	public String getArcType();


	/**
	 * Retourne l'identifiant unique de l'arc.
	 * @return int
	 */
	public int getId();
	
	/**
	 * Indique l'identifiant unique de l'arc.
	 * @param id L'identifiant a affecter a l'arc
	 */
	public void setId(int id);


	/**
	 * Permet de fixer le noeud d'entree de l'arc.
	 * @param node Noeud d'entree
	 * @see INode
	 */
	public void setStartingNode(INode node);

	/**
	 * Permet de fixer le noeud de sortie de l'arc.
	 * @param node Noeud de sortie
	 * @see INode
	 */
	public void setEndingNode(INode node);

	/**
	 * Retourne le noeud d'entree de l'arc.
	 * @return INode
	 * @see INode
	 */
	public INode getStartingNode();

	/**
	 * Retourne le noeud de sortie de l'arc.
	 * @return INode
	 * @see INode
	 */
	public INode getEndingNode();

	/**
	 * Ajout d'un attribut a l'arc
	 * @param attribute Attribut a ajouter
	 * @see IAttribute
	 */
	public void addAttribute(IAttribute attribute);

	/**
	 * Supprime un attribut
	 * @param attribute Attribut a supprimer
	 * @see IAttribute
	 */
	public void removeAttribute(IAttribute attribute);

	/**
	 * Spprime l'attribut en fonction de son index.
	 * @param index Index de l'attribut a supprimer
	 * @see IAttribute
	 */
	public void removeAttribute(int index);

	/**
	 * Cette methode retourne le nombre d'attributs de l'arc.
	 * @return int
	 */
	public int getListOfAttrSize();

	/**
	 * Retourne le vecteur contenant les attributs de l'arc.
	 * @return Vector La liste des attributs de l'arc
	 * @see IAttribute
	 */
	public Vector<IAttribute> getListOfAttr();

	/**
	 * Retourne le nieme attribut de l'arc 
	 * @param index Index de l'attribut recherche
	 * @return IAttribute
	 */
	public IAttribute getNthAttr(int index);

	/**
	 * Traduit un arc en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	public String[] translate();

}