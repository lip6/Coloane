package fr.lip6.move.coloane.ui.model;

import java.util.List;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.motor.formalism.Formalism;

/**
 * Interface generale du modele La classe qui implemente cette interface doit
 * heriter de la classe AbstractModelElement pour avoir des fonctionalites de
 * proprietes
 */
public interface IModelImpl {

	/** ID de propriete lorsqu'un noeud est ajoute au modele */
	public static final String NODE_ADDED_PROP = "Model.AddingNode";

  /** ID de propriete lorsqu'un noeud est supprime du modele */
  public static final String NODE_REMOVED_PROP = "Model.RemovingNode";
    
  /** ID de propriete lorsqu'un attribut doit etre ajoute au modele */
  public static final String ATTRIBUTE_ADDED_PROP = "Model.AddingAttribute";
  
  /**
	 * Ajout d'un noeud au modele
	 * Cette methode est appelee par la vue ou le controleur.
	 * L'objectif est donc de mettre a jour le modele generique
	 * Leve un evenement CHILD_ADDED_PROP
	 * @param child Le noeud fils qu'il faut ajouter au modele augmente et au modele generique
	 * @throws BuildException 
	 */
	public void addNode(INodeImpl child) throws BuildException;


	/**
	 * Suppression d'un noeud Il faut supprimer ce noeud du modele generique Il
	 * faut supprimer le noeud des enfants du modele Lever un evenement
	 * CHILD_REMOVED_PROP
	 * 
	 * @param child
	 *            Lenoeud fils qu'il faut supprimer du modele augmente et du
	 *            modele generique
	 * @throws BuildException
	 */
	public void removeNode(INodeImpl child) throws BuildException;

	/**
	 * Ajout d'un arc au modele
	 * 
	 * @param child
	 *            L'arc adapte qu'il faut ajoute au modele generique
	 */
	public void addArc(IArcImpl child) throws BuildException;

	/**
	 * Retrait d'un arc au modele
	 * 
	 * @param child
	 *            L'arc adapte qu'il faut supprimer du modele generique
	 */
	public void removeArc(IArcImpl child);

	/**
	 * Retourne la liste des INodeImpl du modele
	 * 
	 * @return List
	 */
	public List<IElement> getChildren();

	/**
	 * Retourne la liste des attributs du modele
	 * @return Collection
	 */
	public List<IElement> getAttributes();
	
	/**
	 * Retourne le modele generique
	 * 
	 * @return Model Le mdoele generique
	 * @see fr.lip6.move.coloane.interfaces.model.IModel
	 */
	public IModel getGenericModel();

	/**
	 * Retourne le formalisme associe au modele
	 * 
	 * @return Formalism
	 * @see fr.lip6.move.coloane.motor.formalism.Formalism
	 */
	public Formalism getFormalism();

	/**
	 * Modifie la date du modele (necessaire pour synchronisation avec FK)
	 * Indique si l'envoi d'un message a FK est necessaire
	 * 
	 * @return boolean Indique si un message doit etre envoye a FK en donnant
	 *         une datee
	 */
	public int modifyDate();

	/**
	 * Retourne la date associee au modele
	 * 
	 * @return int
	 */
	public int getDate();

	/**
	 * Change la valeur de la propriete
	 * 
	 * @param id
	 *            Objet dont il faut modifier la valeur
	 * @param value
	 *            Nouvelle valeur pour l'objet
	 */
	public void setPropertyValue(Object id, Object value);

	/**
	 * Indicateur de fraicheur du modele
	 * 
	 * @return boolean
	 */
	public boolean isDirty();

	/**
	 * Permet de rendre obsolete (ou a jour) le modele (pour demande une maj ou
	 * sinigifer une maj)
	 * 
	 * @param dirty
	 *            (true = necessite de mise a jour)
	 */
	public void setDirty(boolean dirty);

	/**
	 * Indique que le modele est en construction
	 */
	public void setBeginBuilding();

	/**
	 * Indique que le modele n'est pas (plus) en construction
	 */
	public void setEndBuilding();

	/*
	 * Met en valeur un noeud
	 */
	public void highlightNode(String idhighlight, String unhighlight);

	public void switchoffNodes();
    
    /**
     * Methode invoquee pour avertir le modele qu'un attribut doit etre ajoute ou supprime de l'affichage
     * Cette methode doit lever l'evenement ATTRIBUTE_ADDED_PROP qui provoque :
     * <ul>
     *   <li>une capture des enfants du modele</li>
     *   <li>un rafraichissement du modele</li>
     * </ul>
     */
    public void annouceAttribute();
}
