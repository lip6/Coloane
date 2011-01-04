package fr.lip6.move.coloane.core.formalisms.its;

import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.extensions.IExample;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class CircularEmptyModel implements IExample {

	public IGraph buildModel(IFormalism formalism) throws PluginException {
		IGraph graph = new GraphModelFactory().createGraph(formalism);
		try {
			INode node = graph.createNode((INodeFormalism) formalism.getRootGraph().getElementFormalism("instance"));
			node.getGraphicInfo().setLocation(new Point(100, 100));
			node.getAttribute("name").setValue("current");
			node.getAttribute("type").setValue("Type");
			
			node = graph.createNode((INodeFormalism) formalism.getRootGraph().getElementFormalism("instance"));
			node.getGraphicInfo().setLocation(new Point(200, 100));
			node.getAttribute("name").setValue("successor");
			node.getAttribute("type").setValue("Type");
			
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return graph;
	}

}
