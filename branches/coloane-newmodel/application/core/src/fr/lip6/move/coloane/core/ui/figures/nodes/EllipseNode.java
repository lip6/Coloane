package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EllipseNode extends AbstractNodeFigure {

	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillOval(getBounds());
	}

	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.width--;
		r.height--;
		r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);
		graphics.drawOval(r);
	}

	public final ConnectionAnchor getConnectionAnchor() {
		return new EllipseAnchor(this);
	}

}
