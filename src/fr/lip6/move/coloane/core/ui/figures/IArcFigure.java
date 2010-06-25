package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

/**
 * Interface that describes an arc figure and available operations on it.
 */
public interface IArcFigure extends IFigure, Connection {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 */
	void setLineWidth(int w);
	
	/**
	 * @return the model arc element that corresponds to the figure
	 */
	IArc getModel();
	
	/**
	 * Set the model arc element associated to this figure
	 * @param modelElement the model arc element
	 * @see IArc
	 */
	void setModelElement(IArc modelElement);

}
