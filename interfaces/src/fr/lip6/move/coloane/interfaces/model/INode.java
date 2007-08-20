package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;

import java.util.Vector;

public interface INode {

	/**
	 * Retourne l'identifiant du noeud
	 * @return int
	 */
	int getId();

	/**
	 * Indique l'identifiant du noeud
	 * @param id L'identifiant a affecter au noeud
	 */
	void setId(int id);

	/**
	 * Retourne le type du noeud
	 * @return String
	 */
	String getNodeType();

	/**
	 * This method sets the x and y position of the node
	 *
	 * @param x	Position x
	 * @param y Position y
	 *
	 */
	void setPosition(int x, int y);

	/**
	 * Retourne la position x du noeud
	 * @return int
	 */
	int getXPosition();

	/**
	 * Retourne la position y noeud
	 * @return int
	 */
	int getYPosition();

	/**
	 * Ajout d'un arc entrant
	 * @param arc Arc a ajouter
	 * @see IArc
	 */
	void addInputArc(IArc arc);

	/**
	 * Supprime un arc entrant
	 * @param arc Arc a supprimer
	 * @see IArc
	 */
	void removeInputArc(IArc arc) throws ModelException;

	/**
	 * Supprime un arc entrant
	 * @param index	Index de l'arc a supprimer
	 * @see IArc
	 */
	void removeInputArc(int index) throws ModelException;

	/**
	 * Retourne le nombre d'arc entrants
	 * @return int
	 */
	int getListOfInputArcSize();

	/**
	 * Retourne l'arc designe par son index dans la liste des arcs entrants
	 * @param index Index de l'arc entrant a supprimer
	 * @return IArc
	 */
	IArc getNthInputArc(int index);

	/**
	 * Ajoute un arc sortant
	 * @param arc Arc a ajouter
	 * @see IArc
	 */
	void addOutputArc(IArc arc);

	/**
	 * Supprime un arc sortant
	 * @param arc Arc a supprimer
	 * @see IArc
	 */
	void removeOutputArc(IArc arc) throws ModelException;

	/**
	 * Supprime un arc sortant
	 * @param index Index de l'arc a supprimer
	 * @see IArc
	 */
	void removeOutputArc(int index) throws ModelException;

	/**
	 * Retourne le nombre d'arcs sortants
	 * @return int
	 */
	int getListOfOutputArcSize();

	/**
	 * Retourne l'arc designe par son idex dans la liste des arcs sortants
	 * @param index Index de l'arc dans la liste
	 * @return IArc L'arc designe
	 * @see IArc
	 */
	IArc getNthOutputArc(int index);

	/**
	 * Ajoute un attribut au noeud
	 * @param attribute Attribut a ajouter
	 * @see IAttribute
	 */
	void addAttribute(IAttribute attribute);

	/**
	 * Suppression d'un attribut
	 * @param attribute	Attribut
	 * @see IAttribute
	 */
	void removeAttribute(IAttribute attribute) throws ModelException;

	/**
	 * Suppression d'un attribut en fonction de son index dans la liste des attributs
	 * @param index Index de l'attribut dans la liste
	 */
	void removeAttribute(int index) throws ModelException;

	/**
	 * Retourne le nombre d'attributs du noeud
	 * @return int
	 */
	int getListOfAttrSize();

	/**
	 * Retourne la liste des attributs du noeud
	 * @return La liste des attributs du noeud
	 * @see IAttribute
	 */
	Vector<IAttribute> getListOfAttr();

	/**
	 * Retourne le nieme attribut de la liste
	 * @param index Index de l'attribut
	 * @return IAttribute
	 * @see IAttribute
	 */
	IAttribute getNthAttr(int index);

	/**
	 * Traduit un noeud en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	String[] translate();

	/*** /!\ *** AJOUTS POUR TESTS UNITAIRES*** /!\ ***/
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfInputArc()
	 */
	Vector<IArc> getListOfInputArc();

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfOuputArc()
	 */
	Vector<IArc> getListOfOutputArc();
	/*** FIN DES AJOUTS ***/
}
