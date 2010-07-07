package fr.lip6.move.coloane.formalism.hack;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


/**
 * @author Mathieu Sassolas
 *
 */
public class StateNode extends AbstractNodeFigure implements INodeFigure {

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.core.ui.figures.INodeFigure#getConnectionAnchor()
	 */
	@Override
	public final ConnectionAnchor getConnectionAnchor() {
		//EllipseAnchor test = new EllipseAnchor(this);
		return new EllipseAnchor(this) {
			@Override
			public Point getLocation(Point reference) {
				Point defaultAnchor = super.getLocation(reference);
				Point currAnchor = defaultAnchor;
				Rectangle r = Rectangle.SINGLETON;
				r.setBounds(getBounds());
				PointList iForbidden = initTriangle();
				iForbidden.setPoint(iForbidden.getPoint(1).translate(0, 2), 1);
				iForbidden.setPoint(iForbidden.getPoint(2).translate(2, 0), 2);
				iForbidden.addPoint(iForbidden.getLastPoint().getCopy());
				iForbidden.setPoint(r.getTopLeft(), 2);
				if (isInitial() && iForbidden.polygonContainsPoint(defaultAnchor.x, defaultAnchor.y)) {
					currAnchor = bestAnchor(defaultAnchor, r, initTriangle().getPoint(0), initTriangle().getPoint(1), initTriangle().getPoint(2));
				}
				PointList fForbidden = new PointList();
				PointList fTri = finalTriangle();
				fForbidden.addPoint(fTri.getPoint(0));
				fForbidden.addPoint(fTri.getPoint(1).x, r.getBottom().y);
				fForbidden.addPoint(r.getLocation());
				fForbidden.addPoint(r.getRight().x, fTri.getPoint(2).y);
				if (isFinal() &&  fForbidden.polygonContainsPoint(defaultAnchor.x, defaultAnchor.y)) {
					currAnchor = bestAnchor(defaultAnchor, r, initTriangle().getPoint(0), initTriangle().getPoint(1), initTriangle().getPoint(2));
				}
				return currAnchor;
			}
		};
	}

	/** {@inheritDoc}
	 * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillOval(getBounds().getResized(-1, -1));
	}

	/** {@inheritDoc}
	 * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.width--;
		r.height--;
		r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);
		graphics.drawOval(r);

		Color oldbg = graphics.getBackgroundColor();
		Color noir = new Color(Display.getCurrent(), 0, 0, 0);

		if (isInitial()) {
			graphics.setBackgroundColor(noir);
			graphics.fillPolygon(this.initTriangle());
			graphics.setBackgroundColor(oldbg);
		}

		if (isFinal()) {
			graphics.setBackgroundColor(noir);
			graphics.fillPolygon(this.finalTriangle());
			graphics.setBackgroundColor(oldbg);
		}
	}
	
	/**
	 * @return If the state is initial.
	 */
	private boolean isInitial() {
		return Boolean.parseBoolean(getModel().getAttribute("Initial").getValue());
	}
	
	/**
	 * @return If the state is final
	 */
	private boolean isFinal() {
		return Boolean.parseBoolean(getModel().getAttribute("Final").getValue());
	}
	
	/**
	 * @return A triangle at the upper left corner of the figure, pointing to the center of the figure.
	 */
	private PointList initTriangle() {
		Rectangle bounds = Rectangle.SINGLETON;
		bounds.setBounds(getBounds());
		PointList triangle = new PointList();
		triangle.addPoint(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
		triangle.addPoint(bounds.x, bounds.y + bounds.height / 4);
		triangle.addPoint(bounds.x + bounds.width / 4, bounds.y);
		return triangle;
	}

	/**
	 * @return A triangle at the bottom right corner of the figure, pointing out of the figure.
	 */
	private PointList finalTriangle() {
		Rectangle bounds = Rectangle.SINGLETON;
		bounds.setBounds(getBounds());
		PointList triangle = new PointList();
		triangle.addPoint(bounds.x + bounds.width, bounds.y + bounds.height);
		triangle.addPoint(bounds.x + 3 * bounds.width / 4, bounds.y + bounds.height / 2);
		triangle.addPoint(bounds.x + bounds.width / 2, bounds.y + 3 * bounds.height / 4);
		return triangle;
	}
	
	/**
	 * ellipseLineintersect returns the list of intersections of an (orthogonal) ellipse and a line.
	 * The ellipse is specified by its center and semi-axis length.
	 * The computation is actually a degree-2 polynomial root search.
	 * @param center center of the ellipse
	 * @param alpha horizontal semi-axis length of the ellipse
	 * @param beta vertical semi-axis length of the ellipse
	 * @param point1 first point of the line
	 * @param point2 second point of the line
	 * @return the set of points both on the ellipse and on the line.
	 */
	private PointList ellipseLineIntersect(Point center, double alpha, double beta, Point point1, Point point2) {
		PointList inter = new PointList();
		double x0 = (double) center.x;
		double y0 = (double) center.y;
		double x1 = (double) point1.x;
		double y1 = (double) point1.y;
		double x2 = (double) point2.x;
		double y2 = (double) point2.y;
		double a, b, c, delta;
		a = 1 / (alpha * alpha) + ((y1 - y2) * (y1 - y2)) / (((x1 - x2) * (x1 - x2) * beta * beta));
		double b0 = (2 * (y2 * x1 - y1 * x2 - y0 * x1 + y0 * x2) * (y1 - y2)) / ((x1 - x2) * (x1 - x2) * beta * beta);
		double b1 = (2 * x0) / (alpha * alpha);
		b =  b0 - b1;
		double c1 = (x0 * x0) / (alpha * alpha);
		double c2 = (y2 * x1 - y1 * x2 - y0 * x1 + y0 * x2);
		double c3 = x1 - x2;
		c = c1 + (c2 * c2) / (c3 * c3 * beta * beta) - 1;
		double pente = (y1 - y2) / (x1 - x2);
		double init = (y2 * x1 - y1 * x2) / (x1 - x2);
		delta = b * b - 4 * a * c;
		if (delta == 0) {
			int solx = (int) (-b / (2 * a));
			int soly = (int) (((y2 - y1) * b) / ((x1 - x2) * 2 * a) + (y2 * x1 + y1 * x2) / (x1 - x2));
			inter.addPoint(solx, soly);
		}
		if (delta > 0) {
			double sol1x = (-b + Math.sqrt(delta)) / (2 * a);
			double sol1y = pente * sol1x + init;
			double sol2x = (-b - Math.sqrt(delta)) / (2 * a);
			double sol2y = pente * sol2x + init;
			inter.addPoint((int) sol1x, (int) sol1y);
			inter.addPoint((int) sol2x, (int) sol2y);
		}
		return inter;
	}
	
	/**
	 * @param defaultAnchor the anchor proposed by the system, clashing with a triangle
	 * @param ellipse the ellipse on which the anchor is to be found
	 * @param tip the tip of the triangle
	 * @param left the left corner of the triangle
	 * @param top the top corner of the triangle
	 * @return an anchor out of the triangle, but on the ellipse, as close as possible to the original anchor.
	 */
	private Point bestAnchor(Point defaultAnchor, Rectangle ellipse, Point tip, Point left, Point top) {
		Point currAnchor = defaultAnchor;
		double a = ((double) ellipse.width) / 2;
		double b = ((double) ellipse.height) / 2;
		PointList intersections = ellipseLineIntersect(ellipse.getLocation(), a, b, tip, left.translate(0, 2));
		intersections.addAll(ellipseLineIntersect(ellipse.getLocation(), a, b, tip, top.translate(2, 0)));
		int i = 0;
		double dist = -1;
		while (i < intersections.size()) {
			Point candidate = intersections.getPoint(i);
			if ((dist < 0) || dist > candidate.getDistance(defaultAnchor)) {
				dist = candidate.getDistance(defaultAnchor);
				currAnchor = candidate;
			}
			i++;
		}
		return currAnchor;
	}
}
