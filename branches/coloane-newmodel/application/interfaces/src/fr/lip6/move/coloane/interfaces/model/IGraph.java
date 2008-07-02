package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;

import java.util.Collection;

public interface IGraph extends IElement {

	/** ID de propriete lorsqu'un noeud est ajoute au modele */
	String NODE_ADDED_PROP = "Model.AddingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un noeud est supprime du modele */
	String NODE_REMOVED_PROP = "Model.RemovingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un attribut doit etre ajoute au modele */
	String ATTRIBUTE_ADDED_PROP = "Model.AddingAttribute"; //$NON-NLS-1$

	/**
	 * Création d'un noeud attaché à ce graphe.
	 * @param nodeFormalismName type du noeud à créer.
	 * @return le noeud créé.
	 */
	INode createNode(String nodeFormalismName);

	/**
	 * Suppression d'un noeud
	 * @param node
	 */
	void deleteNode(INode node);

	/**
	 * Suppression d'un noeud
	 * @param id identifiant du noeud à supprimer
	 */
	void deleteNode(int id);

	/**
	 * @param id
	 * @return le noeud demandé ou null;
	 */
	INode getNode(int id);

	/**
	 * @return la liste des noeuds.
	 */
	Collection<INode> getNodes();

	/**
	 * Ajoute un noeud à ce graphe.
	 * @param arc
	 */
	void addNode(INode node);

	/**
	 * Création d'un arc attaché aux noeuds source et target.
	 * @param arcFormalismName type d'arc à créer.
	 * @param source
	 * @param target
	 * @return l'arc créé.
	 */
	IArc createArc(String arcFormalismName, INode source, INode target);

	/**
	 * Suppression d'un arc
	 * @param arc
	 */
	void deleteArc(IArc arc);

	/**
	 * Suppression d'un arc
	 * @param id identifiant de l'arc à supprimer
	 */
	void deleteArc(int id);

	/**
	 * @param id
	 * @return l'arc demandé ou null.
	 */
	IArc getArc(int id);

	/**
	 * @return la liste des arcs.
	 */
	Collection<IArc> getArcs();

	/**
	 * Ajouter un arc qui aurait été enlevé.
	 * @param arc
	 */
	void addArc(IArc arc);

	/**
	 * @return le formalisme associé à ce graphe.
	 */
	Formalism getFormalism();

	/**
	 * Modifie la date du modele (necessaire pour synchronisation avec FK)
	 * Indique si l'envoi d'un message a FK est necessaire
	 * @return boolean Indique si un message doit etre envoye a FK en donnant une datee
	 */
	int modifyDate();

	/**
	 * Retourne la date associee au modele
	 * @return int
	 */
	int getDate();

	/**
	 * Indicateur de fraicheur du modele
	 * @return boolean
	 */
	boolean isDirty();

	/**
	 * Permet de rendre obsolete (ou a jour) le modele (pour demande une maj ou signifier une maj)
	 * @param dirty (true = necessite de mise a jour)
	 */
	void setDirty(boolean dirty);
}
