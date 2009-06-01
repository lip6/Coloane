/**
 * 
 */
package fr.lip6.move.coloane.extensions.exportToPGF;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import fr.lip6.move.coloane.extensions.exportToPGF.converters.Converter;
import fr.lip6.move.coloane.extensions.exportToPGF.converters.ConverterFactory;
import fr.lip6.move.coloane.extensions.exportToPGF.converters.UnknownFormalismException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * @author alban
 *
 */
public final class Exporter {
	
	/**
	 * Magic number, because coordinates in Coloane an TikZ are not the same.
	 * TikZ coordinates could be the same, but then shapes are not scaled...
	 */
	private static double ratio = 0.032;
	
	//private IProgressMonitor monitor;
	
	private Logger logger;
	
	/**
	 * Converter for attribute values.
	 * Attribute values are strings, but they have to be modified for use in TikZ.
	 * For example, new lines are replaced by "\\".
	 */
	private Converter converter;
	
	public Exporter(IProgressMonitor monitor) {
		//this.monitor = monitor;
		this.logger = Logger.getLogger("fr.lip6.move.coloane.core");
		this.converter = null;
	}
	
	private final class TikzColor {
		int red;
		int green;
		int blue;
		TikzColor(Color color) {
			this.red = color.getRed();
			this.green = color.getGreen();
			this.blue = color.getBlue();
		}
	}
	
	private final class TikzPoint {
		double x;
		double y;
		TikzPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private TikzPoint position(Point location) {
		double x = location.x;
		double y = location.y;
		/**
		 *  @warning "y" position is inverse as (0,0) is at the upper-left corner for Coloane
		 *   		 and at bottom-left for TikZ.
		 */
		return new TikzPoint(x * ratio, - y * ratio);
	}

	private TikzPoint position(Dimension dimension, Point location) {
		double x = location.x + dimension.width  / 2;
		double y = location.y + dimension.height / 2;
		return new TikzPoint(x * ratio, - y * ratio);
	}
	
	private Map<String,Object> export(IAttribute attribute, int containerId) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("formalism", attribute.getAttributeFormalism().getName());
		result.put("name", attribute.getName());
		result.put("value", this.converter.convert(attribute));
		result.put("id", containerId);
		// Location:
		if ( (attribute.getGraphicInfo().getLocation().x == 0) && (attribute.getGraphicInfo().getLocation().y == 0) ) {
			throw new Exception();
		}
		result.put("x", this.position(attribute.getGraphicInfo().getLocation()).x);
		result.put("y", this.position(attribute.getGraphicInfo().getLocation()).y);
		// Background color:
		if ( (attribute.getGraphicInfo() == null) || (attribute.getGraphicInfo().getBackground() == null) ) {
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
		if ( (attribute.getGraphicInfo() == null) || (attribute.getGraphicInfo().getForeground() == null) ) {
			result.put("hasForeground", false);
		} else {
			result.put("hasForeground", true);
			Map<String, Object> foregroundMap = new HashMap<String, Object>();
			foregroundMap.put("red"  , new TikzColor(attribute.getGraphicInfo().getForeground()).red);
			foregroundMap.put("green", new TikzColor(attribute.getGraphicInfo().getForeground()).green);
			foregroundMap.put("blue" , new TikzColor(attribute.getGraphicInfo().getForeground()).blue);
			result.put("foreground", foregroundMap);
		}
		// Font:
		result.put("isItalic", attribute.getAttributeFormalism().isItalic());
		result.put("isBold", attribute.getAttributeFormalism().isBold());
		result.put("isMultiLine", attribute.getAttributeFormalism().isMultiLine());
		result.put("width", "3cm"); // TODO
		return result;
	}

	private Map<String,Object> export(INode node) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Basic formalism information:
		result.put("formalism", node.getNodeFormalism().getName());
		result.put("id", node.getId());
		// Position:
		result.put("x", this.position(node.getGraphicInfo().getSize(), node.getGraphicInfo().getLocation()).x);
		result.put("y", this.position(node.getGraphicInfo().getSize(), node.getGraphicInfo().getLocation()).y);
		// Background color:
		if ( (node.getGraphicInfo() == null) || (node.getGraphicInfo().getBackground() == null) ) {
			result.put("hasBackground", false);
		} else {
			result.put("hasBackground", true);
			Map<String, Object> backgroundMap = new HashMap<String, Object>();
			backgroundMap.put("red"  , new TikzColor(node.getGraphicInfo().getBackground()).red);
			backgroundMap.put("green", new TikzColor(node.getGraphicInfo().getBackground()).green);
			backgroundMap.put("blue" , new TikzColor(node.getGraphicInfo().getBackground()).blue);
			result.put("background", backgroundMap);
		}
		// Foreground color:
		if ( (node.getGraphicInfo() == null) || (node.getGraphicInfo().getForeground() == null) ) {
			result.put("hasForeground", false);
		} else {
			result.put("hasForeground", true);
			Map<String, Object> foregroundMap = new HashMap<String, Object>();
			foregroundMap.put("red"  , new TikzColor(node.getGraphicInfo().getForeground()).red);
			foregroundMap.put("green", new TikzColor(node.getGraphicInfo().getForeground()).green);
			foregroundMap.put("blue" , new TikzColor(node.getGraphicInfo().getForeground()).blue);
			result.put("foreground", foregroundMap);
		}
		// Height and width:
		// This attribute is not used, because TikZ size attributes depend on the shape.
		// Attributes:
		Collection<Object> attributes = new Vector<Object>();
		for (IAttribute attribute : node.getDrawableAttributes()) {
			try {
				attributes.add(this.export(attribute, node.getId()));
			} catch (Exception e) {
			}
		}
		result.put("attributes", attributes);
		return result;
	}
	
	private Map<String,Object> export(IArc arc) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Basic formalism information:
		result.put("formalism", arc.getArcFormalism().getName());
		result.put("id", arc.getId());
		result.put("source", arc.getSource().getId());
		result.put("target", arc.getTarget().getId());
		result.put("isCurve", arc.getGraphicInfo().getCurve());
		// Color:
		if ( (arc.getGraphicInfo() == null) || (arc.getGraphicInfo().getColor() == null) ) {
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
		Collection<Object> inflexions = new Vector<Object>();
		for (AbsoluteBendpoint point : arc.getInflexPoints()) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("x", this.position(point).x);
			map.put("y", this.position(point).y);
			map.put("isCurve", arc.getGraphicInfo().getCurve());
			inflexions.add(map);
		}
		result.put("inflexions", inflexions);
		// Attributes:
		Collection<Object> attributes = new Vector<Object>();
		for (IAttribute attribute : arc.getDrawableAttributes()) {
			try {
				attributes.add(this.export(attribute, arc.getId()));
			} catch (Exception e) {
			}
		}
		result.put("attributes", attributes);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExportTo#export(fr.lip6.move.coloane.interfaces.model.IGraph, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public StringTemplate export(IGraph graph) throws UnknownFormalismException {
		this.converter = ConverterFactory.createConverter(graph.getFormalism());
		StringTemplateGroup group = new StringTemplateGroup("pgfexport");
		StringTemplate query = group.getInstanceOf("templates/TikZ");
		String preamble = "templates/formalism/" + graph.getFormalism().getId();
		query.setAttribute("preamble", preamble);
		query.setAttribute("formalism", graph.getFormalism().getId());
		// Attributes:
		Collection<Object> attributes = new Vector<Object>();
		for (IAttribute attribute : graph.getDrawableAttributes()) {
			logger.finer(attribute.toString());
			try {
				attributes.add(this.export(attribute, graph.getId()));
			} catch (Exception e) {
				logger.finest("Could not export attribute " + attribute.getName());
			}
		}
		query.setAttribute("attributes", attributes);
		// nodes:
		Collection<Object> nodes = new Vector<Object>();
		for (INode node : graph.getNodes()) {
			nodes.add(this.export(node));
		}
		query.setAttribute("nodes", nodes);
		// arcs:
		Collection<Object> arcs = new Vector<Object>();
		for (IArc arc : graph.getArcs()) {
			arcs.add(this.export(arc));
		}
		query.setAttribute("arcs", arcs);
		return query;
	}

}
