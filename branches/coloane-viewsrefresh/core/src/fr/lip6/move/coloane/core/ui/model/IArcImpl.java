package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.interfaces.model.IArc;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;

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
public interface IArcImpl extends IElement {

	/** ID pour la propriete lors d'un changement des arcs entants */
	String INFLEXPOINT_PROP = "Arc.InflexPoint"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String SETSELECT_PROP = "Arc.Select"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String SETUNSELECT_PROP = "Arc.Unselect"; //$NON-NLS-1$

	/** ID pour le changement de couleur */
	String COLOR_PROP = "Arc.Color"; //$NON-NLS-1$

	/**
	 * Retourne l'attribut ContextMenus
	 * @return Collection
	 */
	@SuppressWarnings("unchecked")
	Collection getContextMenus();

	/**
	 * Retourne le noeud source de l'arc
	 * @return INodeImpl noeud source
	 */
	INodeImpl getSource();

	/**
	 * Retourne le noeud cible de l'arc
	 * @return target noeud cible
	 */
	INodeImpl getTarget();

	/**
	 * Retourne l'arc generique associe a cet arc augemente
	 * @return Arc
	 * @see fr.lip6.move.coloane.interfaces.model.Arc
	 */
	IArc getGenericArc();

	/**
	 * Retourne le formalisme associe a l'arc augmente
	 * @return Formalism
	 */
	Formalism getFormalism();

	/**
	 * Retourne le modele generique
	 * @return IModelImpl
	 * @see IModelImpl
	 */
	IModelImpl getModelAdapter();

	/**
	 * Associe le modele a l'arc generique
	 * @param modelAdapter
	 */
	void setModelAdapter(IModelImpl modelAdapter);

	/**
	 * Methode d'acces a la valeur de l'arc generique
	 * @return String
	 */
	String getArcValue();

	/**
	 * Methode d'acces a la valeur du label d'un arc generique
	 * @return String
	 */
	String getArcLabel();

	/**
	 * Retourne une liste des points d'inflexion de cet arc
	 * @return Liste de Bendpoint
	 * @see Bendpoint
	 */
	List<Bendpoint> getInflexPoints();

	/**
	 * Retourne la description du point choisi et designe par son index dans la liste
	 * @param index Position du point dans la liste des points d'inflexion
	 * @return Point geometrique
	 */
	Point getInflexPoint(int index);

	/**
	 * Ajoute un point d'inflexion a la liste deja existante
	 * @param p Le point contenant les coordonnees
	 * @param index L'index d'insertion dans la liste
	 */
	void addInflexPoint(Point p , int index);

	/**
	 * Suppression d'un point d'inflexion de la liste de l'arc
	 * @param index Indice du point d'inflexion dans la liste
	 */
	void removeInflexPoint(int index);

	/**
	 * Modification des coordonnees du point dans la liste de l'arc
	 * @param index Indice du point dans la liste
	 * @param p Coordonees pour verification
	 */
	void modifyInflexPoint(int index, Point p);

	/**
	 * Retourne les informations graphiques liees a l'arc (notamment le point milieu)
	 * @return IGraphicInfo
	 */
	IArcGraphicInfo getGraphicInfo();

	/**
	 * Retourne l'element de base de l'arc
	 * @return L'element de base de l'arc
	 */
	ElementFormalism getElementBase();

	/**
	 * Demande la mise en valeur des attributs attaches a l'objet
	 * @param light Epaisseur de la mise en valeur (survol = light, selection = heavy)
	 * @param state Selection / Deselection
	 */
	void setAttributesSelected(boolean state);

	/**
	 * Mettre a jour la position des attributs de l'arc en fonction de la position des noeuds source et cible
	 */
	void updateAttributesPosition();

	/**
	 * Reconnecte un arc existant a des noeuds differents
	 * @param newSource Nouvelle source
	 * @param newTarget Nouvelle cible
	 */
	void reconnect(INodeImpl newSource, INodeImpl newTarget);

	/**
	 * Permet de mettre en valeur l'arc
	 * @param state : L'etat de selection
	 */
	void setSelect(boolean state);
}
