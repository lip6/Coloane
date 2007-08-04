package fr.lip6.move.coloane.ui.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;

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

	/** ID pour la propriete lors d'un changement des arcs entants */
	public static final String INFLEXPOINT_PROP = "Arc.InflexPoint";


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
	 * Retourne une liste des points d'inflexion de cet arc
	 * @return Liste de Bendpoint
	 * @see Bendpoint
	 */
	public List<Bendpoint> getInflexPoints();

	/**
	 * Retourne la description du point choisi et designe par son index dans la liste
	 * @param index Position du point dans la liste des points d'inflexion
	 * @return Point geometrique
	 */
	public Point getInflexPoint(int index);

	/**
	 * Ajoute un point d'inflexion a la liste deja existante
	 * @param p Le point contenant les coordonnees
	 * @param index L'index d'insertion dans la liste
	 */
	public void addInflexPoint(Point p , int index);

	/**
	 * Suppression d'un point d'inflexion de la liste de l'arc
	 * @param index Indice du point d'inflexion dans la liste
	 */
	public void removeInflexPoint(int index);

	/**
	 * Modification des coordonnees du point dans la liste de l'arc
	 * @param index Indice du point dans la liste
	 * @param p Coordonees pour verification
	 */
	public void modifyInflexPoint(int index, Point p);

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
