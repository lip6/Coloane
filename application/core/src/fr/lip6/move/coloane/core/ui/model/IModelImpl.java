package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.interfaces.model.IModel;

import java.util.List;

/**
 * Interface generale du modele La classe qui implemente cette interface doit
 * heriter de la classe AbstractModelElement pour avoir des fonctionalites de
 * proprietes
 */
public interface IModelImpl extends IElement {

	/** ID de propriete lorsqu'un noeud est ajoute au modele */
	String NODE_ADDED_PROP = "Model.AddingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un noeud est supprime du modele */
	String NODE_REMOVED_PROP = "Model.RemovingNode"; //$NON-NLS-1$

	/** ID de propriete lorsqu'un attribut doit etre ajoute au modele */
	String ATTRIBUTE_ADDED_PROP = "Model.AddingAttribute"; //$NON-NLS-1$

	/**
	 * Ajout d'un noeud au modele
	 * Cette methode est appelee par la vue ou le controleur.
	 * L'objectif est donc de mettre a jour le modele generique
	 * Leve un evenement CHILD_ADDED_PROP
	 * @param child Le noeud fils qu'il faut ajouter au modele augmente et au modele generique
	 * @throws BuildException
	 */
	void addNode(INodeImpl child) throws BuildException;

	/**
	 * Suppression d'un noeud
	 * Cette methode s'occupe de supprimer le noeud du modele generique
	 * Bien sur, cette methode supprime le noeud augmente du modele augmente (liste des enfants)
	 * Cette methode leve l'evenement CHILD_REMOVED_PROP
	 *
	 * @param child Le noeud fils qu'il faut supprimer du modele augmente et du modele generique
	 * @throws BuildException
	 */
	void removeNode(INodeImpl child) throws BuildException;

	/**
	 * Ajout d'un arc au modele
	 * @param child L'arc adapte qu'il faut ajoute au modele generique
	 */
	void addArc(IArcImpl child) throws BuildException;

	/**
	 * Retrait d'un arc au modele
	 * @param child L'arc adapte qu'il faut supprimer du modele generique
	 */
	void removeArc(IArcImpl child) throws BuildException;

	/**
	 * Retourne la liste des noeuds et arcs du modele.<br>
	 * Il s'agit des elements qui peuvent etre mis en valeur.
	 * @return Liste de tous les objets pouvant etre mis en valeur
	 */
	List<IElement> getModelObjects();

	/**
	 * Retourne l'objet du modele designe par son identifiant
	 * @param id L'identifiant de l'objet a retourner
	 * @return L'objet sous forme de IElement
	 */
	IElement getModelObject(int id);

	/**
	 * Retourne le modele generique
	 * @return Model Le mdoele generique
	 * @see fr.lip6.move.coloane.interfaces.model.IModel
	 */
	IModel getGenericModel();

	/**
	 * Retourne le formalisme associe au modele
	 * @return Formalism
	 * @see fr.lip6.move.coloane.core.motor.formalism.Formalism
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
	 * Change la valeur de la propriete
	 * @param id Objet dont il faut modifier la valeur
	 * @param value Nouvelle valeur pour l'objet
	 */
	void setPropertyValue(Object id, Object value);

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

	/**
	 * Reinitialise l'aspect des noeuds
	 */
	void switchoffNodes();

	/**
	 * Methode invoquee pour avertir le modele qu'un attribut doit etre ajoute ou supprime de l'affichage
	 * Cette methode doit lever l'evenement ATTRIBUTE_ADDED_PROP qui provoque :
	 * <ul>
	 *   <li>une capture des enfants du modele</li>
	 *   <li>un rafraichissement du modele</li>
	 * </ul>
	 */
	void announceAttribute();
}
