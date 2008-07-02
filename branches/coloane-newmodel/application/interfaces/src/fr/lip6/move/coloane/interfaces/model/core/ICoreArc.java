package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

public interface ICoreArc extends ICoreElement, IArc {

	/** ID pour la propriete lors d'un changement des arcs entants */
	String INFLEXPOINT_PROP = "Arc.InflexPoint"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String SELECT_PROP = "Arc.Select"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String UNSELECT_PROP = "Arc.Unselect"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SPECIAL_PROP = "Arc.SpecialUpdate"; //$NON-NLS-1$

	/** ID pour le changement de couleur */
	String COLOR_PROP = "Arc.Color"; //$NON-NLS-1$

	/**
	 * @return source de l'arc.
	 */
	INode getSource();

	/**
	 * @return cible de l'arc.
	 */
	INode getTarget();

	/**
	 * @return le FormalismElement décrivant cet arc.
	 */
	Arc getArcFormalism();

	/**
	 * @return les informations graphique liée à cet arc.
	 */
	IArcGraphicInfo getGraphicInfo();

	/**
	 * Ajoute un point d'inflexion à l'index spécifié.
	 * @param p coordonnées du point d'inflexion.
	 * @param index index du point d'inflexion.
	 */
	void addInflexPoint(Point p, int index);

	/**
	 * Ajoute un point d'inflexion.
	 * @param p coordonnées du point d'inflexion.
	 */
	void addInflexPoint(Point p);

	/**
	 * Suppression du point d'inflexion situé à l'index spécifié.
	 * @param index index du point d'inflexion.
	 */
	void removeInflexPoint(int index);

	/**
	 * Modification des coordonnées d'un point d'inflexion.
	 * @param index index du point d'inflexion.
	 * @param p nouvelles coordonnées.
	 */
	void modifyInflexPoint(int index, Point p);

	/**
	 * Déplace tous les points d'inflexions.
	 * @param dx
	 * @param dy
	 */
	void modifyInflexPoints(int dx, int dy);

	/**
	 * @param index
	 * @return Le point d'inflexion situé à l'index spécifié.
	 */
	AbsoluteBendpoint getInflexPoint(int index);

	/**
	 * @return Une liste nom modifiable de tous les points d'inflexions.
	 */
	List<AbsoluteBendpoint> getInflexPoints();

	/**
	 * Reconnecte cet arc à deux nouveaux noeuds.
	 * @param newSource
	 * @param newTarget
	 */
	void reconnect(INode newSource, INode newTarget);

	/**
	 * Mettre a jour la position des attributs de l'arc en fonction de la
	 * position des noeuds source et cible.
	 */
	void updateAttributesPosition();
}
