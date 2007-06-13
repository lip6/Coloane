package fr.lip6.move.coloane.ui.model;

import java.util.Collection;
import java.util.List;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.motor.formalism.ElementBase;

/**
 * Interface pour les noeuds du modele.<br>
 * Cette interface fournie des methodes principales pour un noeud du modele, y compris:
 * <ul>
 * 	<li>Reference du modele qui lui contient
 * 	<li>L'arbe de menu contexte
 * 	<li>Information graphique (la figure a afficher, la hauteur, largeur ...)
 * 	<li>Methodes de gestion ses connexions
 * </ul>
 * <p>
 * La classe qui implemente cette interface doit heriter de la classe AbstractModelElement pour avoir des fonctionalites de proprietes
 * </p> 
 */

public interface INodeImpl {

	/** ID pour la propriete lors d'un changement des arcs sortants */
	public static final String SOURCE_ARCS_PROP = "Node.OutputArc";

	/** ID pour la propriete lors d'un changement des arcs entants */
	public static final String TARGET_ARCS_PROP = "Node.InputArc";

	/** ID pour la propriete lorsqu'un changement de la position */
	public static final String LOCATION_PROP = "Node.Location";
	
	/** ID pour la propriete lorsqu'un changement de la valeur */
    public static final String VALUE_PROP = "Node.ValueUpdate";
	
    /** ID pour la propriete lorsque le noeud est selectionne */
    public static final String SELECT_PROP = "Node.SelectUpdate";
    
    /** ID pour la propriete lorsque le noeud est deselectionne */
    public static final String UNSELECT_PROP = "Node.UnSelectUpdate";
    
	/**
	 * Ajouter un arc entrant. 
	 * L'arc doit etre rajoute dans la liste des arcs entrants du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs entrants du noeud generique
	 * @param arc L'arc a ajouter
	 * @throws BuildException 
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl
	 */
	public void addInputArc(IArcImpl arc) throws BuildException;
    
    /**
     * Ajouter un arc sortant.
     * L'arc doit etre rajoute dans la liste des arcs sortants du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs sortants du noeud generique
     * @param arc L'arc a ajouter
     * @see fr.lip6.move.coloane.ui.model.IArcImpl
     * @throws BuildException
     */
	public void addOutputArc(IArcImpl arc) throws BuildException;
    
	/**
	 * Suppression d'un arc entrant ou sortant du noeud
	 * Cette methode est appelee par l'arc adapte lors de sa deconnexion
	 * @param arc L'arc a supprimer
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl
	 */
	public void removeArc(IArcImpl arc);
	
	/**
	 * Retourne la liste des arcs sortants du noeud
	 * @return List La liste
	 */
	public List getSourceArcs();

	/**
	 * Retourner la liste des arcs entrants du noeud
	 * @return List La liste
	 */
	public List getTargetArcs();
	
	/**
     * Recupere l'ID du noeud generique
     * Evite les appels direct au noeud generique
     * @return id L'identifiant du noeud
     */
    public int getId();
    
    /**
     * Retourne tous les attributs de l'objet pour qu'ils soient affiches
     * @return La liste des attributs
     */
    public List<IElement> getAttributes();
	
	/**
     * Associe le modele augmente au noeud
     * @param modelAdapter
     */
	public void setModelAdapter(IModelImpl modelAdapter);
	
	/**
	 * Retourne le modele augmente auquel appartient le noeud
	 * @return Le modele augmente
	 */
	public IModelImpl getModelAdapter();
    
	/**
	 * Retourne l'element de base du noeud
	 * @return L'element de base du noeud
	 */
	public ElementBase getElementBase();
	
	/**
	 * Retourne la valeur de l'attribut designe par le parametre
	 * @param attribute La chaine caracterisant l'attribut qu'on cible
	 * @return La valeur de l'attribut designe
	 */
	public String getNodeAttributeValue(String attribute);

    /**
     * Retourne le noeud generique
     * @return Node Le noeud generique
     * @see fr.lip6.move.coloane.interfaces.model.INode
     */
    public INode getGenericNode();
    
	/**
	 * Retourne les informations graphiques du noeud
	 * @return INodeGraphicInfo
	 * @see INodeGraphicInfo
	 */
	public INodeGraphicInfo getGraphicInfo();
    
    /**
	 * Retourner le menu contextuel associe au noeud
	 * @return Collection
	 */
	public Collection getContextMenus();

	public void setSpecial();
	
	public void unsetSpecial();
     
}
