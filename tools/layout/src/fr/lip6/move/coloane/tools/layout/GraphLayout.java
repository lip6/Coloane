package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.tools.graphviz.GraphViz;
import fr.lip6.move.coloane.tools.graphviz.GraphVizActivator;
import fr.lip6.move.coloane.tools.graphviz.io.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Point;

/**
 * A utility class that offers "Layout" that modifies in place the given graph.<br>
 * Graphviz settings (preferences) are used.
 * 
 * @author Yann Thierry-Mieg
 */
public final class GraphLayout {
	
	/** 
	 * Hide constructor : functionality is all static.
	 */
	private GraphLayout() { }
	
	 /**
	  * Main non GUI related function, updates a graph in place.
	  * @param graph the graph to update
	  */
	public static void doLayout(IGraph graph) {
		List<ICommand> commands = layout(graph);
		try {
			for (ICommand command : commands) {
				command.execute(graph);
			}
		} catch (ModelException e) {
			LogUtils.logWarning(GraphVizActivator.getID(), "model formalism error", e);
		}
	}

	/**
	 * The main user function : apply dot layout to the provided Graph instance.
	 * @param graph the graph to be updated with new positions.
	 * @return the list of position commands to execute
	 */
	public static List<ICommand> layout(IGraph graph) {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph G {\n");
		for (INode node : graph.getNodes()) {
			// Produce one line
			sb.append("    " + getDotID(node) + " ;\n");
		}
		for (IArc arc : graph.getArcs()) {
			// One line per arc
			sb.append(getDotID(arc.getSource()) + " -> " + getDotID(arc.getTarget()) + "[label=ID" + arc.getId() + " ] ;\n");
		}
		sb.append("}");
		System.err.println(sb.toString());
		try {
			InputStream dotOutput = GraphViz.generate(new ByteArrayInputStream(sb.toString().getBytes()),
								"plain", // format to basic annotated positions
								new Point(20, 20));
			return DotParser.parseGraphPositions(dotOutput, graph);

		// TODO: Exceptions must be handled more carefully !
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<ICommand>();
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
