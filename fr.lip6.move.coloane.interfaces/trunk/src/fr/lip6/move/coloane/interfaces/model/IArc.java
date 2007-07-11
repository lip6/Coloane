package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.objects.IPosition;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
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
	 * Retourne le vecteur de position des points intermediaires 
	 * @return Vector La liste des positions
	 */
	public Vector<IPosition> getListOfPI();
	
	/**
	 * Ajout d'un point intermediaire a l'arc
	 * @param x,y Position (x,y) a ajouter
	 * @see Position
	 */
	public void addPI(int x, int y) throws SyntaxErrorException;


	/**
	 * Supprime la position (x,y)
	 * @param x,y Position(x,y) a supprimer
	 * @see Position
	 */
	public void removePI(int x, int y) throws SyntaxErrorException;

	/**
	 * Retourne la nieme position intermediaire de l'arc 
	 * @param index Index de la position recherche
	 * @return IPosition
	 */
	public IPosition getNthPI(int index);

	
	/**
	 * Traduit un arc en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	public String[] translate();

}