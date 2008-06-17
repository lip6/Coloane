package fr.lip6.move.coloane.core.ui.model;

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
	String SELECT_PROP = "Arc.Select"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String UNSELECT_PROP = "Arc.Unselect"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SPECIAL_PROP = "Arc.SpecialUpdate"; //$NON-NLS-1$


	/**
	 * Retourne l'attribut ContextMenus
	 * @return Collection
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
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
	 * Retourne le modele generique
	 * @return IModelImpl
	 * @see IModelImpl
	 */
	IModelImpl getModelAdapter();

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
	 * Deplacement de tous les points d'inflexions
	 * @param dx
	 * @param dy
	 */
	void modifyInflexPoints(int dx, int dy);

	/**
	 * Retourne les informations graphiques liees a l'arc (notamment le point milieu)
	 * @return IGraphicInfo
	 */
	IArcGraphicInfo getGraphicInfo();

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
}
