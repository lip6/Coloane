package fr.lip6.move.coloane.interfaces.models;

import java.util.Collection;


import fr.lip6.move.coloane.models.Arc;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

/**
 * Interface pour les arcs du modele.<br>
 * Il n'y a pas de lien explicite entre un arc et le model mais via les noeuds.
 * <br>
 * Un arc peut etre l'arc sortant ou l'arc entrant d'un noeud <br>
 * Cette interface fournie des fonctionalites suivantes:
 * <ul>
 * 	<li>L'arbe de menu contexte
 * 	<li>Informations graphiques (la figure a afficher, la hauteur, largeur ...)
 * 	<li>Methodes de gestion ses connexions
 * </ul>
 * <p>
 * La classe qui implemente cette interface doit : heriter de la classe AbstractModelElement 
 * pour avoir des fonctionalites de propriete
 * </p>
 * 
 * @see AbstractModelElement
 * 
 */
public interface IArc {
    
    /** ID pour la propriete lorsqu'un changement de la valeur */
    public static final String VALUE_PROP = "Arc.ValueUpdate";
    
    /** Type d'arc : Normal */
    public static final String ARC_TYPE_NORMAL = "Normal";
    /** Type d'arc : Inhibiteur */
    public static final String ARC_TPYE_INHIBITOR = "Inhibitor";
    
    
    /**
     * Retourne l'arbe de menu contextuel pour l'arc
     * @return Collection
     */
    public Collection getContextMenus();  
   
    
    /**
     * Retourne le noeud qui est son point de source
     * @return NodeImplAdapter
     * @see INode
     */
    public NodeImplAdapter getSource();
    
    /**
     * Retourner le noeud qui est son point de cible
     * @return NodeImplAdapter
     * @see INode
     */
    public NodeImplAdapter getTarget();
    
    /**
     * Reconnecter cet arc. <br>
     * L'arc se reconnectera au noeud auquel il etait connecte avant
     */
    public void reconnect();
    
    /**
     * Reconnecter aux differents noeuds source et cible. 
     * L'arc se deconnecte de ses attaches actuelles et se connecte aux nouvelles 
     * @param source Noeud source
     * @param target Noeud cible
     */
    public void reconnect(NodeImplAdapter source, NodeImplAdapter target);
    
    /**
     * Deconnecter des noeuds actuels
     */
    public void disconnect();
    
    /**
     * Retourne un arc generique
     * @return Arc
     * @see Arc
     */
    public Arc getGenericArc();
    
}
