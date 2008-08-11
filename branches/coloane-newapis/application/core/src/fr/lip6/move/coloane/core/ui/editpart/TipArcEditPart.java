package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.ui.figures.arcs.SimpleArc;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

public class TipArcEditPart extends AbstractConnectionEditPart {

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
	}

	@Override
	protected IFigure createFigure() {
		SimpleArc arc = new SimpleArc();
		arc.setLineStyle(Graphics.LINE_DASH);
		return arc;
	}

}
