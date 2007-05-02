package fr.lip6.move.coloane.ui.model;

import java.util.Collection;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.motor.formalism.Formalism;

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
public interface IArcImpl {
    
    /** ID pour la propriete lorsqu'un changement de la valeur */
    public static final String VALUE_PROP = "Arc.ValueUpdate";
    
    /** Type d'arc : Normal */
    //public static final String ARC_TYPE_NORMAL = "Normal";
    /** Type d'arc : Inhibiteur */
    //public static final String ARC_TPYE_INHIBITOR = "Inhibitor";
    
    
	/**
	 * Retourne l'attribut ContextMenus
	 * @return Collection
	 */
	public Collection getContextMenus();

	/**
	 * Retourne le noeud source de l'arc
	 * @return NodeImplAdapter noeud source
	 */
	public INodeImpl getSource();

	/**
	 * Retourne le noeud cible de l'arc
	 * @return target noeud cible
	 */
	public INodeImpl getTarget();

    /**
     * Recupere l'ID du noeud generique
     * Evite les appels au noeud generique
     * @return ID
     */
    public int getId();
	
	/**
	 * Reconnecter
	 */
	public void reconnect() throws BuildException;

	/**
	 * Reconnecter
	 * @param source
	 * @param target
	 */
	public void reconnect(INodeImpl source, INodeImpl target) throws BuildException;
	
	/**
	 * Deconnecter un arc
	 */
	public void disconnect();

	/**
	 * Retourne l'arc generique associe a cet arc augemente
	 * @return Arc
	 * @see fr.lip6.move.coloane.interfaces.model.Arc
	 */
	public IArc getGenericArc();
	
	/**
	 * Retourne le formalisme associe a l'arc augmente
	 * @return Formalism
	 */
	public Formalism getFormalism();

	/**
	 * Retourne le modele generique
	 * @return IModelImpl
	 * @see IModelImpl
	 */
	public IModelImpl getModelAdapter();

	/** 
	 * Associe le modele a l'arc generique
	 * @param modelAdapter
	 */
	public void setModelAdapter(IModelImpl modelAdapter);

	/** 
	 * Liste des proprietes a afficher dans la fenetre de PropertyView
	 * @return IPropertyDescriptor[]
	 */
	public IPropertyDescriptor[] getPropertyDescriptors();

	/**
	 * Methode d'acces a la valeur de l'arc generique
	 * @return String
	 */
	public String getArcValue();

	/**
	 * Leve un evenement lors de la modification d'un propriete d'un arc.
	 * Cette methode est appelle par AbstractModelElement si l'evenement correspond bien
	 * @param oldValue L'ancienne valeur de la propriete
	 * @param newValue La nouvelle valeur
	 */
	public void throwEventProperty(String oldValue, String newValue);

	/**
	 * Actions entreprises suite ˆ la modification d'un parametres dans la fenetre Properties
	 * @param id L'objet concerne
	 * @param value La nouvelle valeur
	 */
	public void setPropertyValue(Object id, Object value);
    
}
