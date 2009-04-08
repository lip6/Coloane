package fr.lip6.move.coloane.extensions.pnexamples;

import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.core.extensions.IExample;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class SimpleExample implements IExample {
	
	public SimpleExample() { }

	public IGraph buildModel() {
		try {
			IGraph graph = new GraphModel("Place/Transition Net");
		
			// Creation du noeud 1
			INode p1 = graph.createNode("place");
			p1.getGraphicInfo().setLocation(new Point(100,100));
			
			// Creation du noeud 2
			INode p2 = graph.createNode("place");
			p2.getGraphicInfo().setLocation(new Point(100, 160));
			
			// Creation du transition
			INode t = graph.createNode("transition");
			t.getGraphicInfo().setLocation(new Point(100, 130));

			// Creation des arcs
			graph.createArc("arc", p1, t);
			graph.createArc("arc", t, p2); 
			
			return graph;

		} catch (ModelException me){
			return null;
		}
	}
	
}
