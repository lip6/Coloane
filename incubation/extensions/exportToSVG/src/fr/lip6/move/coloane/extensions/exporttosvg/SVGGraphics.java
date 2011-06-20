/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exporttosvg;

import java.awt.AWTError;
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

/**
 * Implementation of the class Graphics based on SVGGraphics2D
 *
 * @author Clément Démoulins
 */
public class SVGGraphics extends Graphics {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private boolean curve = false;
	private final Map<java.awt.Color, Color> colors = new HashMap<java.awt.Color, Color>();

	/** Width of the rounded corner */
	private int cornerLen = 32;

	private SVGGraphics2D svgGenerator;

	/**
	 * Constructor
	 */
	public SVGGraphics() {
		// Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        svgGenerator = new SVGGraphics2D(document);
	}

	/**
	 * @param filename write the svg data into this file
	 * @throws IOException If the file is not writable
	 */
	public final void write(String filename) throws IOException {
		svgGenerator.stream(filename);
	}

	/**
	 * @return <code>true</code> if the polyline must be draw with curve, default set to <code>false</code>
	 */
	public final boolean isCurve() {
		return curve;
	}

	/**
	 * @param curve set <code>true</code> if the polyline must be draw with curve
	 */
	public final void setCurve(boolean curve) {
		this.curve = curve;
	}

	@Override
	public final void clipRect(Rectangle r) {
		LOGGER.warning("clipRect not implemented");
//		svgGenerator.clipRect(r.x, r.y, r.width, r.height);
	}

	@Override
	public final void dispose() {
		svgGenerator.dispose();
	}

	@Override
	public final void drawArc(int x, int y, int w, int h, int offset, int length) {
		LOGGER.finer("drawArc");
		svgGenerator.drawArc(x, y, w, h, offset, length);
	}

	@Override
	public final void drawFocus(int x, int y, int w, int h) {
		LOGGER.warning("drawFocus not implemented");
	}

	@Override
	public final void drawImage(Image srcImage, int x, int y) {
		LOGGER.warning("drawImage not implemented");
	}

	@Override
	public final void drawImage(Image srcImage, int x1, int y1, int w1, int h1,
			int x2, int y2, int w2, int h2) {
		LOGGER.warning("drawImage2 not implemented");
	}

	@Override
	public final void drawLine(int x1, int y1, int x2, int y2) {
		LOGGER.finer("drawLine");
		svgGenerator.drawLine(x1, y1, x2, y2);
	}

	@Override
	public final void drawOval(int x, int y, int w, int h) {
		LOGGER.finer("drawOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		svgGenerator.drawOval(x, y, w, h);
	}

	@Override
	public final void drawPolygon(PointList points) {
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
	public final void drawPolyline(PointList points) {
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
	public final void drawRectangle(int x, int y, int width, int height) {
		LOGGER.finer("drawRectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
		svgGenerator.drawRect(x, y, width, height);
	}

	@Override
	public final void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		LOGGER.finer("drawRoundRectangle");
		svgGenerator.drawRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
	}

	@Override
	public final void drawString(String s, int x, int y) {
		LOGGER.finer("drawString");
		// le -4 permet de corriger un décalage du texte par rapport
		// au rectangle dessiner pour gérer le surlignage des attributs
		svgGenerator.drawString(s, x, y - 4);
	}

	@Override
	public final void drawText(String s, int x, int y) {
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
	public final void fillArc(int x, int y, int w, int h, int offset, int length) {
		LOGGER.warning("fillArc not implemented");
	}

	@Override
	public final void fillGradient(int x, int y, int w, int h, boolean vertical) {
		LOGGER.warning("fillGradient not implemented");
	}

	@Override
	public final void fillOval(int x, int y, int w, int h) {
		LOGGER.finer("fillOval(" + x + ", " + y + ", " + w + ", " + h + ")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillOval(x, y, w, h);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public final void fillPolygon(PointList points) {
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
	public final void fillRectangle(int x, int y, int width, int height) {
		LOGGER.finer("fillRectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRect(x, y, width, height);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public final void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		LOGGER.finer("fillRoundRectangle");
		java.awt.Color oldColor = svgGenerator.getColor();
		svgGenerator.setColor(svgGenerator.getBackground());
		svgGenerator.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
		svgGenerator.setColor(oldColor);
	}

	@Override
	public final void fillString(String s, int x, int y) {
		LOGGER.finer("fillString");
		// le -4 permet de corriger un décalage du texte par rapport
		// au rectangle dessiner pour gérer le surlignage des attributs
		svgGenerator.drawString(s, x, y - 4);
	}

	@Override
	public final void fillText(String s, int x, int y) {
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
	public final Color getBackgroundColor() {
		Color color = colors.get(svgGenerator.getColor());
		if (color == null) {
			return ColorConstants.white;
		}
		return color;
	}

	@Override
	public final Rectangle getClip(Rectangle r) {
		LOGGER.finer("getClip(" + r.getResized(1, 1) + ")");
		return r.getResized(1, 1);
	}

	@Override
	public final Font getFont() {
		LOGGER.warning("getFont not implemented");
		return null;
	}

	@Override
	public final FontMetrics getFontMetrics() {
		LOGGER.warning("getFontMetrics not implemented");
		return null;
	}

	@Override
	public final Color getForegroundColor() {
		Color color = colors.get(svgGenerator.getColor());
		if (color == null) {
			return ColorConstants.black;
		}
		return color;
	}

	@Override
	public final int getLineStyle() {
		LOGGER.warning("getLineStyle not implemented");
		return 0;
	}

	@Override
	public final int getLineWidth() {
		LOGGER.warning("getLineWidth not implemented");
		return 0;
	}

	/**
	 * Not implemented
	 * @return 0
	 */
	public final float getLineWidthFloat() {
		LOGGER.warning("getLineWidthFloat not implemented");
		return 0;
	}

	@Override
	public final boolean getXORMode() {
		LOGGER.warning("getXORMode not implemented");
		return false;
	}

	@Override
	public final void popState() {
		LOGGER.warning("popState not implemented");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Graphics#pushState()
	 */
	@Override
	public final void pushState() {
		LOGGER.warning("pushState not implemented");
	}

	@Override
	public final void restoreState() {
		LOGGER.warning("restoreState not implemented");
	}

	@Override
	public final void scale(double amount) {
		LOGGER.finer("\tscale(" + amount + ")");
		svgGenerator.scale(amount, amount);
	}

	@Override
	public final void setBackgroundColor(Color rgb) {
		LOGGER.finer("setBackgroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setBackground(awtColor);
	}

	@Override
	public final void setClip(Rectangle r) {
		LOGGER.finer("setClip(" + r + ")");
		svgGenerator.clip(new java.awt.Rectangle(r.x, r.y, r.width, r.height));
	}

	@Override
	public final void setFont(Font f) {
		LOGGER.finer("setFont(" + f.getFontData()[0].getStyle() + ", " + f.getFontData()[0].getHeight() + ")");
		java.awt.Font awtFont = svgGenerator.getFont();
		svgGenerator.setFont(awtFont.deriveFont(f.getFontData()[0].getStyle(), f.getFontData()[0].getHeight()));
	}

	@Override
	public final void setForegroundColor(Color rgb) {
		LOGGER.finer("setForegroundColor(" + rgb + ")");
		java.awt.Color awtColor = new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		colors.put(awtColor, rgb);
		svgGenerator.setColor(awtColor);
	}

	/**
	 * Not implemented
	 * @param miterLimit -
	 */
	public final void setLineMiterLimit(float miterLimit) {
		LOGGER.warning("setLineMiterLimit not implemented");
	}

	@Override
	public final void setLineStyle(int style) {
		LOGGER.warning("setLineStyle not implemented");
	}

	@Override
	public final void setLineWidth(int width) {
		LOGGER.warning("setLineWidth not implemented");
	}

	/**
	 * Not implemented
	 * @param width -
	 */
	public final void setLineWidthFloat(float width) {
		LOGGER.warning("setLineWidthFloat not implemented");
	}

	@Override
	public final void setXORMode(boolean b) {
		LOGGER.warning("setXORMode not implemented");
	}

	@Override
	public final void translate(int dx, int dy) {
		LOGGER.finer("\ttranslate(" + dx + "," + dy + ")");
		svgGenerator.translate(dx, dy);
	}

	@Override
	public final void setAntialias(int value) {
		// No meaning in SVG
	}

	/**
	 * Not implemented
	 * @param attributes -
	 */
	public final void setLineAttributes(LineAttributes attributes) {
		LOGGER.warning("setLineAttributes not implemented");
	}

}
