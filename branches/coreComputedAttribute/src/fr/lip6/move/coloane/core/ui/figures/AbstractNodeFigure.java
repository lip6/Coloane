package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.swt.SWT;

/**
 * Basic abstract class used by all figure node classes.<br>
 * You can use the current state of the associated model element while drawing your figure.<br>
 * Please remember that the drawing of a figure must be quick and uses very low resources.
 * Don't make any strong computations to decide whether your figure should be red or blue...
 * 
 * @author Jean-Baptiste Voron
 */
public abstract class AbstractNodeFigure extends Shape implements INodeFigure {
	
	/**
	 * This element can be used to adapt the aspect of the figure according to 
	 * the current state of the associated model node element. Please be careful 
	 * when fetching some attributes from the model... Test the value of the property
	 * before doing any changes to the figure.  
	 */
	private INode modelElement;

	/** {@inheritDoc} */
	@Override
	public final void paintFigure(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final INode getModel() {
		return this.modelElement; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void setModelElement(INode modelElement) {
		this.modelElement = modelElement;
	}
}
