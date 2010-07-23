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
	private ModelWriter() {	}
	
	private static String printOpenMarkup(String type) {
		return printOpenMarkup(type, true);
	}

	private static String printCloseMarkup(String type) {
		return printCloseMarkup(type, true);
	}

	private static String printOpenMarkup(String type, boolean newLine) {
		if (newLine) { return "<" + type + ">\n"; } //$NON-NLS-1$ //$NON-NLS-2$
		return "<" + type + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

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
		line.append("<"+ MODEL_MARKUP + " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		line.append(" "+ MODEL_FORMALISM_MARKUP + "='").append(graph.getFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		line.append(" xposition='0' yposition='0'>\n"); //$NON-NLS-1$

		// Graph attributes
		line.append(translateAttributesToXML(graph));

		// Nodes
		line.append(printOpenMarkup(NODES_LIST_MARKUP));
		line.append(translateNodesToXML(graph));
		line.append(printCloseMarkup(NODES_LIST_MARKUP));

		// Arcs
		line.append(printOpenMarkup(ARCS_LIST_MARKUP));
		line.append(translateArcsToXML(graph));
		line.append(printCloseMarkup(ARCS_LIST_MARKUP));

		// Sticky notes
		line.append(printOpenMarkup(STICKYS_LIST_MARKUP));
		line.append(translateStickyNotesToXML(graph));
		line.append(printCloseMarkup(STICKYS_LIST_MARKUP));

		line.append("</model>"); //$NON-NLS-1$
		return line.toString();
	}

	/**
	 * Convert a Color {@link Color} into a String of type "#RGB"
	 * @param color The SWT Color object
	 * @return A string that can be dump into a XML representation
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
			sb.append(" "+ NODE_ID_MARKUP + " ='").append(node.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_X_MARKUP + "='").append(node.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_Y_MARKUP + "='").append(node.getGraphicInfo().getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_SCALE_MARKUP + "='").append(node.getGraphicInfo().getScale()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_INTERFACE_MARKUP + "='").append(node.isInterface()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (node.getNodeLink() != null) {
				sb.append(" "+ NODE_LINK_MARKUP + "='").append(node.getNodeLink()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			sb.append(" "+ NODE_ALTERNATE_MARKUP + "='").append(node.getGraphicInfo().getGdIndex()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_FOREGROUND_MARKUP + "='").append(color2String(node.getGraphicInfo().getForeground())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ NODE_BACKGROUND_MARKUP + "='").append(color2String(node.getGraphicInfo().getBackground())).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

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

			sb.append("<"+ STICKY_MARKUP); //$NON-NLS-1$
			sb.append(" "+ STICKY_X_MARKUP + "='").append(note.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ STICKY_Y_MARKUP + "='").append(note.getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ STICKY_WIDTH_MARKUP + "='").append(note.getSize().width).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ STICKY_HEIGHT_MARKUP + "='").append(note.getSize().height).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			// Sticky note value
			sb.append(printOpenMarkup(STICKY_VALUE_MARKUP,false)).append(format(note.getLabelContents())).append(printCloseMarkup(STICKY_VALUE_MARKUP));

			// Links
			for (ILink link : note.getLinks()) {
				if (link.getElement() instanceof IElement) {
					int linkId = ((IElement) link.getElement()).getId();
					sb.append("<"+ LINK_MARKUP + " "+ LINK_REFERENCE_MARKUP + "='").append(linkId).append("' />\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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

			sb.append("<"+ ARC_MARKUP + " "+ ARC_TYPE_MARKUP + "='").append(arc.getArcFormalism().getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			sb.append(" "+ ARC_ID_MARKUP + "='").append(arc.getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ ARC_STARTID_MARKUP + "='").append(arc.getSource().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ ARC_ENDID_MARKUP + "='").append(arc.getTarget().getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ ARC_COLOR_MARKUP + "='").append(color2String(arc.getGraphicInfo().getColor())).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ ARC_CURVED_MARKUP + "='").append(arc.getGraphicInfo().getCurve()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(">\n"); //$NON-NLS-1$

			// Inflex points
			sb.append(translateInflexToXML(arc));

			// Arc attributes 
			sb.append(translateAttributesToXML(arc));

			// End of the arc
			sb.append("</"+ ARC_MARKUP + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return sb.toString();
	}

	/**
	 * Translate infelx points
	 * @param arc The arc to translate
	 * @return A string that describe all arc inflex points
	 */
	private static String translateInflexToXML(IArc arc) {
		StringBuilder sb = new StringBuilder();

		// For each inflex point
		for (Bendpoint inflex : arc.getInflexPoints()) {
			sb.append("<"+ PI_MARKUP + ""); //$NON-NLS-1$ //$NON-NLS-2$
			sb.append(" "+ PI_X_MARKUP + "='").append(inflex.getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append(" "+ PI_Y_MARKUP + "='").append(inflex.getLocation().y).append("'/>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		sb.append(printOpenMarkup(ATTRIBUTES_LIST_MARKUP));

		// For each attribute
		for (IAttribute att : elt.getAttributes()) {

			// Do not take into account empty attributes
			if (!att.getValue().equals("")) { //$NON-NLS-1$
				String balise = att.getName();
				sb.append("<"+ ATTRIBUTE_MARKUP + " "+ ATTRIBUTE_NAME_MARKUP + "='").append(balise).append("'");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				sb.append(" "+ ATTRIBUTE_X_MARKUP + "='").append(att.getGraphicInfo().getLocation().x).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append(" "+ ATTRIBUTE_Y_MARKUP + "='").append(att.getGraphicInfo().getLocation().y).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append(">"); //$NON-NLS-1$

				sb.append(format(att.getValue()));

				sb.append(printCloseMarkup(ATTRIBUTE_MARKUP));
			}
		}

		sb.append(printCloseMarkup(ATTRIBUTES_LIST_MARKUP));

		return sb.toString();
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
		line.append("<"+ MODEL_MARKUP + " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"); //$NON-NLS-1$ //$NON-NLS-2$
		line.append(" xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'"); //$NON-NLS-1$
		line.append(" "+ MODEL_FORMALISM_MARKUP + "='").append(formalism.getName()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
