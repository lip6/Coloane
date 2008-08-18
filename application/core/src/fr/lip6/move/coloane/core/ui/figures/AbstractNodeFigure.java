package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.swt.SWT;

/**
 * Classe abstraite commune à tous les noeuds.
 */
public abstract class AbstractNodeFigure extends Shape implements INodeFigure {

	/** {@inheritDoc} */
	@Override
	public final void paintFigure(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
	}

}
