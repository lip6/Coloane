package fr.lip6.move.coloane.core.ui.model.interfaces;

import java.util.List;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;

public interface INode extends IElement {

	/** ID pour la propriete lors d'un changement des arcs sortants */
	String SOURCE_ARCS_PROP = "Node.OutputArc"; //$NON-NLS-1$

	/** ID pour la propriete lors d'un changement des arcs entants */
	String TARGET_ARCS_PROP = "Node.InputArc"; //$NON-NLS-1$

	/** ID pour la propriete lorsqu'un changement de la position */
	String LOCATION_PROP = "Node.Location"; //$NON-NLS-1$

	/** ID pour la propriete lorsqu'un changement de la valeur */
	String VALUE_PROP = "Node.ValueUpdate"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SELECT_PROP = "Node.SelectUpdate"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est deselectionne */
	String UNSELECT_PROP = "Node.UnSelectUpdate"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SPECIAL_PROP = "Node.SpecialUpdate"; //$NON-NLS-1$

	/** ID pour la propriete lorsque le noeud est deselectionne */
	String UNSPECIAL_PROP = "Node.UnSpecialUpdate"; //$NON-NLS-1$

	/** ID pour la propriété lorque la couleur d'un noeud change */
	String BACKGROUND_COLOR_PROP = "Node.Color.Background"; //$NON-NLS-1$

	/** ID pour la propriété lorque la couleur d'un noeud change */
	String FOREGROUND_COLOR_PROP = "Node.Color.Foreground"; //$NON-NLS-1$

	/** ID pour la propriété de changement de zoom */
	String RESIZE_PROP = "Node.Zoom"; //$NON-NLS-1$

	/**
	 * @return le FormalismElement décrivant ce noeud.
	 */
	Node getNodeFormalism();

	/**
	 * @return les informations graphiques liées a l'arc.
	 */
	INodeGraphicInfo getGraphicInfo();

	/**
	 * Permet de parcourir la liste des arcs sortants de ce noeud.
	 * @return une liste <b>non modifiable</b>
	 */
	List<IArc> getSourceArcs();

	/**
	 * Permet de parcourir la liste des arcs entrants de ce noeud.
	 * @return une liste <b>non modifiable</b>
	 */
	List<IArc> getTargetArcs();
}
