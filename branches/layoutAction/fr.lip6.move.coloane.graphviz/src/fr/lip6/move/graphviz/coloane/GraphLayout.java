package fr.lip6.move.graphviz.coloane;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Point;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.graphviz.GraphViz;

/**
 * A utility class that offers "Layout" that modifies in place the given graph.
 * Graphviz settings (preferences) are used.
 * 
 * @author Yann
 *
 */
public final class GraphLayout {
	
	/** 
	 * Hide constructor : dunctionality is all static.
	 */
	private GraphLayout() { }

	/**
	 * The main user function : apply dot layout to the provided Graph instance.
	 * @param graph the graph to be updated with new positions.
	 */
	public static void layout(IGraph graph) {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph G {\n");
		for (INode node : graph.getNodes()) {
			// produce one line
			sb.append("    " + getDotID(node) + " ;\n");
		}
		for (IArc arc : graph.getArcs()) {
			// one line per arc
			sb.append(getDotID(arc.getSource()) + " -> " + getDotID(arc.getTarget())
					+ "[label=ID" + arc.getId() + " ] ;\n");
		}
		sb.append("}");
		System.err.println(sb.toString());
		try {
			InputStream dotOutput = GraphViz.generate(new ByteArrayInputStream(sb.toString().getBytes()),
								"plain", // format to basic annotated positions
								new Point(20, 20));
			DotParser.parseGraphPositions(dotOutput, graph);

		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Convert an integer node ID to a string passed to dot as object ID
	 * e.g. 12 -> "ID12"
	 * @param node the node
	 * @return the string ID
	 */
	private static String getDotID(INode node) {
		return "ID" + node.getId();
	}
	
}
