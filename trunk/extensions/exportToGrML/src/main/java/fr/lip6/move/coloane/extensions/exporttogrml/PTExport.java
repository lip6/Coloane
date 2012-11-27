package fr.lip6.move.coloane.extensions.exporttogrml;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;


public class PTExport implements IGrMLExport {
	private static final String PTNET_URL = "http://formalisms.cosyverif.org/pt-net.fml";
	private static final String GRML_NAMESPACE = "http://cosyverif.org/ns/model";
	
	private static final Map<String, String> attrMap = new HashMap<String, String>();
	static {
		attrMap.put("title", "name");
		attrMap.put("version", "version");
		attrMap.put("authors", "authors");
		attrMap.put("note", "comments");
		attrMap.put("name", "name");
		attrMap.put("valuation", "valuation");
		attrMap.put("marking", "marking");
	}

	public void export(IGraph graph, Writer writer, String formalismURL, IProgressMonitor monitor) throws ExtensionException {
		try {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
			writer.write("<model formalismUrl=\"" + PTNET_URL + "\"");
			writer.write(" xmlns=\"" + GRML_NAMESPACE + "\">\n");

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
		sb.append(" nodeType=\"").append(node.getNodeFormalism().getName()).append("\"");
		sb.append(" x=\"").append(node.getGraphicInfo().getLocation().x()).append("\"");
		sb.append(" y=\"").append(node.getGraphicInfo().getLocation().y()).append("\">\n");
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
		if (!attrMap.containsKey(attribute.getName())) { return ""; }
		String name = attrMap.get(attribute.getName());
		StringBuilder sb = new StringBuilder();
		sb.append("<attribute name=\"").append(name).append("\"");
		sb.append(" x=\"").append(attribute.getGraphicInfo().getLocation().x()).append("\"");
		sb.append(" y=\"").append(attribute.getGraphicInfo().getLocation().y()).append("\">");
		sb.append(attribute.getValue());
		sb.append("</attribute>\n");
		return sb.toString();
	}
}
