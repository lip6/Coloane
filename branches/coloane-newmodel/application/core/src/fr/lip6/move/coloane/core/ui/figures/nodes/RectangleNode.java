package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;

public class RectangleNode extends AbstractNodeFigure implements RotatableDecoration {
	private final Transform transform = new Transform();

	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds());
	}

	@Override
	protected final void outlineShape(Graphics graphics) {
		System.err.println(graphics);
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		graphics.drawRectangle(x, y, w, h);

//		if (graphics instanceof SWTGraphics) {
//		graphics.pushState();
//		graphics.rotate(30);
//		graphics.drawLine(
//				getBounds().x,
//				getBounds().y + getBounds().height / 2,
//				getBounds().x + getBounds().width,
//				getBounds().y + getBounds().height / 2);
////		graphics.rotate(30);
//		graphics.popState();
//		}
	}

	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}

	public final void setReferencePoint(Point p) {
		Point pt = Point.SINGLETON;
		pt.setLocation(p);
		pt.negate().translate(getLocation());
		transform.setRotation(Math.atan2(pt.y, pt.x));
	}

}
