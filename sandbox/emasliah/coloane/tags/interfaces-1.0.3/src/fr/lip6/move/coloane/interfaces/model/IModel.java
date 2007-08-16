package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
//import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

import java.util.Vector;

public interface IModel {

	int INITIAL_X = 20;
	int INITIAL_Y = 20;

	/**
	 * Retourne le noeud d'identifiant uniqueId.
	 * @param uniqueId Identifiant unique du noeud
	 * @return INode
	 * @see INode
	 */
	INode getANode(int uniqueId);

	/**
	 * Retourne l'arc d'identifiant uniqueId.
	 * @param uniqueId identifiant unique de l'arc
	 * @return IArc
	 * @see IArc
	 */
	IArc getAnArc(int uniqueId);

	/**
	 * Ajoute un noeud au modele
	 * @param node Noeud a ajouter
	 * @see INode
	 */
	void addNode(INode node) throws ModelException;

	/**
	 * Ajoute un arc au modele
	 * @param arc Arc a ajouter
	 * @see Arc
	 */
	void addArc(IArc arc) throws ModelException;

	/**
	 * Ajoute un attribut au modele.
	 * @param attribute l'attribut a ajouter au modele
	 * @see IAttribute
	 */
	void addAttribute(IAttribute attribute);

	/**
	 * Permet de supprimer un noeud dans le modele.
	 * @param node Noeud a supprimer du modele
	 * @see IArc
	 * @see INode
	 */
	void removeNode(INode node) throws ModelException;

	/**
	 * Supprime un arc du modele
	 * @param arc Arc a supprimer du modele
	 * @see IArc
	 */
	void removeArc(IArc arc) throws ModelException;

	/**
	 * Retourne le nombre d'attributs du modele.
	 * @return int
	 */
	int getListOfAttrSize();

	/**
	 * Retourne le nombre d'arcs du modele
	 * @return int
	 */
	int getListOfArcSize();

	/**
	 * Retourne le nombre de noeuds du modele
	 * @return int
	 */
	int getListOfNodeSize();

	/**
	 * Retourne le nieme attribut du modele.
	 * @param index Indice de l'attribut
	 * @return IAttribute
	 * @see IAttribute
	 */
	IAttribute getNthAttr(int index);

	/**
	 * Retourne le nieme arc du modele.
	 * @param index Indice de l'arc a retourner
	 * @return IArc L'arc demande
	 * @see IArc
	 */
	IArc getNthArc(int index);

	/**
	 * Rend le nieme noeud du modele.
	 * @param index Indice du noeud
	 * @return INode
	 */
	INode getNthNode(int index);

	/**
	 * Retourne la coordonnee X
	 * @return int
	 */
	int getXPosition();

	/**
	 * Retourne la coordonnee Y
	 * @return int
	 */
	int getYPosition();

	/**
	 * Cette methode permet de fixer les coordonnees spatiales de l'attribut
	 * @param x Coordonnee x
	 * @param y Coordonnee y
	 */
	void setPosition(int x, int y);

	/**
	 * Indique le formalisme attache au modele
	 * @param formalism Le formalisme
	 */
	void setFormalism(String formalism);

	/**
	 * Retourne le formalime attache au modele
	 * @return formalism
	 */
	String getFormalism();

	/**
	 * Retourne la valeur maximale des identifiants des elements du modele
	 * @return L'identifiant maximal
	 */
	int getMaxId();

	/**
	 * Permet de modifier la valeur maximale des identifiants des elements du modele
	 * De cette facon la numerotation peut �tre modifiee.
	 * @param max L'identifiant maximum qu'on souhaite affecter
	 */
	int setMaxId(int max);


	/**
	 * Traduit un modele en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	String[] translate();

	/**
	 * Renvoie la liste des identifiants pour les tests unitaires
	 * @return la liste des identifiants
	 */
	Vector<Integer> getListOfId();
}
