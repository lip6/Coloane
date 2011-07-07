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

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.swt.graphics.Color;

/**
 * Utility class that help to write a model to an XML file.
 *
 * @author Jean-Baptiste Voron
 */
public final class ModelWriter implements IModelHandler {
	/**
	 * Utility class, only static methods
	 */
	private ModelWriter() {	}

	/**
	 * @param type name of the markup
	 * @return xml markup as string with a new line
	 */
	private static String printCloseMarkup(String type) {
		return printCloseMarkup(type, true);
	}

	/**
	 * @param type name of the markup
	 * @param newLine if true, add a new line at the end
	 * @return xml markup
	 */
	private static String printOpenMarkup(String type, boolean newLine) {
		if (newLine) { return "<" + type + ">\n"; } //$NON-NLS-1$ //$NON-NLS-2$
		return "<" + type + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param type name of the markup
	 * @param newLine if true, add a new line at the end
	 * @return xml markup
	 */
	private static String printCloseMarkup(String type, boolean newLine) {
		if (newLine) { return "</" + type + ">\n"; } //$NON-NLS-1$ //$NON-NLS-2$
		return "</" + type + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Produce a string that contains the entire model
	 * @param graph The graph model object
	 * @return The entire model description
	 */
	public static String translateToXML(IGraph graph) {

		// Headers
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Formalism
		line.append("<" + MODEL_MARKUP + " xmlns='http://gml.lip6.fr/model'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" " + MODEL_FORMALISM_MARKUP + "='").append(graph.getFormalism().getName()).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		// Graph attributes
		line.append(translateAttributesToXML(graph));

		// Nodes
		line.append(translateNodesToXML(graph));

		// Arcs
		line.append(translateArcsToXML(graph));

		// Sticky notes
		line.append(translateStickyNotesToXML(graph));

		line.append("</model>"); //$NON-NLS-1$
		return line.toString();
	}

	/**
	 * Convert a Color {@link Color} into a String of type "#RGB"
	 * @param color The SWT Color object
	 * @return A string that can be dumped into a XML representation
	 */
	private static String color2String(Color color) {
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());
		if (red.length() == 1) {
			red = "0" + red; //$NON-NLS-1$
		}
		if (green.length() == 1) {
			green = "0" + green; //$NON-NLS-1$
		}
		if (blue.length() == 1) {
			blue = "0" + blue; //$NON-NLS-1$
		}
		return "#" + red + green + blue; //$NON-NLS-1$
	}

	/**
	 * Translate a node
	 * @param graph The graph to translate
	 * @return A string that describes all nodes of the graph
	 */
	private static String translateNodesToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// For each node
		for (INode node : graph.getNodes()) {

			sb.append("<" + NODE_MARKUP + " " + NODE_TYPE_MARKUP + "='").append(node.getNodeFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			sb.append(" " + NODE_ID_MARKUP + " ='").append(node.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + NODE_X_MARKUP + "='").append(node.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + NODE_Y_MARKUP + "='").append(node.getGraphicInfo().getLocation().y).append("'>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + NODE_SCALE_MARKUP + "='").append(node.getGraphicInfo().getScale()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + NODE_INTERFACE_MARKUP + "='").append(node.isInterface()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (node.getNodeLink() != null) {
				sb.append(" " + NODE_LINK_MARKUP + "='").append(node.getNodeLink()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			//sb.append(" " + NODE_ALTERNATE_MARKUP + "='").append(node.getGraphicInfo().getGdIndex()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + NODE_FOREGROUND_MARKUP + "='").append(color2String(node.getGraphicInfo().getForeground())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + NODE_BACKGROUND_MARKUP + "='").append(color2String(node.getGraphicInfo().getBackground())).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			// Translate attributes
			sb.append(translateAttributesToXML(node));

			// End of the node
			sb.append(printCloseMarkup(NODE_MARKUP));
		}
		return sb.toString();
	}

	/**
	 * Translate a sticky note
	 * @param graph The graph to translate
	 * @return A string that describes all stocky notes
	 */
	private static String translateStickyNotesToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// For each sticky note
		for (IStickyNote note : ((GraphModel) graph).getStickyNotes()) {

			sb.append("<" + STICKY_MARKUP); //$NON-NLS-1$
			sb.append(" " + STICKY_X_MARKUP + "='").append(note.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + STICKY_Y_MARKUP + "='").append(note.getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + STICKY_WIDTH_MARKUP + "='").append(note.getSize().width).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + STICKY_HEIGHT_MARKUP + "='").append(note.getSize().height).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			// Sticky note value
			sb.append(printOpenMarkup(STICKY_VALUE_MARKUP, false)).append(format(note.getLabelContents())).append(printCloseMarkup(STICKY_VALUE_MARKUP));

			// Links
			for (ILink link : note.getLinks()) {
				if (link.getElement() instanceof IElement) {
					int linkId = ((IElement) link.getElement()).getId();
					sb.append("<" + LINK_MARKUP + " " + LINK_REFERENCE_MARKUP + "='").append(linkId).append("' />\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
			}

			// End of the note
			sb.append(printCloseMarkup(STICKY_MARKUP));
		}
		return sb.toString();
	}

	/**
	 * Translate arcs
	 * @param graph The graph to translate
	 * @return A string that describes all graph arcs
	 */
	private static String translateArcsToXML(IGraph graph) {
		StringBuilder sb = new StringBuilder();

		// For each arc
		for (IArc arc : graph.getArcs()) {

			sb.append("<" + ARC_MARKUP + " " + ARC_TYPE_MARKUP + "='").append(arc.getArcFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			sb.append(" " + ARC_ID_MARKUP + "='").append(arc.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + ARC_STARTID_MARKUP + "='").append(arc.getSource().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + ARC_ENDID_MARKUP + "='").append(arc.getTarget().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + ARC_COLOR_MARKUP + "='").append(color2String(arc.getGraphicInfo().getColor())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//sb.append(" " + ARC_CURVED_MARKUP + "='").append(arc.getGraphicInfo().getCurve()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(">\n"); //$NON-NLS-1$

			// Inflex points
			sb.append(translateInflexToXML(arc));

			// Arc attributes
			sb.append(translateAttributesToXML(arc));

			// End of the arc
			sb.append("</" + ARC_MARKUP + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return sb.toString();
	}

	/**
	 * Translate inflex points
	 * @param arc The arc to translate
	 * @return A string that describe all arc inflex points
	 */
	private static String translateInflexToXML(IArc arc) {
		StringBuilder sb = new StringBuilder();

		// For each inflex point
		for (Bendpoint inflex : arc.getInflexPoints()) {
			sb.append("<" + PI_MARKUP + ""); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" " + PI_X_MARKUP + "='").append(inflex.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + PI_Y_MARKUP + "='").append(inflex.getLocation().y).append("'/>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return sb.toString();
	}

	/**
	 * Translate the attributes of an element
	 * @param elt The element
	 * @return A string that describes all node attributes
	 */
	private static String translateAttributesToXML(IElement elt) {
		StringBuilder sb = new StringBuilder();

		// For each attribute
		for (IAttribute att : elt.getAttributes()) {
			sb.append(translateSubAttributeToXML(att));
		}

		return sb.toString();
	}
	
	/**
	 * Translate an attribute
	 * @param att The attribute
	 * @return A string that describes the attribute
	 */
	private static String translateSubAttributeToXML(IAttribute att) {
		StringBuilder sb = new StringBuilder();

		// Do not take into account empty attributes
		if (!(att.getValue().equals("") && att.getAttributes().isEmpty())) { //$NON-NLS-1$
			String balise = att.getName();
			sb.append("<" + ATTRIBUTE_MARKUP + " " + ATTRIBUTE_NAME_MARKUP + "='").append(balise).append("'");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			sb.append(" " + ATTRIBUTE_X_MARKUP + "='").append(att.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" " + ATTRIBUTE_Y_MARKUP + "='").append(att.getGraphicInfo().getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(">"); //$NON-NLS-1$

			if (att.isLeaf()) {
				sb.append(format(att.getValue()));
			} else {
				for (IAttribute a : att.getAttributes()) {
					sb.append(translateSubAttributeToXML(a));
				}
			}

			sb.append(printCloseMarkup(ATTRIBUTE_MARKUP));
		}

		return sb.toString();
	}

	/**
	 * Deals with special characters (protect)
	 * @param txt The text to protect
	 * @return The protected text
	 */
	private static String format(String txt) {
		String protectedTxt = txt;
		protectedTxt = protectedTxt.replaceAll("&", "&amp;"); //$NON-NLS-1$ //$NON-NLS-2$
		protectedTxt = protectedTxt.replaceAll("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		protectedTxt = protectedTxt.replaceAll(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$
		return protectedTxt;
	}

	/**
	 * Empty model description
	 * @param formalism formalism used for this empty model
	 * @return A string that describes an empty model
	 */
	public static String createDefault(IFormalism formalism) {
		// L'entete XML
		StringBuilder line = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$

		// Headers
		line.append("<" + MODEL_MARKUP + " xmlns='http://gml.lip6.fr/model'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" " + MODEL_FORMALISM_MARKUP + "='").append(formalism.getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		line.append(">\n"); //$NON-NLS-1$

		// Nodes

		// Arcs

		line.append(printCloseMarkup(MODEL_MARKUP));

		return line.toString();
	}
}
