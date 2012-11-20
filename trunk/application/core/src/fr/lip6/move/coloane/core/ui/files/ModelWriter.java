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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

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
	private static String printOpenMarkup(String type) {
		return printOpenMarkup(type, true);
	}

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
		try {
			StringWriter writer = new StringWriter();
			translateToXML(graph, writer);
			return writer.toString();
		} catch (IOException e) {
			throw new AssertionError(e.getMessage());
		}
	}
	
	/**
	 * Put the XML representation of the model in a writer.
	 * @param graph The graph model object
	 * @param writer The writer for the XML representation
	 * @throws IOException if something goes wrong
	 */
	public static void translateToXML(IGraph graph, Writer writer) throws IOException {
		// Headers
		writer.write("<?xml version='1.0' encoding='UTF-8'?>\n"); //$NON-NLS-1$
		// Formalism
		writer.write("<" + MODEL_MARKUP + " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.write(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		writer.write(" " + MODEL_FORMALISM_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.write(graph.getFormalism().getName());
		writer.write("'"); //$NON-NLS-1$
		writer.write(" xposition='0' yposition='0'>\n"); //$NON-NLS-1$
		// Graph attributes
		translateAttributesToXML(graph, writer);
		// Nodes
		writer.write(printOpenMarkup(NODES_LIST_MARKUP));
		translateNodesToXML(graph, writer);
		writer.write(printCloseMarkup(NODES_LIST_MARKUP));
		// Arcs
		writer.write(printOpenMarkup(ARCS_LIST_MARKUP));
		translateArcsToXML(graph, writer);
		writer.write(printCloseMarkup(ARCS_LIST_MARKUP));
		// Sticky notes
		writer.write(printOpenMarkup(STICKYS_LIST_MARKUP));
		translateStickyNotesToXML(graph, writer);
		writer.write(printCloseMarkup(STICKYS_LIST_MARKUP));
		writer.write("</model>"); //$NON-NLS-1$
	}

	/**
	 * Convert a Color {@link Color} into a String of type "#RGB"
	 * @param color The SWT Color object
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void color2String(Color color, Writer writer) throws IOException {
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
		writer.write("#" + red + green + blue); //$NON-NLS-1$
	}

	/**
	 * Translate a node
	 * @param graph The graph to translate
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void translateNodesToXML(IGraph graph, Writer writer) throws IOException {
		// For each node
		for (INode node : graph.getNodes()) {
			writer.write("<" + NODE_MARKUP + " " + NODE_TYPE_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			writer.write(node.getNodeFormalism().getName());
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_ID_MARKUP + " ='");  //$NON-NLS-1$//$NON-NLS-2$
			writer.write(node.getId() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_X_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
			writer.write(node.getGraphicInfo().getLocation().x + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_Y_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(node.getGraphicInfo().getLocation().y + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_SCALE_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(node.getGraphicInfo().getScale() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_INTERFACE_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(Boolean.toString(node.isInterface()));
			writer.write("'"); //$NON-NLS-1$
			if (node.getNodeLink() != null) {
				writer.write(" " + NODE_LINK_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write(node.getNodeLink());
				writer.write("'"); //$NON-NLS-1$
			}
			writer.write(" " + NODE_ALTERNATE_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(node.getGraphicInfo().getGdIndex() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_FOREGROUND_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			color2String(node.getGraphicInfo().getForeground(), writer);
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + NODE_BACKGROUND_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			color2String(node.getGraphicInfo().getBackground(), writer);
			writer.write("'>\n"); //$NON-NLS-1$
			// Translate attributes
			translateAttributesToXML(node, writer);
			// End of the node
			writer.write(printCloseMarkup(NODE_MARKUP));
		}
	}

	/**
	 * Translate a sticky note
	 * @param graph The graph to translate
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void translateStickyNotesToXML(IGraph graph, Writer writer) throws IOException {
		// For each sticky note
		for (IStickyNote note : ((GraphModel) graph).getStickyNotes()) {
			writer.write("<" + STICKY_MARKUP); //$NON-NLS-1$
			writer.write(" " + STICKY_X_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(note.getLocation().x + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + STICKY_Y_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
			writer.write(note.getLocation().y + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + STICKY_WIDTH_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(note.getSize().width + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + STICKY_HEIGHT_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
			writer.write(note.getSize().height + ""); //$NON-NLS-1$
			writer.write("'>\n"); //$NON-NLS-1$
			// Sticky note value
			writer.write(printOpenMarkup(STICKY_VALUE_MARKUP, false));
			writer.write(format(note.getLabelContents()));
			writer.write(printCloseMarkup(STICKY_VALUE_MARKUP));
			// Links
			for (ILink link : note.getLinks()) {
				if (link.getElement() instanceof IElement) {
					int linkId = ((IElement) link.getElement()).getId();
					writer.write("<" + LINK_MARKUP + " " + LINK_REFERENCE_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
					writer.write(linkId);
					writer.write("' />\n"); //$NON-NLS-1$
				}
			}
			// End of the note
			writer.write(printCloseMarkup(STICKY_MARKUP));
		}
	}

	/**
	 * Translate an arc
	 * @param graph The graph to translate
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void translateArcsToXML(IGraph graph, Writer writer) throws IOException {
		// For each arc
		for (IArc arc : graph.getArcs()) {
			writer.write("<" + ARC_MARKUP + " " + ARC_TYPE_MARKUP + "='");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
			writer.write(arc.getArcFormalism().getName());
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + ARC_ID_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(arc.getId() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + ARC_STARTID_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(arc.getSource().getId() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + ARC_ENDID_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(arc.getTarget().getId() + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + ARC_COLOR_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
			color2String(arc.getGraphicInfo().getColor(), writer);
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + ARC_CURVED_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(Boolean.toString(arc.getGraphicInfo().getCurve()));
			writer.write("'"); //$NON-NLS-1$
			writer.write(">\n"); //$NON-NLS-1$
			// Inflex points
			translateInflexToXML(arc, writer);
			// Arc attributes
			translateAttributesToXML(arc, writer);
			// End of the arc
			writer.write("</" + ARC_MARKUP + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Translate inflexion points
	 * @param arc The arc to translate
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void translateInflexToXML(IArc arc, Writer writer) throws IOException {
		// For each inflex point
		for (Bendpoint inflex : arc.getInflexPoints()) {
			writer.write("<" + PI_MARKUP + ""); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(" " + PI_X_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
			writer.write(inflex.getLocation().x + ""); //$NON-NLS-1$
			writer.write("'"); //$NON-NLS-1$
			writer.write(" " + PI_Y_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write(inflex.getLocation().y + ""); //$NON-NLS-1$
			writer.write("'/>\n"); //$NON-NLS-1$
		}
	}

	/**
	 * Translate the attributes of an element
	 * @param elt The element
	 * @param writer The model writer
	 * @throws IOException if something goes wrong
	 */
	private static void translateAttributesToXML(IElement elt, Writer writer) throws IOException {
		writer.write(printOpenMarkup(ATTRIBUTES_LIST_MARKUP));
		// For each attribute
		for (IAttribute att : elt.getAttributes()) {
			// Do not take into account empty attributes
			if (!att.getValue().equals("")) { //$NON-NLS-1$
				String balise = att.getName();
				writer.write("<" + ATTRIBUTE_MARKUP + " " + ATTRIBUTE_NAME_MARKUP + "='");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
				writer.write(balise);
				writer.write("'");  //$NON-NLS-1$
				writer.write(" " + ATTRIBUTE_X_MARKUP + "='"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write(att.getGraphicInfo().getLocation().x + ""); //$NON-NLS-1$
				writer.write("'"); //$NON-NLS-1$
				writer.write(" " + ATTRIBUTE_Y_MARKUP + "='");  //$NON-NLS-1$//$NON-NLS-2$
				writer.write(att.getGraphicInfo().getLocation().y + ""); //$NON-NLS-1$
				writer.write("'"); //$NON-NLS-1$
				writer.write(">"); //$NON-NLS-1$
				writer.write(format(att.getValue()));
				writer.write(printCloseMarkup(ATTRIBUTE_MARKUP));
			}
		}
		writer.write(printCloseMarkup(ATTRIBUTES_LIST_MARKUP));
	}

	/**
	 * Deals with special charatacters (protect)
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
		line.append("<" + MODEL_MARKUP + " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		line.append(" " + MODEL_FORMALISM_MARKUP + "='").append(formalism.getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		line.append(">\n"); //$NON-NLS-1$

		// Nodes
		line.append(printOpenMarkup(NODES_LIST_MARKUP));
		line.append(printCloseMarkup(NODES_LIST_MARKUP));

		// Arcs
		line.append(printOpenMarkup(ARCS_LIST_MARKUP));
		line.append(printCloseMarkup(ARCS_LIST_MARKUP));

		line.append(printCloseMarkup(MODEL_MARKUP));

		return line.toString();
	}
}
