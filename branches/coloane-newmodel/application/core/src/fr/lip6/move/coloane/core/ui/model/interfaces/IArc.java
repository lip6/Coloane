package fr.lip6.move.coloane.core.ui.model.interfaces;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;

public interface IArc extends IElement {

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

	INode getSource();
	INode getTarget();
	Arc getArcFormalism();
	IArcGraphicInfo getGraphicInfo();
}
