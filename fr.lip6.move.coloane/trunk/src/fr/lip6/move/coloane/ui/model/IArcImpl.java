package fr.lip6.move.coloane.ui.model;

import java.util.Collection;
import java.util.List;

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
   
    
	/**
	 * Retourne l'attribut ContextMenus
	 * @return Collection
	 */
	public Collection getContextMenus();

	/**
	 * Retourne le noeud source de l'arc
	 * @return INodeImpl noeud source
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
	 * Retourne la liste des attributs attaches a cet objet
	 * @return La liste des attributs associes a cet objet
	 */
	public List<IElement> getAttributes(); 

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
	 * Methode d'acces a la valeur de l'arc generique
	 * @return String
	 */
	public String getArcValue();
	
	/**
	 * Methode d'acces a la valeur du label d'un arc generique
	 * @return String
	 */
	public String getArcLabel();  
	
	
	/**
	 * Retourne les informations graphiques liees a l'arc (notamment le point milieu)
	 * @return IGraphicInfo
	 */
	public IArcGraphicInfo getGraphicInfo();
	
	/**
	 * Demande la mise en valeur des attributs attaches a l'objet
	 * @param light Epaisseur de la mise en valeur (survol = light, selection = heavy)
	 * @param state Selection / Deselection
	 */
	public void setAttributesSelected(boolean state);
	
	/**
	 * Mettre a jour la position des attributs de l'arc en fonction de la position des noeuds source et cible
	 */
	public void updateAttributesPosition();
}
