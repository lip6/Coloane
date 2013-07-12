package fr.lip6.move.coloane.extensions.exporttogrml.automaton;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.extensions.exporttogrml.IGrMLExport;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class AutomatonExport implements IGrMLExport {

	private static final String AUTOMATON_URL = "https://forge.cosyverif.org/svn/formalisms/trunk/automaton.fml";
	private static final String GRML_NAMESPACE = "http://cosyverif.org/ns/model";

	// Modgraph: Used to construct a unique id for every node. Defines the
	// maximal number of automaton models used when
	// invoking the tool (4 allows for up to 997 automata)
	private static final int TOTAL_ID_LENGTH = 4;

	public void export(IGraph graph, Writer writer, String filePath,
			IProgressMonitor monitor) throws ExtensionException {
		try {

			String modelId = new String("");
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<model formalismUrl=\"" + AUTOMATON_URL + "\"");
			writer.write(" xmlns=\"" + GRML_NAMESPACE + "\">\n");
			writer.write("<attribute name=\"name\">" + graph.getAttribute("uniqueId").getValue() + "</attribute>\n");

			for (IAttribute attribute : graph.getAttributes()) {
				if (attribute.getName().equals("uniqueId"))
					modelId = attribute.getValue();
				else
					writer.write(exportAttribute(attribute));
			}

			for (INode node : graph.getNodes()) {
				writer.write(exportNode(node, modelId));
			}
			for (IArc arc : graph.getArcs()) {
				writer.write(exportArc(arc, modelId));
			}
			writer.write("</model>\n");

		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}

	private String exportNode(INode node, String modelId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder typeSb = new StringBuilder();
		String id = ConstructUniqueId(node.getId(), modelId);

		sb.append("<node id=\"").append(id).append("\"");

		sb.append(" nodeType=\"").append(node.getNodeFormalism().getName())
				.append("\"");
		sb.append(" x=\"").append(node.getGraphicInfo().getLocation().x())
				.append("\"");
		sb.append(" y=\"").append(node.getGraphicInfo().getLocation().y())
				.append("\">\n");
		for (IAttribute attribute : node.getAttributes()) {
			if (attribute.getName().equals("initialState")
					|| attribute.getName().equals("finalState")) {
				if (attribute.getValue().equals("1"))
					typeSb.append(exportAttribute(attribute));
			} else
				sb.append(exportAttribute(attribute));
		}

		sb.append("<attribute name=\"type\">");
		sb.append(typeSb);
		sb.append("</attribute>\n");

		sb.append("</node>\n");
		return sb.toString();
	}

	/**
	 * Constructs a unique identifier for every node by concatenating the model
	 * uniqueId attribute and the Coloane node id. (Modgraph requires that nodes
	 * from different models have different identifiers)
	 * 
	 * @param nodeId
	 * @param modelId
	 * @return
	 */
	private String ConstructUniqueId(int nodeId, String modelId) {
		int idLength = String.valueOf(nodeId).length();
		StringBuilder sb = new StringBuilder();
		sb.append(modelId);
		for (int i = 0; i < (TOTAL_ID_LENGTH - idLength); i++)
			sb.append("0");
		sb.append(nodeId);
		return sb.toString();
	}

	private String exportArc(IArc arc, String modelId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder tempSb = new StringBuilder();
		StringBuilder shared = new StringBuilder();

		String sourceId = ConstructUniqueId(arc.getSource().getId(), modelId);
		String targetId = ConstructUniqueId(arc.getTarget().getId(), modelId);

		sb.append("<arc id=\"").append(arc.getId()).append("\"");
		sb.append(" source=\"").append(sourceId).append("\"");
		sb.append(" target=\"").append(targetId).append("\"");
		sb.append(" arcType=\"").append(arc.getArcFormalism().getName())
				.append("\">\n");

		sb.append(tempSb.toString());

		for (IAttribute attribute : arc.getAttributes()) {
			if (attribute.getName().equals("label")) {
				sb.append("<attribute name=\"").append(attribute.getName())
						.append("\"");
				sb.append(" x=\"")
						.append(attribute.getGraphicInfo().getLocation().x())
						.append("\"");
				sb.append(" y=\"")
						.append(attribute.getGraphicInfo().getLocation().y())
						.append("\"");
				sb.append(" value=\"").append(attribute.getValue())
						.append("\">");
				sb.append("</attribute>\n");

			} else {
				if (attribute.getName().equals("shared")) {

					shared.append("<attribute name=\"")
							.append(attribute.getName()).append("\"");
					shared.append(" x=\"")
							.append(attribute.getGraphicInfo().getLocation()
									.x()).append("\"");
					shared.append(" y=\"")
							.append(attribute.getGraphicInfo().getLocation()
									.y()).append("\"");
					shared.append(" value=\"").append(attribute.getValue())
							.append("\">");
					shared.append("</attribute>\n");
				}
			}
		}

		sb.append(shared.toString());
		sb.append("</arc>\n");
		return sb.toString();
	}

	private String exportAttribute(IAttribute attribute) {
		StringBuilder sb = new StringBuilder();
		sb.append("<attribute name=\"").append(attribute.getName())
				.append("\"");
		sb.append(" x=\"").append(attribute.getGraphicInfo().getLocation().x())
				.append("\"");
		sb.append(" y=\"").append(attribute.getGraphicInfo().getLocation().y())
				.append("\">");
		sb.append(attribute.getValue());
		sb.append("</attribute>\n");
		return sb.toString();
	}
}
