package fr.lip6.move.coloane.core.formalisms.its;

import fr.lip6.move.coloane.core.extensions.IExample;
import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class CircularEmptyModel implements IExample {

	@Override
	public IGraph buildModel() {
		IGraph graph = new GraphModelFactory().createGraph("CircularComposite");
		try {
			INode node = graph.createNode("instance");
			node.getAttribute("name").setValue("current");
			node.getAttribute("type").setValue("Type");
			
			node = graph.createNode("instance");
			node.getAttribute("name").setValue("successor");
			node.getAttribute("type").setValue("Type");
						
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return graph;
	}

}
