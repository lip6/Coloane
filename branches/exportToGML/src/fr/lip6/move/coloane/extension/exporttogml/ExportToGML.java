package fr.lip6.move.coloane.extension.exporttogml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ExportToGML implements IExportTo {
	
	/** (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExportTo#export(fr.lip6.move.coloane.interfaces.model.IGraph, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void export(IGraph graph, String filePath, IProgressMonitor monitor)
			throws ColoaneException {
		
		Writer out = null;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			exportGraph(graph, out, monitor);
		} catch (IOException e) {
			throw new ColoaneException(e.getMessage());
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new ColoaneException(e.getMessage());
			}
		}
	}

	/**
	 * Export a graph object
	 *
	 * @param graph graph to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @throws IOException if the writer throw an exception
	 */
	private void exportGraph(IGraph graph, Writer out, IProgressMonitor monitor) throws IOException {
		String gap = "  ";
		
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
		out.write("<model formalismUrl=\"pt-net.fml\"\n");
		out.write("    xmlns=\"http://gml.lip6.fr/model\">\n");
    	
		// Export model attributes
		for(IAttribute attr : graph.getAttributes()) {
			exportAttribute(attr, out, monitor, gap);
		}
    	out.write("\n");
    	
    	//Export nodes
    	for(INode node : graph.getNodes()) {
    		exportNode(node, out, monitor, gap);
    	}
    	out.write("\n");
    	
    	//Export Arcs
    	for(IArc arc : graph.getArcs()) {
    		exportArc(arc, out, monitor, gap);
    	}
    	out.write("\n");

    	out.write("</model>");
	}

	/**
	 * Export an attribute object
	 *
	 * @param attr attribut to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @throws IOException if the writer throw an exception
	 */
	private void exportAttribute(IAttribute attr, Writer out, IProgressMonitor monitor, String gap) throws IOException {
		out.write(gap + "<attribute name=\"" + attr.getName() + "\">" + attr.getValue() + "</attribute>\n");
	}

	/**
	 * Export a node object
	 *
	 * @param node node to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @throws IOException if the writer throw an exception
	 */
	private void exportNode(INode node, Writer out, IProgressMonitor monitor, String gap) throws IOException {
		out.write(gap + "<node id=\"" + node.getNodeFormalism().getName() + node.getId() + "\" nodeType=\"" + node.getNodeFormalism().getName() + "\">\n");
		for(IAttribute attr : node.getAttributes()) {
			exportAttribute(attr, out, monitor, gap + "  ");
		}
		out.write(gap + "</node>\n");
	}

	/**
	 * Export an arc object
	 *
	 * @param arc arc to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @throws IOException if the writer throw an exception
	 */
	private void exportArc(IArc arc, Writer out, IProgressMonitor monitor, String gap) throws IOException {
		INode source = arc.getSource();
		INode target = arc.getTarget();
		out.write(gap + "<arc id=\"" + arc.getArcFormalism().getName() + arc.getId() + "\" arcType=\"" + arc.getArcFormalism().getName() + "\" "
				+ "source=\"" + source.getNodeFormalism().getName() + source.getId() + "\" "
				+ "target=\"" + target.getNodeFormalism().getName() + target.getId() + "\">\n");
		for(IAttribute attr : arc.getAttributes()) {
			exportAttribute(attr, out, monitor, gap + "  ");
		}
		out.write(gap + "</arc>\n");
	}
}
