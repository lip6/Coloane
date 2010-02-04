package fr.lip6.move.coloane.extension.importExportRomeo.importFromRomeo;

import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A class to parse a Romeo model from an XML file.
 * @author Yann TM 
 */
public class ModelHandler extends DefaultHandler {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.its"); //$NON-NLS-1$

	// context stack
	private Stack<Object> stack = new Stack<Object>();

	// to reconstruct Romeo IDS when parsing arcs.
	private Map<String, INode> placeIds = null;
	private Map<String, INode> transIds = null;

	// object constructed
	private IGraph graph;

	private Map<String, List<INode>> transColors;

	private Map<String, List<INode>> placeColors;

//	private Map<String, List<IArc>> arcColors;

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		// Balise MODEL
		if ("TPN".equals(baliseName)) { //$NON-NLS-1$
			graph = new GraphModelFactory().createGraph("Time Petri Net");
			placeIds = new HashMap<String, INode>();
			transIds = new HashMap<String, INode>();
			transColors = new HashMap<String, List<INode>>();
			placeColors = new HashMap<String, List<INode>>();
//			arcColors = new HashMap<String, List<IArc>>();
			// 6 colors only.
			for (int i = 0; i < 6; i++) {
				String lab = "c"+ i;
				transColors.put(lab, new ArrayList<INode>());
				placeColors.put(lab, new ArrayList<INode>());
//				arcColors.put(lab, new ArrayList<IArc>());
			}
		} else if ("place".equals(baliseName)) { //$NON-NLS-1$
			// stack a the place
			stack.push(handlePlace(attributes));
		} else if ("graphics".equals(baliseName)) { //$NON-NLS-1$
			String colorLab = attributes.getValue("color");
			if (colorLab != null) {
				storeObjectColor(colorLab);
			}
		} else if ("position".equals(baliseName)) { //$NON-NLS-1$
			handleNodePosition((INode) stack.peek(),attributes);
		} else if ("deltaLabel".equals(baliseName)) { //$NON-NLS-1$
			handleNodeLabelPosition((INode) stack.peek(),attributes);
		} else if ("scheduling".equals(baliseName)) { //$NON-NLS-1$
			// TODO : Do something to the place ?
		} else if ("transition".equals(baliseName)) { //$NON-NLS-1$
			// stack a the place
			stack.push(handleTransition(attributes));
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			// stack a the place
			stack.push(handleArc(attributes));
		} else if ("nail".equals(baliseName)) { //$NON-NLS-1$
			handleArcNail((IArc) stack.peek(), attributes);
		} else if ("preferences".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("colorPlace".equals(baliseName)) { //$NON-NLS-1$
			handlePlaceColors(attributes);
		} else if ("colorTransition".equals(baliseName)) { //$NON-NLS-1$
			handleTransitionColors(attributes);
		} else if ("colorArc".equals(baliseName)) { //$NON-NLS-1$
			// Happily ignore arc colors, feature unimplemented in Romeo GUI anyway.
			//			handleArcColors(attributes);
		} else {
			logger.warning("Unknown XML tag in source file: "+ baliseName); //$NON-NLS-1$
		}
	}


	/**
	 * Stores the color of an element (i.e. top of the stack)
	 * into the appropriate container.
	 * @param colorLab
	 */
	private void storeObjectColor(String colorLab) {
		Object obj = stack.peek();
		if (obj instanceof INode) {
			INode node = (INode) obj;
			if (node.getNodeFormalism().getName().equals("transition")) {		
				transColors.get("c"+ colorLab).add(node);
			} else {
				placeColors.get("c"+ colorLab).add(node);
			}
		}
		// IGNORE ARC COLORS
//		} else {
//			if (obj instanceof IArc) {
//				IArc arc = (IArc) obj;
//				arcColors.get("c"+ colorLab).add(arc);
//			}
//		}

	}


	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		// Balise MODEL
		if ("TPN".equals(baliseName)) { //$NON-NLS-1$
			// cleanup
			placeIds = null;
			transIds = null;
			transColors = null;
//			arcColors = null;
			placeColors = null;
		} else if ("place".equals(baliseName)) { //$NON-NLS-1$
			// pop place from context stack
			stack.pop();
		} else if ("graphics".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("position".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("deltaLabel".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("nail".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("transition".equals(baliseName)) { //$NON-NLS-1$
			// pop transition from context stack
			stack.pop();
		} else if ("arc".equals(baliseName)) { //$NON-NLS-1$
			// stack a the place
			stack.pop();
		} else if ("preferences".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("colorPlace".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("colorTransition".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("colorArc".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else {
			logger.warning("Unknown XML tag in source file: "+ baliseName); //$NON-NLS-1$
		}
	}


	/**
	 * Handle parse of a place.
	 * @param attributes the attributes of the place element
	 * @return the node constructed.
	 */
	private INode handlePlace(Attributes attributes) {
		INode place = null;
		try {
			place = graph.createNode("place");
			String label = attributes.getValue("label");
			if (label != null)
				place.getAttribute("name").setValue(label);
			String initialMarking = attributes.getValue("initialMarking");
			if (initialMarking != null)
				place.getAttribute("marking").setValue(initialMarking);
			
			// add to traces
			placeIds.put(attributes.getValue("id"), place);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return place;
	}

	/**
	 * Handle parse of a Transition.
	 * @param attributes The attributes of the XML element.
	 * @return the constructed node.
	 */
	private INode handleTransition(Attributes attributes) {
		INode transition = null;
		try {
			transition = graph.createNode("transition");
			String label = attributes.getValue("label");
			if (label != null)
				transition.getAttribute("label").setValue(label);
			
			String eft = attributes.getValue("eft");
			transition.getAttribute("earliestFiringTime").setValue(eft);
			
			String lft = attributes.getValue("lft");
			if (lft.equals("infini")) {
				transition.getAttribute("latestFiringTime").setValue("inf");
			} else {
				transition.getAttribute("latestFiringTime").setValue(lft);				
			}

			// add to traces
			transIds.put(attributes.getValue("id"), transition);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return transition;
	}


	/**
	 * Handle parse of an arc.
	 * @param attributes The attributes of the XML element.
	 * @return the arc constructed.
	 */
	private IArc handleArc(Attributes attributes) {
		IArc arc = null;
		try {
			INode place = placeIds.get(attributes.getValue("place"));
			INode trans = transIds.get(attributes.getValue("transition"));
			
			
			String type = attributes.getValue("type");			
			if ("PlaceTransition".equals(type)) {
				arc = graph.createArc("arc", place, trans);
			} else if ("TransitionPlace".equals(type)) {
				arc = graph.createArc("arc", trans, place);
			} else if ("flush".equals(type)) {
				arc = graph.createArc("reset", place, trans);
			} else if ("read".equals(type)) {
				arc = graph.createArc("test", place, trans);
			} else if ("logicalInhibitor".equals(type)) {
				arc = graph.createArc("inhibitor", place, trans);
			}
			IAttribute val = arc.getAttribute("valuation");
			if (val != null) {
				String weight = attributes.getValue("weight");
				val.setValue(weight);
			}
			
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return arc;
	}

	/**
	 * Parse the position of a node.
	 * @param node the node
	 * @param attributes The attributes of the XML element.
	 */
	private void handleNodePosition(INode node, Attributes attributes) {
		float x = Float.parseFloat(attributes.getValue("x"));
		float y = Float.parseFloat(attributes.getValue("y"));
		node.getGraphicInfo().setLocation(new Point(x,y));
	}

	/**
	 * Parse the position of a node label.
	 * @param node The node
	 * @param attributes The attributes of the XML element.
	 */
	private void handleNodeLabelPosition(INode node, Attributes attributes) {
		Point nodePos = node.getGraphicInfo().getLocation();
		float x = Float.parseFloat(attributes.getValue("deltax"));
		float y = Float.parseFloat(attributes.getValue("deltay"));
		Point labPos = new Point(x,y);
		IAttribute label;
		if ("place".equals(node.getNodeFormalism().getName())) {
			label = node.getAttribute("name");
		} else {
			label = node.getAttribute("label");			
		}
		label.getGraphicInfo().setLocation(labPos.translate(nodePos));
	}

	/**
	 * Add a nail = inflexion point to an arc.
	 * @param arc the arc
	 * @param attributes the attributes of the xml element.
	 */
	private void handleArcNail (IArc arc, Attributes attributes) {
		float x = Float.parseFloat(attributes.getValue("xnail"));
		float y = Float.parseFloat(attributes.getValue("ynail"));
		if (x != 0 && y != 0) {
			Point nail = new Point(x,y);
			arc.addInflexPoint(nail);
		}
	}

	/**
	 * Parse place colors.
	 * @param attributes the attributes of the xml element.
	 */
	private void handlePlaceColors(Attributes attributes) {
		for (int i = 0; i < 6; i++) {
			String colorIndex = "c"+ i;
			String color = attributes.getValue(colorIndex);
			
			Color bgColor = getColor (color);
			if (color == null) {
				break;
			}
			for (INode place : placeColors.get(colorIndex)) {				
				place.getGraphicInfo().setBackground(bgColor);
			}
		}
	}

	/**
	 * Parse transition colors.
	 * @param attributes the attributes of the xml element.
	 */
	private void handleTransitionColors(Attributes attributes) {
		for (int i = 0; i < 6; i++) {
			String colorIndex = "c"+ i;
			String color = attributes.getValue(colorIndex);
			
			Color bgColor = getColor (color);
			if (color == null) {
				break;
			}
			for (INode trans : transColors.get(colorIndex)) {				
				trans.getGraphicInfo().setBackground(bgColor);
			}
		}
	}

	/**
	 * Attempt to produce an approaching color based on the color name in Romeo.
	 * @param color the color name in romeo
	 * @return an appropriate color constant or null if no match found.
	 */
	private Color getColor(String color) {
		Color bgColor = null;
		if ("cyan".equals(color)) {
			bgColor  = ColorConstants.cyan;
		} else if ("yellow".equals(color)) {
			bgColor = ColorConstants.yellow;
		} else if ("gray".equals(color)) {
			bgColor = ColorConstants.gray;
		} else if ("brown".equals(color)) {
			bgColor = ColorConstants.darkGreen;
		} else if ("SkyBlue2".equals(color)) {
			bgColor = ColorConstants.lightBlue;
		} else if ("green".equals(color)) {
			bgColor = ColorConstants.green;
		} 
		return bgColor;
	}


	/**
	 * @return the graph loaded from the XML file
	 */
	public final IGraph getGraph() {
		return graph;
	}
}
