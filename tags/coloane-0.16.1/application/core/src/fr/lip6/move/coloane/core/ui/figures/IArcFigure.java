package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

/**
 * Interface représentant un arc dans coloane
 */
public interface IArcFigure extends IFigure, Connection {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 */
	void setLineWidth(int w);
}
