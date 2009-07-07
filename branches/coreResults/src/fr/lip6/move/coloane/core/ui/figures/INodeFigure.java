package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

/**
 * Interface représentant un noeuds dans coloane
 */
public interface INodeFigure extends IFigure {
	/** Couleur du fond des noeud plein */
	Color FILLED_BACKGROUND_COLOR = ColorConstants.black;

	/**
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 * {@inheritDoc}
	 */
	void setLineWidth(int w);

	/**
	 * Creation des ancres pour attacher les connexions
	 * @return ConnectionAnchor
	 */
	ConnectionAnchor getConnectionAnchor();
}
