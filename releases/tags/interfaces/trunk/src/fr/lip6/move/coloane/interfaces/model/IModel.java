package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

public interface IModel {

	/**
	 * Construction du modele a partir d'un vecteur de commandes<br>
	 * Cette method est a implementee par les concepteurs d'API a partir du protocole qu'ils utilisent
	 * @param commands Un vecteur de commandes pour construire le modele
	 * @throws SyntaxErrorException
	 */
	public void buildModel(Vector<String> commands) throws SyntaxErrorException;
	/**
	 * Retourne le noeud d'identifiant uniqueId.
	 * @param uniqueId Identifiant unique du noeud
	 * @return INode
	 * @see INode
	 */
	public INode getANode(int uniqueId);

	/**
	 * Retourne l'arc d'identifiant uniqueId.
	 * @param uniqueId identifiant unique de l'arc
	 * @return IArc
	 * @see IArc
	 */
	public IArc getAnArc(int uniqueId);

	/**
	 * Ajoute un noeud au modele
	 * @param node Noeud a ajouter
	 * @see INode
	 */
	public void addNode(INode node) throws SyntaxErrorException;

	/**
	 * Ajoute un arc au modele
	 * @param arc Arc a ajouter
	 * @see Arc
	 */
	public void addArc(IArc arc) throws SyntaxErrorException;

	/**
	 * Ajoute un attribut au modele.
	 * @param attribute l'attribut a ajouter au modele
	 * @see IAttribute
	 */
	public void addAttribute(IAttribute attribute);

	/**
	 * Permet de supprimer un noeud dans le modele.
	 * @param node Noeud a supprimer du modele
	 * @see IArc
	 * @see INode
	 */
	public void removeNode(INode node);

	/**
	 * Supprime un arc du modele
	 * @param arc Arc a supprimer du modele
	 * @see IArc
	 */
	public void removeArc(IArc arc);

	/**
	 * Retourne le nombre d'attributs du modele.
	 * @return int
	 */
	public int getListOfAttrSize();

	/**
	 * Retourne le nombre d'arcs du modele
	 * @return int
	 */
	public int getListOfArcSize();

	/**
	 * Retourne le nombre de noeuds du modele
	 * @return int
	 */
	public int getListOfNodeSize();

	/**
	 * Retourne le nieme attribut du modele.
	 * @param index Indice de l'attribut
	 * @return IAttribute
	 * @see IAttribute
	 */
	public IAttribute getNthAttr(int index);

	/**
	 * Retourne le nieme arc du modele.
	 * @param index Indice de l'arc a retourner
	 * @return IArc L'arc demande
	 * @see IArc
	 */
	public IArc getNthArc(int index);

	/**
	 * Rend le nieme noeud du modele.
	 * @param index Indice du noeud
	 * @return INode
	 */
	public INode getNthNode(int index);

	/**
	 * Retourne la coordonnee X
	 * @return int
	 */
	public int getXPosition();

	/**
	 * Retourne la coordonnee Y
	 * @return int
	 */
	public int getYPosition();

	/**
	 * Cette methode permet de fixer les coordonnees spatiales de l'attribut
	 * @param x Coordonnee x
	 * @param y Coordonnee y
	 */
	public void setPosition(int x, int y);

	/**
	 * Indique le formalisme attache au modele
	 * @param formalism Le formalisme
	 */
	public void setFormalism(String formalism);

	/**
	 * Retourne le formalime attache au modele
	 * @return formalism
	 */
	public String getFormalism();
	
	/**
	 * Retourne la valeur maximale des identifiants des elements du modele
	 * @return L'identifiant maximal 
	 */
	public int getMaxId();
	
	/**
	 * Permet de modifier la valeur maximale des identifiants des elements du modele
	 * De cette facon la numerotation peut �tre modifiee.
	 * @param max L'identifiant maximum qu'on souhaite affecter
	 */	
	public int setMaxId(int max);
	
	
	/**
	 * Traduit un modele en chaines de caracteres du protocole considere.
	 * Cette methode doit etre implementee par les developpeurs d'API de communication en fonction de leur protocole d'echange
	 * @return un ensemble de commandes de construction
	 */
	public String[] translate();
	
	/**
	 * Renvoie la liste des identifiants pour les tests unitaires
	 * @return la liste des identifiants
	 */	
	public Vector<Integer> getListOfId();
}