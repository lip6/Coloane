package fr.lip6.move.coloane.core.ui.figures;

/*
 * Copyright (c) 2004-2005 Massachusetts Institute of Technology. This code was
 * developed as part of the Haystack (http://haystack.lcs.mit.edu/) research
 * project at MIT. Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/*
 * Created on Oct 27, 2004
 */

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * Renders a line that can be curved if necessary<br>
 * The cruve is compute thanks to a bezier algorithm.
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public class RoundedPolyline extends Polyline {

	/** Is the arc curved ? */
	private boolean rounded = false;

	/** Width of the rounded corner */
	private int cornerLen = 64;

	/**
	 * Sets the length of the corner on each edge.
	 * @param len the dimensions of the corner
	 */
	public final void setCornerLength(int len) {
		cornerLen = len;
	}

	/**
	 * Set a flag to indicate wether the arc must be curved or not
	 * @param flag <code>true</code> for a curved arc
	 */
	public final void setCurved(boolean flag) {
		this.rounded = flag;
	}

	/**
	 * Scale the distance between the two points such that the distance is equal to the cornerLen
	 * @param pt1 point 1
	 * @param pt2 point 2
	 * @param twoCorners est ce qu'on a deux coins
	 * @return taille
	 */
	private Dimension getCornerDimension(Point pt1, Point pt2, boolean twoCorners) {
		double scale = ((double) cornerLen) / pt1.getDistance(pt2);
		if (twoCorners) {
			scale = Math.min(0.5, scale);
		} else {
			scale = Math.min(1, scale);
		}
		return pt2.getDifference(pt1).scale(scale);
	}

	/**
	 * @see Shape#outlineShape(Graphics)
	 * {@inheritDoc}
	 */
    @Override
	protected final void outlineShape(Graphics g) {

    	// If the arc must not be curved... Just render a polyline
    	if (!rounded) {
			super.outlineShape(g);
			return;
		}

    	// If not... Render a RoundedPolyline
    	PointList pointList = getPoints();

		int sz = pointList.size();
		Point prevPt = pointList.getPoint(0);
		Point prevPtBendBeg = null;
		Point prevPtBendEnd = null;
		for (int i = 1; i < sz; i++) {
			Point currPt = pointList.getPoint(i);

			boolean corner = (prevPtBendBeg != null);
			Dimension cornerDimension = getCornerDimension(prevPt, currPt, corner);

			// draw corner if there is a corner beginning
			if (corner) {
				prevPtBendEnd = prevPt.getTranslated(cornerDimension);
				PointList tmp = new PointList();
				tmp.addPoint(prevPtBendBeg);
				tmp.addPoint(prevPt);
				tmp.addPoint(prevPtBendEnd);
				if (cornerLen > 2) {
					drawBezier(g, prevPtBendBeg, prevPt, prevPtBendEnd, prevPt, 6.0 / cornerLen);
					// drawBezier(g, prevPtBendBeg, prevPt, prevPtBendEnd, 2.0 / cornerLen);
				} else {
					g.drawLine(prevPtBendBeg, prevPtBendEnd);
				}
				prevPt = prevPtBendEnd;
			}

			Point curPtLineEnd = currPt;	// default to currPt
			if (i < sz - 1) {
				curPtLineEnd = currPt.getTranslated(cornerDimension.negate());
			}

			if (i < sz - 1 && prevPtBendEnd != null && prevPt.getDistance(currPt) < cornerLen) {
				prevPtBendBeg = prevPtBendEnd;
			} else {
				g.drawLine(prevPt, curPtLineEnd);
				prevPtBendBeg = curPtLineEnd;
			}
			prevPt = currPt;
		}

	}

	/**
	 * Classe qui va calculer les coordonnées de chaque point de la courbe
	 */
	static class BezierDimension {
		private double a, b, c, d;
		/** based on: http://www.moshplant.com/direct-or/bezier/math.html<br>
		 * <br>
		 * based on the spec:<br>
		 * value(t) = a.t.t.t + b.t.t + c.t + d<br>
		 * p0 = d<br>
		 * p1 = p0+c/3<br>
		 * p2 = p1+(c+b)/3<br>
		 * p3 = p0+c+b+a
		 * @param p0 start point
		 * @param p1 start control point
		 * @param p2 end control point
		 * @param p3 end point
		 */
		public BezierDimension(double p0, double p1, double p2, double p3) {
			d = p0;
			c = 3 * (p1 - p0);
			b = 3 * (p2 - p1) - c;
			a = p3 - p0 - c - b;
		}

		/**
		 * @param t pas compris dans l'interval [0,1]
		 * @return la coordonnée calculée
		 */
		public int getValue(double t) {
			return (int) Math.round((a * t * t * t) + (b * t * t) + (c * t) + d);
		}
	};

	/**
	 * Dessine une spline en utilisant l'algo dispo sur ce site : http://www.moshplant.com/direct-or/bezier/math.html
	 *
	 * @param g Graphics qui permet de dessiner
	 * @param startPt point de départ
	 * @param startCtrlPt premier point de control
	 * @param endPt point d'arrivé
	 * @param endCtrlPt deuxième point de control
	 * @param step pas
	 */
	private static void drawBezier(Graphics g, Point startPt, Point startCtrlPt, Point endPt, Point endCtrlPt, double step) {
		BezierDimension x = new BezierDimension(startPt.x, startCtrlPt.x, endCtrlPt.x, endPt.x);
		BezierDimension y = new BezierDimension(startPt.y, startCtrlPt.y, endCtrlPt.y, endPt.y);
		double t = 0;
		Point midPt = startPt;
		while (t < 1) {
			Point nextPt = new Point();
			nextPt.setLocation(x.getValue(t), y.getValue(t));
			g.drawLine(midPt, nextPt);
			t += step;
			midPt = nextPt;
		}
		g.drawLine(midPt, endPt);
	}

	/**
	 * Dessine une courbe de bézier
	 * @param g Graphics qui permet de dessiner
	 * @param p0 point de départ
	 * @param p1 point de control
	 * @param p2 point d'arrivé
	 * @param step pas
	 */
	@SuppressWarnings("unused")
	private static void drawBezier(Graphics g, Point p0, Point p1, Point p2, double step) {
		PointList tmp = new PointList();
		tmp.addPoint(p0);
		tmp.addPoint(p1);
		tmp.addPoint(p2);
//		g.drawPolygon(tmp);
		double t = step;
		Point prev = p0;
		g.pushState();
		g.setForegroundColor(ColorConstants.blue);
		while (t < 1) {
			double td = t;
			double tdd = 1 - td;
			PrecisionPoint p = new PrecisionPoint();
			p.preciseX = (tdd * tdd * p0.x) + (2 * td * tdd * p1.x) + (td * td * p2.x);
			p.preciseY = (tdd * tdd * p0.y) + (2 * td * tdd * p1.y) + (td * td * p2.y);
			p.updateInts();
			g.drawLine(prev, p);
			t += step;
			prev = p;
		}
		g.drawLine(prev, p2);
		g.popState();
	}
}
