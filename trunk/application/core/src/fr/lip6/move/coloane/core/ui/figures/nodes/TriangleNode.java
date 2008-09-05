package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Description d'une figure en forme de triangle.<br>
 * Cette figure peut être utilisée pour représenter un noeud sur un modèle.
 */
public class TriangleNode extends AbstractNodeFigure {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		PointList list = new PointList();

		// Contraintes de la figure imposée par le formalisme
		Rectangle r = getBounds();

//		int p1x = r.x + (r.width / 2);
//		int p1y = r.y + (lineWidth / 2);
//		list.addPoint(p1x, p1y);
		list.addPoint(r.getTop());

//		int p2x = r.x + (lineWidth / 2);
//		int p2y = r.y + r.height - Math.max(1, lineWidth);
//		list.addPoint(p2x, p2y);
		list.addPoint(r.getBottomLeft().getTranslated(0, -1));

//		int p3x = r.x + r.width - Math.max(1, lineWidth);
//		int p3y = r.y + r.height - Math.max(1, lineWidth);
//		list.addPoint(p3x, p3y);
		list.addPoint(r.getBottomRight().getTranslated(0, -1));

		//list.addPoint(p1x, p1y);

		graphics.drawPolygon(list);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new AbstractConnectionAnchor(this) {
			public Point getLocation(Point reference) {
				Rectangle r = getBounds();

				Point[] points = new Point[3];
				points[0] = r.getTop();
				points[1] = r.getBottomLeft().getTranslated(0, -1);
				points[2] = r.getBottomRight().getTranslated(0, -1);

				int distMin = Integer.MAX_VALUE;
				int indexMin = 0;
				for (int i = 0; i < points.length; i++) {
					int distTmp = points[i].getDistance2(reference);
					if (distTmp < distMin) {
						indexMin = i;
						distMin = distTmp;
					}
				}

				return points[indexMin];
			}
		};
	}
}
