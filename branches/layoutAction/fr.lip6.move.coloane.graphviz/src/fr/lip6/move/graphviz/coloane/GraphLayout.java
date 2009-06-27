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


public class GraphLayout {
	
	private static String getDotID (INode node) {
		return "ID"+node.getId();
	}
	
	public static void Layout (IGraph graph) {
		StringBuffer sb=new StringBuffer();
		sb.append("digraph G {\n");
		for (INode node : graph.getNodes()) {
			// produce one line
			sb.append("    "+ getDotID(node) + " ;\n");
		}
		for (IArc arc : graph.getArcs()) {
			// one line per arc
			sb.append(getDotID(arc.getSource()) + " -> " + getDotID(arc.getTarget()) 
					+ "[label=ID" +arc.getId() + " ] ;\n");
		}
		sb.append("}");
		System.err.println(sb.toString());
		try {
//			GraphViz.setLayoutType(GraphViz.NEATO);
			InputStream dotOutput = GraphViz.generate(new ByteArrayInputStream(sb.toString().getBytes()), 
								"plain", // format to basic annotated positions 
								new Point(20,20)); 
			DotParser.parseGraphPositions(dotOutput,graph);
			
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
