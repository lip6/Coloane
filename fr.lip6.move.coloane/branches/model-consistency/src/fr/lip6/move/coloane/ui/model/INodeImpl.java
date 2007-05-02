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

	/** TODO: Verifier que c'est bien necessaire */
	/** ID pour la propriete lorsqu'un changement de taille */
	public static final String SIZE_PROP = "Node.Size";
	
	/** ID pour la propriete lorsqu'un changement de la valeur */
    public static final String VALUE_PROP = "Node.ValueUpdate";
	

	/**
	 * Retourner l'arbe de menu context
	 * @return Collection
	 */
	public Collection getContextMenus();

	/**
	 * Retourne les informations graphiques du noeud
	 * @return INodeGraphicInfo
	 * @see INodeGraphicInfo
	 */
	public INodeGraphicInfo getGraphicInfo();

	/**
	 * Ajouter un arc entrant. 
	 * @param arc L'arc a ajouter
	 * @see ArcImplAdapter
	 */
	public void addInputArc(IArcImpl arc) throws BuildException;
    
    /**
     * Ajouter un arc sortant.
     * @param arc L'arc a ajouter
     * @see ArcImplAdpater
     */
	public void addOutputArc(IArcImpl arc) throws BuildException;
    
	/**
	 * Suppression d'un arc entrant/sortant du noeud
	 * @param arcAdapter
	 */
	public void removeArc(ArcImplAdapter arcAdapter);
	
    /**
     * Recupere l'ID du noeud generique
     * Evite les appels au noeud generique
     * @return ID
     */
    public int getId();
	
	/**
	 * Retourne la liste des arcs sortants
	 * @return List
	 * TODO: Pourquoi List et pas Vector ?
	 */
	public List getSourceArcs();

	/**
	 * Retourner la liste des arcs entrants 
	 * @return List
	 * TODO: Pourquoi List et pas Vector ?
	 */
	public List getTargetArcs();
	
	/**
     * Associe le modele augmente au noeud
     * @param modelAdapter
     */
	public void setModelAdapter(IModelImpl modelAdapter);
	
	public IModelImpl getModelAdapter();
    
	public ElementBase getElementBase();
	
	public String getNodeAttributeValue(String attribute);

    /**
     * Retourne le noeud generique
     * @return Node
     * @see Node
     */
    public INode getGenericNode();
     
}
