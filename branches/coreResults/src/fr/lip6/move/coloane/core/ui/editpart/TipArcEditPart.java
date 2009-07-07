package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

/**
 * Arc permettant de relier un tip avec sont IElement associé.
 */
public class TipArcEditPart extends AbstractConnectionEditPart {

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
	}

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		PolylineConnection arc = new PolylineConnection();
		arc.setLineStyle(Graphics.LINE_DOT);
		return arc;
	}

}
