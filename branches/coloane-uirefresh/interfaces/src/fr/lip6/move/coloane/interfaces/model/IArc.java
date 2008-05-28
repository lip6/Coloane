package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.objects.IInflexPoint;

import java.util.Vector;

public interface IArc extends Cloneable {

	/**
	 * Retourne le type de l'arc.
	 * @return String
	 */
	String getArcType();

	/**
	 * Indique le type de l'arc
	 * @param arcType Le type de l'arc
	 */
	void setArcType(String arcType);

	/**
	 * Retourne l'identifiant unique de l'arc.
	 * @return int
	 */
	int getId();

	/**
	 * Indique l'identifiant unique de l'arc.
	 * @param id L'identifiant a affecter a l'arc
	 */
	void setId(int id);


	/**
	 * Permet de fixer le noeud d'entree de l'arc.
	 * @param node Noeud d'entree
	 * @see INode
	 */
	void setStartingNode(INode node);

	/**
	 * Permet de fixer le noeud de sortie de l'arc.
	 * @param node Noeud de sortie
	 * @see INode
	 */
	void setEndingNode(INode node);

	/**
	 * Retourne le noeud d'entree de l'arc.
	 * @return INode
	 * @see INode
	 */
	INode getStartingNode();

	/**
	 * Retourne le noeud de sortie de l'arc.
	 * @return INode
	 * @see INode
	 */
	INode getEndingNode();

	/**
	 * Ajout d'un attribut a l'arc
	 * @param attribute Attribut a ajouter
	 * @see IAttribute
	 */
	void addAttribute(IAttribute attribute);

	/**
	 * Supprime un attribut
	 * @param attribute Attribut a supprimer
	 * @see IAttribute
	 */
	void removeAttribute(IAttribute attribute) throws ModelException;

	/**
	 * Spprime l'attribut en fonction de son index.
	 * @param index Index de l'attribut a supprimer
	 * @see IAttribute
	 */
	void removeAttribute(int index) throws ModelException;

	/**
	 * Cette methode retourne le nombre d'attributs de l'arc.
	 * @return int
	 */
	int getListOfAttrSize();

	/**
	 * Retourne le vecteur contenant les attributs de l'arc.
	 * @return Vector La liste des attributs de l'arc
	 * @see IAttribute
	 */
	Vector<IAttribute> getListOfAttr();

	/**
	 * Retourne le nieme attribut de l'arc
	 * @param index Index de l'attribut recherche
	 * @return IAttribute
	 */
	IAttribute getNthAttr(int index);

	/**
	 * Retourne le vecteur de position des points intermediaires
	 * @return Vector La liste des positions
	 */
	Vector<IInflexPoint> getListOfPI();

	/**
	 * Ajout d'un point intermediaire a l'arc
	 * @param x,y Position (x,y) a ajouter
	 * @see InflexPoint
	 */
	void addPI(int x, int y) throws ModelException;

	/**
	 * Ajout d'un point intermediaire a l'arc en precisant sa position dans la liste des points
	 * @param x,y Position (x,y) a ajouter
	 * @param index Position dans la liste des points d'inflexion
	 * @see InflexPoint
	 */
	void addPI(int x, int y, int index) throws ModelException;


	/**
	 * Supprime le point d'inflexion repere par les coordonnees (x,y)
	 * @param x,y Position(x,y) a supprimer
	 * @see InflexPoint
	 */
	void removePI(int x, int y) throws ModelException;

	/**
	 * Supprime le point d'inflexion donne par son indice dans la liste
	 * @param index L'indice du point d'inflexion a supprimer
	 * @throws ModelException
	 * @see InflexPoint
	 */
	void removePI(int index) throws ModelException;

	/**
	 * Modifie les coordonnees d'un point d'inflexion deja existant
	 * @param index L'indice du point d'inflexion dans la liste
	 * @param newX Nouvelle abscisse
	 * @param newY Nouvelle ordonnee
	 * @see InflexPoint
	 */
	void modifyPI(int index, int newX, int newY) throws ModelException;

	/**
	 * Retourne la nieme position intermediaire de l'arc
	 * @param index Index de la position recherche
	 * @return IPosition
	 */
	IInflexPoint getNthPI(int index);

	Object clone() throws CloneNotSupportedException;
}
