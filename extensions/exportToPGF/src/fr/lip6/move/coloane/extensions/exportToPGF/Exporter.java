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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exportToPGF;

import fr.lip6.move.coloane.extensions.exportToPGF.converters.Converter;
import fr.lip6.move.coloane.extensions.exportToPGF.converters.ConverterFactory;
import fr.lip6.move.coloane.extensions.exportToPGF.converters.UnknownFormalismException;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Export an Eclipse model to a TikZ representation, that can be used in (La)TeX.
 */
public final class Exporter {

	/**
	 * Magic number, because coordinates in Coloane an TikZ are not the same.
	 * TikZ coordinates could be the same, but then shapes are not scaled...
	 */
	private static double ratio = 0.035;

	@SuppressWarnings("unused")
	private IProgressMonitor monitor;

	@SuppressWarnings("unused")
	private Logger logger;

	/**
	 * Converter for attribute values. Attribute values are strings, but they
	 * have to be modified for use in TikZ. For example, new lines are replaced
	 * by "\\".
	 */
	private Converter converter;

	/**
	 * @param monitor Monitor to use to set progress during export.
	 */
	public Exporter(IProgressMonitor monitor) {
		this.monitor = monitor;
		this.logger = Logger.getLogger("fr.lip6.move.coloane.core");
		this.converter = null;
	}

	/**
	 * @return Graphical ratio.
	 */
	public static double getRatio() {
		return ratio;
	}

	/**
	 * Wrapper for colors, to build TikZ RGB colors from an Eclipse color.
	 */
	private final class TikzColor {
		private int red;
		private int green;
		private int blue;

		/**
		 * @param color The Eclipse color to convert.
		 */
		private TikzColor(Color color) {
			this.red = color.getRed();
			this.green = color.getGreen();
			this.blue = color.getBlue();
		}
	}

	/**
	 * Wrpper for points, to build TikZ coordinates from Eclipse ones.
	 * @warning Eclipse and Tikz use different coordinates schemes.
	 * 			<ul>
	 * 				<li> a ratio between Eclipse and TikZ coordinates is required </li>
	 * 				<li> "y" coordinates are inverted </li>
	 * 				<li> Eclipse refers to the upper-left coordinate of objects while TikZ refers to their centers </li>
	 * 			</ul>
	 */
	private final class TikzPoint {
		private double x;
		private double y;

		/**
		 * @param point The Eclipse point to convert.
		 */
		private TikzPoint(Point point) {
			this.x =  point.x * ratio;
			this.y = -point.y * ratio;
		}

		/**
		 * @param point The Eclipse point to convert.
		 * @param dimension The dimension of Eclipse object.
		 */
		private TikzPoint(Point point, Dimension dimension) {
			this.x = (point.x + dimension.width / 2) * ratio;
			this.y = (-point.y - dimension.height / 2) * ratio;
		}
	}

	/**
	 * @param attribute The attribute to convert.
	 * @param containerId The identifier of container of attribute.
	 * @return A map that can be used with StringTemplate.
	 * @throws EmptyCoordinatesException If attribute has (0,0) coordinates.
	 */
	private Map<String, Object> export(IAttribute attribute, int containerId) throws EmptyCoordinatesException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("formalism", converter.getName(attribute));
		result.put("value", this.converter.convert(attribute));
		result.put("id", containerId);
		// Location:
		if (!attribute.getGraphicInfo().isVisible()) {
			throw new EmptyCoordinatesException();
		}
		result.put("x", new TikzPoint(attribute.getGraphicInfo().getLocation(), attribute.getGraphicInfo().getSize()).x);
		result.put("y", new TikzPoint(attribute.getGraphicInfo().getLocation(), attribute.getGraphicInfo().getSize()).y);
		// Background color:
		if ((attribute.getGraphicInfo() == null) || (attribute.getGraphicInfo().getBackground() == null)) {
			result.put("hasBackground", false);
		} else {
			result.put("hasBackground", true);
			Map<String, Object> backgroundMap = new HashMap<String, Object>();
			backgroundMap.put("red"  , new TikzColor(attribute.getGraphicInfo().getBackground()).red);
			backgroundMap.put("green", new TikzColor(attribute.getGraphicInfo().getBackground()).green);
			backgroundMap.put("blue" , new TikzColor(attribute.getGraphicInfo().getBackground()).blue);
			result.put("background", backgroundMap);
		}
		// Foreground color:
		if ((attribute.getGraphicInfo() == null)
				|| (attribute.getGraphicInfo().getForeground() == null)) {
			result.put("hasForeground", false);
		} else {
			result.put("hasForeground", true);
			Map<String, Object> foregroundMap = new HashMap<String, Object>();
			foregroundMap.put("red", new TikzColor(attribute.getGraphicInfo().getForeground()).red);
			foregroundMap.put("green", new TikzColor(attribute.getGraphicInfo().getForeground()).green);
			foregroundMap.put("blue", new TikzColor(attribute.getGraphicInfo().getForeground()).blue);
			result.put("foreground", foregroundMap);
		}
		// Font:
		result.put("isItalic", attribute.getAttributeFormalism().isItalic());
		result.put("isBold", attribute.getAttributeFormalism().isBold());
		if (attribute.getAttributeFormalism() instanceof IAttributeFormalism) {
			result.put("isMultiLine", ((IAttributeFormalism) attribute.getAttributeFormalism()).isMultiLine());
		} else {
			// FIXME : i'm not sure about this, i need a method to test if the attribute is multiline even for computed attributes.
			result.put("isMultiLine", false);
		}
		result.put("width" , attribute.getGraphicInfo().getSize().width  * ratio);
		result.put("height", attribute.getGraphicInfo().getSize().height * ratio);
		return result;
	}

	/**
	 * @param node Node to convert.
	 * @return A map that can be used with StringTemplate.
	 */
	private Map<String, Object> export(INode node) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Basic formalism information:
		result.put("formalism", node.getNodeFormalism().getName());
		result.put("id", node.getId());
		// Position:
		result.put("x", new TikzPoint(node.getGraphicInfo().getLocation(), node.getGraphicInfo().getSize()).x);
		result.put("y", new TikzPoint(node.getGraphicInfo().getLocation(), node.getGraphicInfo().getSize()).y);
		// Background color:
		if ((node.getGraphicInfo() == null) || (node.getGraphicInfo().getBackground() == null)) {
			result.put("hasBackground", false);
		} else {
			result.put("hasBackground", true);
			Map<String, Object> backgroundMap = new HashMap<String, Object>();
			backgroundMap.put("red", new TikzColor(node.getGraphicInfo().getBackground()).red);
			backgroundMap.put("green", new TikzColor(node.getGraphicInfo().getBackground()).green);
			backgroundMap.put("blue", new TikzColor(node.getGraphicInfo().getBackground()).blue);
			result.put("background", backgroundMap);
		}
		// Foreground color:
		if ((node.getGraphicInfo() == null)
				|| (node.getGraphicInfo().getForeground() == null)) {
			result.put("hasForeground", false);
		} else {
			result.put("hasForeground", true);
			Map<String, Object> foregroundMap = new HashMap<String, Object>();
			foregroundMap.put("red", new TikzColor(node.getGraphicInfo()
					.getForeground()).red);
			foregroundMap.put("green", new TikzColor(node.getGraphicInfo()
					.getForeground()).green);
			foregroundMap.put("blue", new TikzColor(node.getGraphicInfo()
					.getForeground()).blue);
			result.put("foreground", foregroundMap);
		}
		// Height and width:
		// This attribute is not used, because TikZ size attributes depend on
		// the shape.
		// Attributes:
		Collection<Object> attributes = new ArrayList<Object>();
		for (IAttribute attribute : node.getDrawableAttributes()) {
			try {
				attributes.add(this.export(attribute, node.getId()));
			} catch (EmptyCoordinatesException e) {
				continue;
			}
		}
		result.put("attributes", attributes);
		return result;
	}

	/**
	 * @param arc The arc to convert.
	 * @return A map that can be used with StringTemplate.
	 */
	private Map<String, Object> export(IArc arc) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Basic formalism information:
		result.put("formalism", arc.getArcFormalism().getName());
		result.put("id", arc.getId());
		result.put("source", arc.getSource().getId());
		result.put("target", arc.getTarget().getId());
		result.put("isCurve", arc.getGraphicInfo().getCurve());
		// Color:
		if ((arc.getGraphicInfo() == null) || (arc.getGraphicInfo().getColor() == null)) {
			result.put("hasColor", false);
		} else {
			result.put("hasColor", true);
			Map<String, Object> colorMap = new HashMap<String, Object>();
			colorMap.put("red"  , new TikzColor(arc.getGraphicInfo().getColor()).red);
			colorMap.put("green", new TikzColor(arc.getGraphicInfo().getColor()).green);
			colorMap.put("blue" , new TikzColor(arc.getGraphicInfo().getColor()).blue);
			result.put("color", colorMap);
		}
		// Inflexion points:
		Collection<Object> inflexions = new ArrayList<Object>();
		for (AbsoluteBendpoint point : arc.getInflexPoints()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("x", new TikzPoint(point).x);
			map.put("y", new TikzPoint(point).y);
			map.put("isCurve", arc.getGraphicInfo().getCurve());
			inflexions.add(map);
		}
		result.put("inflexions", inflexions);
		// Attributes:
		Collection<Object> attributes = new ArrayList<Object>();
		for (IAttribute attribute : arc.getDrawableAttributes()) {
			try {
				attributes.add(this.export(attribute, arc.getId()));
			} catch (EmptyCoordinatesException e) {
				continue;
			}
		}
		result.put("attributes", attributes);
		return result;
	}

	/**
	 * @param graph The graph to export.
	 * @return A StringTemplate query to execute, by converting it to a string.
	 * @throws UnknownFormalismException If formalism of graph is unknown
	 * 		   to the attribute text converters factory.
	 */
	public StringTemplate export(IGraph graph) throws UnknownFormalismException {
		this.converter = ConverterFactory.createConverter(graph.getFormalism());
		StringTemplateGroup group = new StringTemplateGroup("pgfexport");
		StringTemplate query = group.getInstanceOf("resources/stringtemplates/TikZ");
		StringBuffer preamble = new StringBuffer("resources/stringtemplates/formalism/");
		preamble.append(graph.getFormalism().getId());
		query.setAttribute("preamble", preamble);
		query.setAttribute("formalism", graph.getFormalism().getId());
		// Attributes:
		Collection<Object> attributes = new ArrayList<Object>();
		for (IAttribute attribute : graph.getDrawableAttributes()) {
			try {
				attributes.add(this.export(attribute, graph.getId()));
			} catch (EmptyCoordinatesException e) {
				continue;
			}
		}
		query.setAttribute("attributes", attributes);
		// nodes:
		Set<String> encountered = new HashSet<String>();
		Collection<Object> nodes = new ArrayList<Object>();
		for (INode node : graph.getNodes()) {
			if (!encountered.contains(node.getNodeFormalism().getName())) {
				encountered.add(node.getNodeFormalism().getName());
				converter.setGraphicalDescription(query, node);
			}
			nodes.add(this.export(node));
		}
		query.setAttribute("nodes", nodes);
		for (String name : encountered) {
			StringBuilder str = new StringBuilder("has");
			str.append(name);
			query.setAttribute(str.toString(), true);
		}
		// arcs:
		Collection<Object> arcs = new ArrayList<Object>();
		for (IArc arc : graph.getArcs()) {
			arcs.add(this.export(arc));
		}
		query.setAttribute("arcs", arcs);
		return query;
	}

}
