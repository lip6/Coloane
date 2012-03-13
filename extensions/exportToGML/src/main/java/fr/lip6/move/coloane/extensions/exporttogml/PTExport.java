package fr.lip6.move.coloane.extensions.exporttogml;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;


public class PTExport implements IGMLExport {
	private static final String PTNET_URL = "http://alligator.lip6.fr/pt-net.fml";
	private static final String GML_NAMESPACE = "http://gml.lip6.fr/model";

	public void export(IGraph graph, Writer writer, String formalismURL, IProgressMonitor monitor) throws ExtensionException {
		try {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
			writer.write("<model formalismUrl=\"" + PTNET_URL + "\"");
			writer.write(" xmlns=\"" + GML_NAMESPACE + "\">\n");

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
		sb.append(" nodeType=\"").append(node.getNodeFormalism().getName()).append("\">\n");
		for (IAttribute attribute : node.getAttributes()) {
			sb.append(exportAttribute(attribute));
		}
		sb.append("</node>\n");
		return sb.toString();
	}

	private String exportArc(IArc arc) {
		StringBuilder sb = new StringBuilder();
		sb.append("<arc id=\"").append(arc.getId()).append("\"");
		sb.append(" source=\"").append(arc.getSource().getId()).append("\"");
		sb.append(" target=\"").append(arc.getTarget().getId()).append("\"");
		sb.append(" arcType=\"").append(arc.getArcFormalism().getName()).append("\">\n");
		for (IAttribute attribute : arc.getAttributes()) {
			sb.append(exportAttribute(attribute));
		}
		sb.append("</arc>\n");
		return sb.toString();
	}

	private String exportAttribute(IAttribute attribute) {
		StringBuilder sb = new StringBuilder();
		sb.append("<attribute name=\"").append(attribute.getName()).append("\">");
		sb.append(attribute.getValue());
		sb.append("</attribute>\n");
		return sb.toString();
	}
}
