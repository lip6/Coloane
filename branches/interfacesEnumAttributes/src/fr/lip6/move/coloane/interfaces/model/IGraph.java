package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.Collection;

/**
 * Définition d'un graphe.<br>
 * Cette définition comporte aussi tous les événements qui peuvent se produire sur un graphe
 */
public interface IGraph extends IElement {

	/** ID de propriete lorsqu'un noeud est ajoute au modele */
	String NODE_ADDED_PROP = "Model.AddingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un noeud est supprime du modele */
	String NODE_REMOVED_PROP = "Model.RemovingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un arc est ajoute au modele */
	String ARC_ADDED_PROP = "Model.AddingArc"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un arc est supprime du modele */
	String ARC_REMOVED_PROP = "Model.RemovingArc"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un attribut doit etre ajoute au modele */
	String ATTRIBUTE_ADDED_PROP = "Model.AddingAttribute"; //$NON-NLS-1$

	/**
	 * Création d'un noeud attaché à ce graphe.
	 * @param nodeFormalismName type du noeud à créer.
	 * @return le noeud créé.
	 * @throws ModelException si le nom du formalisme n'est pas correcte
	 */
	INode createNode(String nodeFormalismName) throws ModelException;

	/**
	 * Création d'un noeud attaché à ce graphe.
	 * @param nodeFormalismName type du noeud à créer.
	 * @param id L'identifiant du noeud
	 * @return le noeud créé.
	 * @throws ModelException si le nom du formalisme n'est pas correcte
	 */
	INode createNode(String nodeFormalismName, int id) throws ModelException;

	/**
	 * Suppression d'un noeud
	 * @param node Le noeud qui doit être supprimé
	 */
	void deleteNode(INode node);

	/**
	 * Suppression d'un noeud
	 * @param id identifiant du noeud à supprimer
	 */
	void deleteNode(int id);

	/**
	 * @param id L'identifiant du noeud recherché
	 * @return le noeud demandé ou null;
	 */
	INode getNode(int id);

	/**
	 * @return la liste des noeuds.
	 */
	Collection<INode> getNodes();

	/**
	 * Ajoute un noeud à ce graphe.
	 * @param node Le noeud à ajouter au graphe
	 */
	void addNode(INode node);

	/**
	 * Création d'un arc attaché aux noeuds source et target.
	 * @param arcFormalismName type d'arc à créer.
	 * @param source La source de l'arc
	 * @param target La cible de l'arc
	 * @return l'arc créé.
	 * @throws ModelException si un des parametres n'est pas correct, par exemple le formalisme n'existe pas
	 * ou un des noeuds source ou cible n'est pas valide.
	 */
	IArc createArc(String arcFormalismName, INode source, INode target) throws ModelException;

	/**
	 * Création d'un arc attaché aux noeuds source et target.
	 * @param arcFormalismName type d'arc à créer.
	 * @param source La source de l'arc
	 * @param target La cible de l'arc
	 * @param id L'identifiant de l'arc à créer
	 * @return l'arc créé.
	 * @throws ModelException si un des parametres n'est pas correct, par exemple le formalisme n'existe pas
	 * ou un des noeuds source ou cible n'est pas valide.
	 */
	IArc createArc(String arcFormalismName, INode source, INode target, int id) throws ModelException;

	/**
	 * Suppression d'un arc
	 * @param arc L'arc à supprimer
	 */
	void deleteArc(IArc arc);

	/**
	 * Suppression d'un arc
	 * @param id identifiant de l'arc à supprimer
	 */
	void deleteArc(int id);

	/**
	 * @param id L'identifiant de l'arc recherché
	 * @return l'arc demandé ou null.
	 */
	IArc getArc(int id);

	/**
	 * @return la liste des arcs.
	 */
	Collection<IArc> getArcs();

	/**
	 * Ajouter un arc qui aurait été enlevé.
	 * @param arc L'arc qui doit être ajouté
	 */
	void addArc(IArc arc);

	/**
	 * Récupérer un objet du graphe grâce à son identifiant
	 * @param id L'identifiant de l'objet recherché
	 * @return L'objet comme un {@link IElement}
	 */
	IElement getObject(int id);

	/**
	 * Supprime un objet identifié par son identifiant<br>
	 * Si aucun objet existe... Rien ne se passe.
	 * @param id L'identifiant de l'objet à supprimer
	 * @throws ModelException si la suppression s'est mal passée
	 */
	void deleteObject(int id) throws ModelException;

	/**
	 * Ajoute tous les éléments du graphe.
	 * @param graph IGraph
	 */
	void addGraph(IGraph graph);

	/**
	 * @return le formalisme associé à ce graphe.
	 */
	IFormalism getFormalism();

	/**
	 * Retourne la date associee au modele
	 * @return int
	 */
	int getDate();
}
