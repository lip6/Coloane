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
package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.formalisms.Formalism;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.LinkModel;
import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.core.model.factory.SaxHandler;
import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * How to read an XML file in order to produce a model.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class ModelHandler extends DefaultHandler {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Object Stack */
	private Stack<Object> stack = new Stack<Object>();

	/** Mapping between file ids and new objects ids */
	private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();

	/** The final graph */
	private IGraph graph;

	/** Various data */
	private StringBuilder data = new StringBuilder();

	/** ElementFormalism Cache */
	private Map<String, IElementFormalism> formalismCache = new HashMap<String, IElementFormalism>();

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		data.setLength(0);

		// MODEL
		if (ModelConstants.MODEL_MARKUP.equals(baliseName)) {
			startModel(attributes);

		// NODE
		} else if (ModelConstants.NODE_MARKUP.equals(baliseName)) {
			try {
				startNode(attributes);
			} catch (ModelException e) {
				LOGGER.warning(e.getMessage());
				throw new IllegalArgumentException(e);
			}

		// ARC
		} else if (ModelConstants.ARC_MARKUP.equals(baliseName)) {
			try {
				startArc(attributes);
			} catch (ModelException e) {
				LOGGER.warning(e.getMessage());
				throw new IllegalArgumentException(e);
			}

		// STICKY NOTE
		} else if (ModelConstants.STICKY_MARKUP.equals(baliseName)) {
			startStickyNote(attributes);

		// INFLEX POINT
		} else if (ModelConstants.PI_MARKUP.equals(baliseName)) {
			startInflexPoint(attributes);

		// ATTRIBUTE
		} else if (ModelConstants.ATTRIBUTE_MARKUP.equals(baliseName)) {
			startAttribute(attributes.getValue(ModelConstants.ATTRIBUTE_NAME_MARKUP), attributes);

		// LINK (between sticky note and elements)
		} else if (ModelConstants.LINK_MARKUP.equals(baliseName)) {
			startLink(attributes);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data.append(this.deformat(new String(ch, start, length)));
	}

	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			endModel();
		} else if (ModelConstants.NODE_MARKUP.equals(baliseName)) {
			endNode();
		} else if (ModelConstants.STICKY_MARKUP.equals(baliseName)) {
			endStickyNote();
		} else if (ModelConstants.ARC_MARKUP.equals(baliseName)) {
			endArc();
		} else if (ModelConstants.PI_MARKUP.equals(baliseName)) {
			endInflexPoint();
		} else if (ModelConstants.ATTRIBUTE_MARKUP.equals(baliseName)) {
			endAttribute();
		} else if (ModelConstants.ATTRIBUTE_VALUE_MARKUP.equals(baliseName)) {
			endValue();
		}
	}

	/**
	 * Deal with special characters (unprotect)
	 * @param protectedTxt The text to clean up
	 * @return The text without any protection
	 */
	private String deformat(String protectedTxt) {
		String txt = protectedTxt;
		txt = txt.replaceAll("&amp;", "&"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&lt;", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&gt;", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}

	/**
	 * Start to parse the model.<br>
	 * <i>The stack should be empty</i>
	 * @param attributes Set of attributes attached to the current element
	 * @throws SAXException Wrap an IllegalArgumentException throws by {@link GraphModel}.
	 */
	private void startModel(Attributes attributes) throws SAXException {
		// Fetch the formalism name
		String formalismURL = attributes.getValue(ModelConstants.MODEL_FORMALISM_MARKUP);

		// Build the graph
		try {
			IFormalism formalism;
			try {
				formalism = FormalismManager.getInstance().getFormalismByUrl(formalismURL);
			} catch (IllegalArgumentException e) {

				//TODO: have this happen in formalismmanager, and add the new formalism
				//to the list of known formalisms, I think it might actually not work being done here
				//since it cannot be found with getformalismby (id, url, etc)

				formalism = new Formalism("", "", formalismURL, null);  //$NON-NLS-1$//$NON-NLS-2$
				URL url;
				try {
				url = new URL(formalismURL);
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				SaxHandler handler = new SaxHandler((Formalism) formalism);
				saxParser.parse(url.openStream(), handler);
				formalism = handler.getFormalism();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParserConfigurationException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			IGraph graph = new GraphModel(formalism);
			stack.push(graph);
			this.graph = graph;

			// build the formalism cache
			for (IElementFormalism elementFormalism : formalism.getAllElementFormalism()) {
				formalismCache.put(elementFormalism.getName(), elementFormalism);
			}

		} catch (IllegalArgumentException e) {
			throw new SAXException(e);
		}
	}

	/**
	 * Parse a node.<br>
	 * @param attributes Set of attributes attached to the current element
	 * @throws ModelException If something went wring during the node creation
	 */
	private void startNode(Attributes attributes) throws ModelException {
		IGraph graph = (IGraph) stack.peek();

		// Fetch information about the node
		int x = Integer.parseInt(attributes.getValue(ModelConstants.NODE_X_MARKUP));
		int y = Integer.parseInt(attributes.getValue(ModelConstants.NODE_Y_MARKUP));
		String nodeFormalismName = attributes.getValue(ModelConstants.NODE_TYPE_MARKUP);
		int id = Integer.parseInt(attributes.getValue(ModelConstants.NODE_ID_MARKUP));

		// Build the node
		INode node = graph.createNode((INodeFormalism) formalismCache.get(nodeFormalismName));
		ids.put(id, node.getId());
		node.getGraphicInfo().setLocation(new Point(x, y));

		// Node size
		try {
			int scale = Integer.parseInt(attributes.getValue(ModelConstants.NODE_SCALE_MARKUP));
			node.getGraphicInfo().setScale(scale);
		} catch (NumberFormatException e) {
			LOGGER.fine("Scale attribute does not exist or is invalid"); //$NON-NLS-1$
		}

		// Alternate figures
		try {
			int alt = Integer.parseInt(attributes.getValue(ModelConstants.NODE_ALTERNATE_MARKUP));
			node.getGraphicInfo().switchGraphicalDescription(alt);
		} catch (NumberFormatException e) {
			LOGGER.fine("Alternte figure attribute does not exist or is invalid"); //$NON-NLS-1$
			node.getGraphicInfo().switchGraphicalDescription(0);
		}

		// Node foreground color
		try {
			Color foreground = parseColor(attributes.getValue(ModelConstants.NODE_FOREGROUND_MARKUP));
			node.getGraphicInfo().setForeground(foreground);
		} catch (NumberFormatException e) {
			LOGGER.fine("Foreground attribute does not exist or is invalid"); //$NON-NLS-1$
		}

		// Node background color
		try {
			Color background = parseColor(attributes.getValue(ModelConstants.NODE_BACKGROUND_MARKUP));
			node.getGraphicInfo().setBackground(background);
		} catch (NumberFormatException e) {
			LOGGER.fine("Background attributedoes not exist or is invalid"); //$NON-NLS-1$
		}

		// Is interface ?
		try {
			boolean state = Boolean.valueOf(attributes.getValue(ModelConstants.NODE_INTERFACE_MARKUP));
			node.setInterface(state);
		} catch (NumberFormatException e) {
			LOGGER.fine("Interface attribute does not exist or is invalid"); //$NON-NLS-1$
		}

		// Node link
		try {
			String nodeLink = attributes.getValue(ModelConstants.NODE_LINK_MARKUP);
			node.setNodeLink(nodeLink);
		} catch (NumberFormatException e) {
			LOGGER.fine("Link attribut does not exist or is invalid"); //$NON-NLS-1$
		}

		stack.push(node);
	}

	/**
	 * Parse a sticky note
	 * @param attributes Set of attributes attached to the current element
	 */
	private void startStickyNote(Attributes attributes) {
		ICoreGraph graph = (ICoreGraph) stack.peek();

		// Fetch information about the sticky note
		int x = Integer.parseInt(attributes.getValue(ModelConstants.STICKY_X_MARKUP));
		int y = Integer.parseInt(attributes.getValue(ModelConstants.STICKY_Y_MARKUP));
		int width = Integer.parseInt(attributes.getValue(ModelConstants.STICKY_WIDTH_MARKUP));
		int height = Integer.parseInt(attributes.getValue(ModelConstants.STICKY_HEIGHT_MARKUP));

		// Build the note
		IStickyNote note = graph.createStickyNote();
		note.setLocation(new Point(x, y));
		note.setSize(new Dimension(width, height));
		stack.push(note);
	}

	/**
	 * Build a link between the top stack sticky note and the referenced element
	 * @param attributes Set of attributes attached to the current element
	 */
	private void startLink(Attributes attributes) {
		IStickyNote note = (IStickyNote) stack.pop();
		ICoreGraph graph = (ICoreGraph) stack.peek();
		int linkId = Integer.parseInt(attributes.getValue(ModelConstants.LINK_REFERENCE_MARKUP));
		IElement element = graph.getObject(ids.get(linkId));

		// Build the link
		ILink newLink = new LinkModel(note, (ILinkableElement) element);
		newLink.connect();

		stack.push(note);
	}

	/**
	 * Transform a string into a color
	 * @param value Color description under hexadecimal form '#XXXXXX'
	 * @return The corresponding color
	 * @throws NumberFormatException if the color string is not valid
	 */
	private Color parseColor(String value) throws NumberFormatException {
		if (value == null || !value.matches("#\\p{XDigit}{6}")) { //$NON-NLS-1$
			throw new NumberFormatException("This value : " + value + " is not a valid color.");  //$NON-NLS-1$//$NON-NLS-2$
		}
		Color color = JFaceResources.getColorRegistry().get(value);
		if (color == null) {
			RGB rgb = new RGB(
				Integer.parseInt(value.substring(1, 3), 16),
				Integer.parseInt(value.substring(3, 5), 16),
				Integer.parseInt(value.substring(5, 7), 16));
			JFaceResources.getColorRegistry().put(value, rgb);
			color = JFaceResources.getColorRegistry().get(value);
		}
		return color;
	}

	/**
	 * Parse an arc.<br>
	 * @param attributes Set of attributes attached to the current element
	 * @throws ModelException If something went wrong
	 */
	private void startArc(Attributes attributes) throws ModelException {
		IGraph graph = (IGraph) stack.peek();

		// Fetch information about the arc
		int id = Integer.parseInt(attributes.getValue(ModelConstants.ARC_ID_MARKUP));
		int startid = Integer.parseInt(attributes.getValue(ModelConstants.ARC_STARTID_MARKUP));
		int endid = Integer.parseInt(attributes.getValue(ModelConstants.ARC_ENDID_MARKUP));
		boolean curved = Boolean.parseBoolean(attributes.getValue(ModelConstants.ARC_CURVED_MARKUP));
		String arcFormalismName = attributes.getValue(ModelConstants.ARC_TYPE_MARKUP);

		// Build the arc
		IArc arc = graph.createArc((IArcFormalism) formalismCache.get(arcFormalismName),
				graph.getNode(ids.get(startid)),
				graph.getNode(ids.get(endid)));
		ids.put(id, arc.getId());

		// Arc color
		try {
			Color color = parseColor(attributes.getValue(ModelConstants.ARC_COLOR_MARKUP));
			arc.getGraphicInfo().setColor(color);
		} catch (NumberFormatException e) {
			LOGGER.fine("Color attribute does not exist or is invalid"); //$NON-NLS-1$
		}

		// Curved status
		arc.getGraphicInfo().setCurve(curved);

		stack.push(arc);
	}

	/**
	 * Parse an inflex point (bend point).<br>
	 * <i>The stack should contain an {@link IArc}</i>
	 * @param attributes Set of attributes attached to the current element
	 */
	private void startInflexPoint(Attributes attributes) {
		IArc arc = (IArc) stack.peek();
		int x = Integer.parseInt(attributes.getValue(ModelConstants.PI_X_MARKUP));
		int y = Integer.parseInt(attributes.getValue(ModelConstants.PI_Y_MARKUP));
		arc.addInflexPoint(new Point(x, y));
	}

	/**
	 * Parse an attribute.<br>
	 * The stack should contain the {@link IElement} to which this attribute will be attached to
	 * @param name Attribute name
	 * @param attributes Set of attributes attached to the current element
	 */
	private void startAttribute(String name, Attributes attributes) {
		Object o = stack.peek();
		IAttribute attribute = null;
		if (o instanceof IElement) {
			IElement element = (IElement) o;
			try {
				attribute = graph.createAttribute(element, element.getElemFormalism(), name);
				element.putAttribute(name, attribute);
			} catch (ModelException e) {
				String message = "Attribute with name \"" + name + "\" found but no such attribute exists in formalism. File is malformed.";  //$NON-NLS-1$//$NON-NLS-2$
				LOGGER.severe(message);
				attribute = null;
			}
		} else if (o instanceof IAttribute) {
			IAttribute attr = (IAttribute) o;
			try {
				attribute = graph.createAttribute(attr.getReference(), attr, (IAttributeFormalism) attr.getAttributeFormalism(), name);
				attr.addAttribute(attribute);
			} catch (ModelException e) {
				String message = "Attribute with name \"" + name + "\" found but no such attribute exists in formalism. File is malformed.";  //$NON-NLS-1$//$NON-NLS-2$
				LOGGER.severe(message);
				attribute = null;
			}
		}

		int x = Integer.parseInt(attributes.getValue(ModelConstants.ATTRIBUTE_X_MARKUP));
		int y = Integer.parseInt(attributes.getValue(ModelConstants.ATTRIBUTE_Y_MARKUP));
		Point location = new Point(x, y);

		stack.push(location);
		stack.push(attribute);
	}

	/** When the parse is done, the graph is set */
	private void endModel() {
		this.graph = (IGraph) stack.pop();
	}

	/** End of a node */
	private void endNode() {
		stack.pop();
	}

	/** End of an arc */
	private void endArc() {
		stack.pop();
	}

	/** End of an inflex point */
	private void endInflexPoint() {
		// Nothing to do
	}

	/** End of a sticky note */
	private void endStickyNote() {
		stack.pop();
	}

	/** End of an attribute (assigned its value) */
	private void endAttribute() {
		IAttribute attribute = (IAttribute) stack.pop();
		Point location = (Point) stack.pop();
		String value = data.toString();
		if (attribute != null) {
			if (attribute.getAttributes().isEmpty()) {
				attribute.setValue(value);
			} else {
				attribute.initialiseValue();
			}
			attribute.getGraphicInfo().setLocation(location);
		}
	}

	/** End of a sticky note value, assign it to the top stack sticky note */
	private void endValue() {
		IStickyNote note = (IStickyNote) stack.peek();
		String value = data.toString();
		note.setLabelContents(value);
	}

	/**
	 * @return The graph
	 */
	public final IGraph getGraph() {
		return graph;
	}
}
