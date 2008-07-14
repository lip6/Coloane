package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class RoundedRectangleNode extends AbstractNodeFigure {
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds());
	}

	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);

		Rectangle fr = new Rectangle(x, y, w, h);
		graphics.drawRoundRectangle(fr, 4, 4);
	}

	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}
}
