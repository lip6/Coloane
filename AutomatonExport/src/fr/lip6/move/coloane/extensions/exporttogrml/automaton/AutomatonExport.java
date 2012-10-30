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

	private static final String AUTOMATON_URL = "http://lipn.univ-paris13.fr/~lembachar/automaton.fml";
	private static final String GRML_NAMESPACE = "http://cosyverif.org/ns/model";

	public void export(IGraph graph, Writer writer, String filePath,
			IProgressMonitor monitor) throws ExtensionException {
		try {
			
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<model formalismUrl=\"" + AUTOMATON_URL + "\"");
			writer.write(" xmlns=\"" + GEML_NAMESPACE + "\">\n");
			for (IAttribute attribute : graph.getAttributes()) {
				writer.write(exportAttribute(attribute));
			}
			for (INode node : graph.getNodes()) {
				writer.write(exportNode(node));
			}
			for (IArc arc : graph.getArcs()) {
				writer.write(exportArc(arc));
			}
			writer.write("</model>\n");

		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}

	private String exportNode(INode node) {
		StringBuilder sb = new StringBuilder();
		sb.append("<node id=\"").append(node.getId()).append("\"");
		sb.append(" nodeType=\"").append(node.getNodeFormalism().getName())
				.append("\"");
		sb.append(" x=\"").append(node.getGraphicInfo().getLocation().x())
				.append("\"");
		sb.append(" y=\"").append(node.getGraphicInfo().getLocation().y())
				.append("\">\n");
		for (IAttribute attribute : node.getAttributes()) {
			sb.append(exportAttribute(attribute));
		}
		sb.append("</node>\n");
		return sb.toString();
	}

	private String exportArc(IArc arc) {
		StringBuilder sb = new StringBuilder();
		StringBuilder tempSb = new StringBuilder();

		sb.append("<arc id=\"").append(arc.getId()).append("\"");

		for (IAttribute attribute : arc.getAttributes()) {
			if (attribute.getName().equals("name"))
				sb.append(" name=\"").append(attribute.getValue()).append("\"");
			else {
				if (attribute.getName().equals("shared")
						&& attribute.getValue().equals("1"))
					tempSb.append(exportAttribute(attribute));
			}
		}

		sb.append(" source=\"").append(arc.getSource().getId()).append("\"");
		sb.append(" target=\"").append(arc.getTarget().getId()).append("\"");
		sb.append(" arcType=\"").append(arc.getArcFormalism().getName())
				.append("\">\n");

		sb.append(tempSb.toString());
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
