package fr.lip6.move.coloane.core.ui.figures.nodes;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;


/**
 * A figure that changes its aspect depending on the value of the visibility attribute.
 * @author Yann based on RectangleNode
 *
 */
public class TPNTransition extends AbstractNodeFigure {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds().getResized(-1, -1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = getBounds();

		boolean isPublic = getModel().getAttribute("visibility").getValue().equals("public");
		
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		graphics.drawRectangle(x, y, w, h);
		
		// double the line for public transition : gives a "bold" effect.
		if (isPublic) {
			int ix = x + 2;
			int iy = y + 2; 
			int iw = Math.max(1,w - 4); 
			int ih = Math.max(1,h - 4); 
			graphics.drawRectangle(ix, iy, iw, ih);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}
	
}
