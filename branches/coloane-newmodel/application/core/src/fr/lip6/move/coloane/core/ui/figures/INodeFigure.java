package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

public interface INodeFigure extends IFigure {
	/** L'epaisseur des lignes lors de la selection */
	int LINE_WIDTH = 3;

	/** Couleur du fond des noeud plein */
	Color FILLED_BACKGROUND_COLOR = ColorConstants.black;

	/**
	 * Modifie la figure lorsqu'elle est selectionee
	 * On definit ici le feedback visuel lors de la selection d'un objet Noeud
	 */
	void setSelect();

	/**
	 * Modifie la figure lorsqu'elle est mise en valeur par des retours de services
	 * On definit ici le feedback visuel lors de la selection d'un resultat qui met en valeur le noeud
	 */
	void setSelectSpecial();

	/**
	 * Mise en valeur du noeud (selection d'un attribut referent)
	 */
	void setHighlight();

	/**
	 * Modifie la figure lorsqu'elle est deselectionee
	 * Annulation du feedback visuel du a la selection d'un objet Noeud
	 */
	void setUnselect();

	/**
	 * Creation des ancres pour attacher les connexions
	 * @return ConnectionAnchor
	 */
	ConnectionAnchor getConnectionAnchor();
}
