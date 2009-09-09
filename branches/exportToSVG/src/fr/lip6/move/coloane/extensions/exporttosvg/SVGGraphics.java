package fr.lip6.move.coloane.extensions.exporttosvg;

import java.awt.Polygon;
import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SVGGraphics extends Graphics {
	private boolean curve = false;
	private final Map<java.awt.Color, Color> colors = new HashMap<java.awt.Color, Color>();

	/** Width of the rounded corner */
	private int cornerLen = 32;

	private SVGGraphics2D svgGenerator;

	public SVGGraphics() {
        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        svgGenerator = new SVGGraphics2D(document);
	}

	public void write(String filename) throws IOException {
		svgGenerator.stream(filename);
	}

	/**
	 * @return <code>true</code> if the polyline must be draw with curve, default set to <code>false</code>
	 */
	public boolean isCurve() {
		return curve;
	}

	/**
	 * @param curve set <code>true</code> if the polyline must be draw with curve
	 */
	public void setCurve(boolean curve) {
		this.curve = curve;
	}

	@Override
	public void clipRect(Rectangle r) {
		System.err.println("clipRect(" + r + ")");
	}

	@Override
	public void dispose() {
		svgGenerator.dispose();
	}

	@Override
	public void drawArc(int x, int y, int w, int h, int offset, int length) {
		System.err.println("drawArc");
		svgGenerator.drawArc(x, y, w, h, offset, length);
	}

	@Override
	public void drawFocus(int x, int y, int w, int h) {
		System.err.println("drawFocus");
	}

	@Override
	public void drawImage(Image srcImage, int x, int y) {
		System.err.println("drawImage");
	}

	@Override
	public void drawImage(Image srcImage, int x1, int y1, int w1, int h1,
			int x2, int y2, int w2, int h2) {
		System.err.println("drawImage");
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		System.err.println("drawLine");
		svgGenerator.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawOval(int x, int y, int w, int h) {
		System.err.println("drawOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		svgGenerator.drawOval(x, y, w, h);
	}

	@Override
	public void drawPolygon(PointList points) {
		System.err.print("drawPolygon(");
		Polygon poly = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			System.err.print(" [" + p.x + "," + p.y + "]");
			poly.addPoint(p.x, p.y);
		}
		System.err.println(")");
		svgGenerator.drawPolygon(poly);
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
	 * Draw a curved polyline, this method is call by drawPolyline if curved is set.
	 * @param points list of points
	 */
	private void drawCurvePolyline(PointList points) {
		System.err.print("drawCurvePolyline( ");
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			System.err.print(" [" + p.x + "," + p.y + "]");
		}
		System.err.println(")");

		if (points.size() <= 2) {
			svgGenerator.drawLine(points.getFirstPoint().x, points.getFirstPoint().y, points.getLastPoint().x, points.getLastPoint().y);
			return;
		}
		
		/*
		 * For each loop, a curve is draw between 3 points : p0, p1, p2
		 * For reduce the curve effect, we calcul two internediate point :
		 * startCurve and endCurve (see method getCornerDimension).
		 * Finally we draw the segment [p0, startCurve], the curve (startCurve, p1, endCurve)
		 * and the segment [endCurve, p2]
		 */
		Point startCurve, endCurve = null, prevEndCurve;
		for (int i = 2; i < points.size(); i++) {
			Point p0 = points.getPoint(i - 2);
			Point p1 = points.getPoint(i - 1);
			Point p2 = points.getPoint(i);

			prevEndCurve = endCurve;

			Dimension curveSize1 = getCornerDimension(p0, p1, (i == 2));
			startCurve = p1.getTranslated(curveSize1);

			Dimension curveSize2 = getCornerDimension(p2, p1, (i + 1 == points.size()));
			endCurve = p1.getTranslated(curveSize2);

			svgGenerator.draw(new QuadCurve2D.Float(startCurve.x, startCurve.y, p1.x, p1.y, endCurve.x, endCurve.y));

			// if first point
			if (i == 2) {
				drawLine(p0, startCurve);
			}
			// if last point
			if (i + 1 == points.size()) {
				drawLine(endCurve, p2);
			}
			// other point
			if (i > 2 && i < points.size() && prevEndCurve != null) {
				drawLine(prevEndCurve, startCurve);
			}
		}
	}
	
	@Override
	public void drawPolyline(PointList points) {
		if (curve) {
			drawCurvePolyline(points);
		} else {
			System.err.print("drawPolyline( ");
			Polygon poly = new Polygon();
			for (int i = 0; i < points.size(); i++) {
				Point p = points.getPoint(i);
				System.err.print(" [" + p.x + "," + p.y + "]");
				poly.addPoint(p.x, p.y);
			}
			System.err.println(")");
			svgGenerator.drawPolyline(poly.xpoints, poly.ypoints, poly.npoints);
		}
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		System.err.println("drawRectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
		svgGenerator.drawRect(x, y, width, height);
	}

	@Override
	public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		System.err.println("drawRoundRectangle");
		svgGenerator.drawRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
	}

	@Override
	public void drawString(String s, int x, int y) {
		System.err.println("drawString");
		svgGenerator.drawString(s, x, y);
	}

	@Override
	public void drawText(String s, int x, int y) {
		System.err.println("drawText(" + s + "," + x + "," + y + ")");
		Font font = JFaceResources.getDefaultFont();
		Dimension d = FigureUtilities.getTextExtents(s, font);
		svgGenerator.drawString(s, x, y + d.height);
	}

	@Override
	public void fillArc(int x, int y, int w, int h, int offset, int length) {
		System.err.println("fillArc");
	}

	@Override
	public void fillGradient(int x, int y, int w, int h, boolean vertical) {
		System.err.println("fillGradient");
	}

	@Override
	public void fillOval(int x, int y, int w, int h) {
		System.err.println("fillOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillOval(x, y, w, h);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillPolygon(PointList points) {
		System.err.print("fillPolygon(");
		Polygon poly = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			System.err.print(" [" + p.x + "," + p.y + "]");
			poly.addPoint(p.x, p.y);
		}
		System.err.println(")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillPolygon(poly);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		System.err.println("fillRectangle");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRect(x, y, width, height);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		System.err.println("fillRoundRectangle");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillString(String s, int x, int y) {
		System.err.println("fillString");
		svgGenerator.drawString(s, x, y);
	}

	@Override
	public void fillText(String s, int x, int y) {
		System.err.println("fillText");
		svgGenerator.drawString(s, x, y);
	}

	@Override
	public Color getBackgroundColor() {
		Color color = colors.get(svgGenerator.getColor());
		if (color == null) {
			return ColorConstants.white;
		}
		return color;
	}

	@Override
	public Rectangle getClip(Rectangle r) {
		System.err.println("getClip(" + r.getResized(1, 1) + ")");
		return r.getResized(1, 1);
	}

	@Override
	public Font getFont() {
		return null;
	}

	@Override
	public FontMetrics getFontMetrics() {
		return null;
	}

	@Override
	public Color getForegroundColor() {
		Color color = colors.get(svgGenerator.getColor());
		if (color == null) {
			return ColorConstants.black;
		}
		return color;
	}

	@Override
	public int getLineStyle() {
		return 0;
	}

	@Override
	public int getLineWidth() {
		System.err.println("getLineWidth()");
		return 0;
	}

	@Override
	public float getLineWidthFloat() {
		return 0;
	}

	@Override
	public boolean getXORMode() {
		return false;
	}

	@Override
	public void popState() {
	}

	@Override
	public void pushState() {
	}

	@Override
	public void restoreState() {
	}

	@Override
	public void scale(double amount) {
		System.err.println("\tscale(" + amount + ")");
		svgGenerator.scale(amount, amount);
	}

	@Override
	public void setBackgroundColor(Color rgb) {
		System.err.println("setBackgroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setBackground(awtColor);
	}

	@Override
	public void setClip(Rectangle r) {
		System.err.println("setClip(" + r + ")");
		svgGenerator.clip(new java.awt.Rectangle(r.x, r.y, r.width, r.height));
	}

	@Override
	public void setFont(Font f) {
	}

	@Override
	public void setForegroundColor(Color rgb) {
		System.err.println("setForegroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setColor(awtColor);
	}

	@Override
	public void setLineMiterLimit(float miterLimit) {
	}

	@Override
	public void setLineStyle(int style) {
	}

	@Override
	public void setLineWidth(int width) {
	}

	@Override
	public void setLineWidthFloat(float width) {
	}

	@Override
	public void setXORMode(boolean b) {
		System.err.println("setXORMode");
	}

	@Override
	public void translate(int dx, int dy) {
		System.err.println("\ttranslate(" + dx + "," + dy + ")");
		svgGenerator.translate(dx, dy);
	}

	@Override
	public void setAntialias(int value) {
	}

	@Override
	public void setLineAttributes(LineAttributes attributes) {
		System.err.println("setLineAttributes(" + attributes + ")");
	}

}
