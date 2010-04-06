package fr.lip6.move.coloane.extensions.exporttosvg;

import java.awt.Polygon;
import java.awt.font.TextLayout;
import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SVGGraphics extends Graphics {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private boolean curve = false;
	private final Map<java.awt.Color, Color> colors = new HashMap<java.awt.Color, Color>();

	/** Width of the rounded corner */
	private int cornerLen = 32;

	private SVGGraphics2D svgGenerator;

	public SVGGraphics() throws IOException {
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
		LOGGER.warning("clipRect(" + r + ")");
	}

	@Override
	public void dispose() {
		svgGenerator.dispose();
	}

	@Override
	public void drawArc(int x, int y, int w, int h, int offset, int length) {
		LOGGER.finer("drawArc");
		svgGenerator.drawArc(x, y, w, h, offset, length);
	}

	@Override
	public void drawFocus(int x, int y, int w, int h) {
		LOGGER.warning("drawFocus");
	}

	@Override
	public void drawImage(Image srcImage, int x, int y) {
		LOGGER.warning("drawImage");
	}

	@Override
	public void drawImage(Image srcImage, int x1, int y1, int w1, int h1,
			int x2, int y2, int w2, int h2) {
		LOGGER.warning("drawImage");
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		LOGGER.finer("drawLine");
		svgGenerator.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawOval(int x, int y, int w, int h) {
		LOGGER.finer("drawOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		svgGenerator.drawOval(x, y, w, h);
	}

	@Override
	public void drawPolygon(PointList points) {
		StringBuilder sb = new StringBuilder("drawPolygon(");
		Polygon poly = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			sb.append(" [" + p.x + "," + p.y + "]");
			poly.addPoint(p.x, p.y);
		}
		sb.append(")");
		LOGGER.finer(sb.toString());
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
		StringBuilder sb = new StringBuilder("drawCurvePolyline(");
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			sb.append(" [" + p.x + "," + p.y + "]");
		}
		sb.append(")");
		LOGGER.finer(sb.toString());

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
			StringBuilder sb = new StringBuilder("drawPolyline(");
			Polygon poly = new Polygon();
			for (int i = 0; i < points.size(); i++) {
				Point p = points.getPoint(i);
				sb.append(" [" + p.x + "," + p.y + "]");
				poly.addPoint(p.x, p.y);
			}
			sb.append(")");
			LOGGER.finer(sb.toString());
			svgGenerator.drawPolyline(poly.xpoints, poly.ypoints, poly.npoints);
		}
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		LOGGER.finer("drawRectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
		svgGenerator.drawRect(x, y, width, height);
	}

	@Override
	public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		LOGGER.finer("drawRoundRectangle");
		svgGenerator.drawRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
	}

	@Override
	public void drawString(String s, int x, int y) {
		LOGGER.finer("drawString");
		// le -4 permet de corriger un décalage du texte par rapport
		// au rectangle dessiner pour gérer le surlignage des attributs
		svgGenerator.drawString(s, x, y - 4);
	}

	@Override
	public void drawText(String s, int x, int y) {
		LOGGER.finer("drawText(" + s + "," + x + "," + y + ")");
		float fx = x, fy = y;
		for (String line : s.split("\n")) {
			TextLayout text = new TextLayout(line, svgGenerator.getFont(), svgGenerator.getFontRenderContext());
			fy += text.getAscent();
			text.draw(svgGenerator, fx, fy);
			fy += text.getDescent() + text.getLeading();
		}
	}

	@Override
	public void fillArc(int x, int y, int w, int h, int offset, int length) {
		LOGGER.warning("fillArc");
	}

	@Override
	public void fillGradient(int x, int y, int w, int h, boolean vertical) {
		LOGGER.warning("fillGradient");
	}

	@Override
	public void fillOval(int x, int y, int w, int h) {
		LOGGER.finer("fillOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillOval(x, y, w, h);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillPolygon(PointList points) {
		StringBuilder sb = new StringBuilder("fillPolygon(");
		Polygon poly = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.getPoint(i);
			sb.append(" [" + p.x + "," + p.y + "]");
			poly.addPoint(p.x, p.y);
		}
		sb.append(")");
		LOGGER.finer(sb.toString());
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillPolygon(poly);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		LOGGER.finer("fillRectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRect(x, y, width, height);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		LOGGER.finer("fillRoundRectangle");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public void fillString(String s, int x, int y) {
		LOGGER.finer("fillString");
		// le -4 permet de corriger un décalage du texte par rapport
		// au rectangle dessiner pour gérer le surlignage des attributs
		svgGenerator.drawString(s, x, y - 4);
	}

	@Override
	public void fillText(String s, int x, int y) {
		LOGGER.finer("fillText(\"" + s + "\", " + x + ", " + y + ")");
		float fx = x, fy = y;
		float width = 0, height = 0;
		for (String line : s.split("\n")) {
			TextLayout text = new TextLayout(line, svgGenerator.getFont(), svgGenerator.getFontRenderContext());
			fy += text.getAscent() + text.getDescent() + text.getLeading();
			width = Math.max(width, (float) text.getBounds().getWidth());
		}
		height = fy - y;
		fillRectangle(x, y, (int) width, (int) height);
		fy = y;
		for (String line : s.split("\n")) {
			TextLayout text = new TextLayout(line, svgGenerator.getFont(), svgGenerator.getFontRenderContext());
			fy += text.getAscent();
			svgGenerator.drawString(line, fx, fy);
//			text.draw(svgGenerator, fx, fy);
			fy += text.getDescent() + text.getLeading();
		}
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
		LOGGER.finer("getClip(" + r.getResized(1, 1) + ")");
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
		LOGGER.finer("getLineWidth()");
		return 0;
	}

	public float getLineWidthFloat() {
		return 0;
	}

	@Override
	public boolean getXORMode() {
		return false;
	}

	@Override
	public void popState() {
		LOGGER.warning("popState()");
	}

	@Override
	public void pushState() {
		LOGGER.warning("pushState()");
	}

	@Override
	public void restoreState() {
		LOGGER.warning("restoreState()");
	}

	@Override
	public void scale(double amount) {
		LOGGER.finer("\tscale(" + amount + ")");
		svgGenerator.scale(amount, amount);
	}

	@Override
	public void setBackgroundColor(Color rgb) {
		LOGGER.finer("setBackgroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setBackground(awtColor);
	}

	@Override
	public void setClip(Rectangle r) {
		LOGGER.finer("setClip(" + r + ")");
		svgGenerator.clip(new java.awt.Rectangle(r.x, r.y, r.width, r.height));
	}

	@Override
	public void setFont(Font f) {
		LOGGER.finer("setFont(" + f.getFontData()[0].getStyle() + ", " + f.getFontData()[0].getHeight() + ")");
		java.awt.Font awtFont = svgGenerator.getFont();
		svgGenerator.setFont(awtFont.deriveFont(f.getFontData()[0].getStyle(), f.getFontData()[0].getHeight()));
	}

	@Override
	public void setForegroundColor(Color rgb) {
		LOGGER.finer("setForegroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setColor(awtColor);
	}

	public void setLineMiterLimit(float miterLimit) {
	}

	@Override
	public void setLineStyle(int style) {
	}

	@Override
	public void setLineWidth(int width) {
	}

	public void setLineWidthFloat(float width) {
	}

	@Override
	public void setXORMode(boolean b) {
		LOGGER.warning("setXORMode");
	}

	@Override
	public void translate(int dx, int dy) {
		LOGGER.finer("\ttranslate(" + dx + "," + dy + ")");
		svgGenerator.translate(dx, dy);
	}

	@Override
	public void setAntialias(int value) {
	}

	public void setLineAttributes(LineAttributes attributes) {
		System.err.println("setLineAttributes(" + attributes + ")");
	}

}
