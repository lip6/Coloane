package fr.lip6.move.coloane.ui.model;

import java.util.Collection;
import java.util.List;

import fr.lip6.move.coloane.interfaces.model.INode;

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
	public void addInputArc(ArcImplAdapter arc);
    
    /**
     * Ajouter un arc sortant.
     * @param arc L'arc a ajouter
     * @see ArcImplAdpater
     */
	public void addOutputArc(ArcImplAdapter arc);
     

	/**
	 * Supprime un arc 
	 * @param arc Arc a suprimmer
	 */
	public void removeArc(ArcImplAdapter arc);

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
     * Retourne le noeud generique
     * @return Node
     * @see Node
     */
    public INode getGenericNode();
     
}
