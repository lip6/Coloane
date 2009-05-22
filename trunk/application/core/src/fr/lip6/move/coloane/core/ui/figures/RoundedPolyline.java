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
	private int cornerLen = 32;

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
	 * @param isExtremity <code>true</code> if p1 or p2 is an extremity of the arc.
	 * @return taille
	 */
	private Dimension getCornerDimension(Point pt1, Point pt2, boolean isExtremity) {
		double scale = ((double) cornerLen) / pt1.getDistance(pt2);
		if (!isExtremity) {
			scale = Math.min(0.5, scale);
		} else {
			scale = Math.min(1, scale);
		}
		return pt1.getDifference(pt2).scale(scale);
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

    	if (pointList.size() == 2) {
    		g.drawLine(pointList.getFirstPoint(), pointList.getLastPoint());
    	} else if (pointList.size() >= 2) {

    		/*
    		 * For each loop, a curve is draw between 3 points : p0, p1, p2
    		 * For reduce the curve effect, we calcul two internediate point :
    		 * startCurve and endCurve (see method getCornerDimension).
    		 * Finally we draw the segment [p0, startCurve], the curve (startCurve, p1, endCurve)
    		 * and the segment [endCurve, p2]
    		 */
    		Point startCurve, endCurve = null, prevEndCurve;
    		for (int i = 2; i < pointList.size(); i++) {
    			Point p0 = pointList.getPoint(i - 2);
    			Point p1 = pointList.getPoint(i - 1);
    			Point p2 = pointList.getPoint(i);

    			prevEndCurve = endCurve;

    			Dimension curveSize1 = getCornerDimension(p0, p1, (i == 2));
    			startCurve = p1.getTranslated(curveSize1);

    			Dimension curveSize2 = getCornerDimension(p2, p1, (i + 1 == pointList.size()));
    			endCurve = p1.getTranslated(curveSize2);

    			// Draw the curve, we choose the method according to the curve size.
    			if ((i == 2 && p0.getDifference(p1).equals(curveSize1))
    					|| (i + 1 == pointList.size() && p2.getDifference(p1).equals(curveSize2))
    					|| p0.getDifference(p1).scale(0.5).equals(curveSize1)
    					|| p2.getDifference(p1).scale(0.5).equals(curveSize2)) {
//    				g.pushState();
//    				g.setForegroundColor(ColorConstants.blue);
    				drawBezier(g, startCurve, p1, endCurve, 6.0 / cornerLen);
//    				g.popState();
    			} else {
    				drawBezier(g, startCurve, p1, endCurve, p1, 6.0 / cornerLen);
    			}
//    			drawCurve(g, startCurve, p1, endCurve, 1);

    			// if first point
    			if (i == 2) {
    				g.drawLine(p0, startCurve);
    			}
    			// if last point
    			if (i + 1 == pointList.size()) {
    				g.drawLine(endCurve, p2);
    			}
    			// other point
    			if (i > 2 && i < pointList.size() && prevEndCurve != null) {
    				g.drawLine(prevEndCurve, startCurve);
    			}
    		}
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
//			g.drawPoint(midPt.x, midPt.y);
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
	private static void drawBezier(Graphics g, Point p0, Point p1, Point p2, double step) {
//		PointList tmp = new PointList();
//		tmp.addPoint(p0);
//		tmp.addPoint(p1);
//		tmp.addPoint(p2);
//		g.drawPolygon(tmp);
		double t = step;
		Point prev = p0;
		g.pushState();
//		g.setForegroundColor(ColorConstants.blue);
		while (t < 1) {
			double td = t;
			double tdd = 1 - td;
			PrecisionPoint p = new PrecisionPoint();
			p.preciseX = (tdd * tdd * p0.x) + (2 * td * tdd * p1.x) + (td * td * p2.x);
			p.preciseY = (tdd * tdd * p0.y) + (2 * td * tdd * p1.y) + (td * td * p2.y);
			p.updateInts();
//			g.drawPoint(prev.x, prev.y);
			g.drawLine(prev, p);
			t += step;
			prev = p;
		}
		g.drawLine(prev, p2);
		g.popState();
	}

	/**
	 * @param pl point list
	 * @param level recursion level
	 * @return curve point list
	 */
	private static PointList calculCurve(PointList pl, int level) {
		if (level > 0 && pl.size() > 2) {
			PointList npl = new PointList();
			Point p0, p1, p2;
			Point mx4, p;

			npl.addPoint(pl.getFirstPoint());

			int i = 2;
			while (i < pl.size()) {
				p0 = pl.getPoint(i - 2);
				p1 = pl.getPoint(i - 1);
				p2 = pl.getPoint(i);
				mx4 = new Point();
				mx4.x = p0.x + 2 * p1.x + p2.x;
				mx4.y = p0.y + 2 * p1.y + p2.y;

				p = new Point();
				p.x = (mx4.x / 2 + 3 * p0.x + 3 * p1.x) / 8;
				p.y = (mx4.y / 2 + 3 * p0.y + 3 * p1.y) / 8;
				npl.addPoint(p);

				p = new Point();
				p.x = (((int) (3 * mx4.x) / 4) + p1.x) / 4;
				p.y = (((int) (3 * mx4.y) / 4) + p1.y) / 4;
				npl.addPoint(p);

				p = new Point();
				p.x = (mx4.x / 2 + 3 * p2.x + 3 * p1.x) / 8;
				p.y = (mx4.y / 2 + 3 * p2.y + 3 * p1.y) / 8;
				npl.addPoint(p);

				i++;
			}
			npl.addPoint(pl.getLastPoint());

			return calculCurve(npl, level - 1);
		}

		return pl;
	}

	/**
	 * Another method for draw a curve.
	 * @param g Graphics for drawing
	 * @param p0 start point
	 * @param p1 control point
	 * @param p2 end point
	 * @param level recursion level
	 */
	@SuppressWarnings("unused")
	private static void drawCurve(Graphics g, Point p0, Point p1, Point p2, int level) {
		PointList initList = new PointList();
		initList.addPoint(p0);
		initList.addPoint(p1);
		initList.addPoint(p2);
		PointList pl = calculCurve(initList, level);

//		System.err.println(pl.size());
		g.drawPolyline(pl);
//		for (int i = 0; i < pl.size(); i++) {
//			Point p = pl.getPoint(i);
//			g.drawPoint(p.x, p.y);
//			System.err.println(p);
//		}

//		PointList tmp = new PointList();
//		tmp.addPoint(p0);
//		tmp.addPoint(p1);
//		tmp.addPoint(p2);
//		g.drawPolygon(tmp);
	}
}
