package fr.lip6.move.coloane.extensions.pnexamples;

import fr.lip6.move.coloane.core.extensions.IExample;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.geometry.Point;

/**
 * Class that demonstrate how to build a simple model directly from model interfaces.
 *
 * @author Jean-Baptiste Voron
 */
public class SimpleExample implements IExample {

	/**
	 * Default constructor
	 */
	public SimpleExample() { }

	/**
	 * {@inheritDoc}
	 */
	public final IGraph buildModel() throws PluginException {
		try {
			IGraph graph = new GraphModel("Place/Transition Net");

			// Node 1 creation
			INode p1 = graph.createNode("place");
			p1.getGraphicInfo().setLocation(new Point(100, 100));

			// Node 2 creation
			INode p2 = graph.createNode("place");
			p2.getGraphicInfo().setLocation(new Point(100, 160));

			// Transition creation
			INode t = graph.createNode("transition");
			t.getGraphicInfo().setLocation(new Point(100, 130));

			// Arcs creation
			graph.createArc("arc", p1, t);
			graph.createArc("arc", t, p2);

			return graph;
		} catch (ModelException me) {
			throw new PluginException(Activator.PLUGIN_ID, me.getMessage());
		}
	}

}
