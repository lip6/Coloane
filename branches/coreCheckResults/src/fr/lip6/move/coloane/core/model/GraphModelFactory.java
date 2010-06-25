package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.AbsoluteBendpoint;

/**
 * Classe permettant de construire un nouveau IGraph.
 */
public class GraphModelFactory {

	/**
	 * @param formalismName nom du formalisme.
	 * @return nouvelle instance d'un IGraph.
	 */
	public final IGraph createGraph(String formalismName) {
		return new GraphModel(formalismName);
	}

	/**
	 * Copies an IGraph instance, i.e. produce a new IGraph with the same nodes, arcs, attributes etc.
	 * Note this copy preserves identifiers of the original graph, although the two graphs do not share
	 * memory in any way.
	 * @param original the graph we want to make a copy of
	 * @return a copy
	 */
	public final IGraph copyGraph(IGraph original) {
		IGraph copy = createGraph(original.getFormalism().getName());

		try {
			// copy graph attributes
			for (IAttribute att : original.getAttributes()) {
				IAttribute attCopy = copy.getAttribute(att.getName());
				copyAttribute(att, attCopy);
			}

			// copy nodes
			for (INode node : original.getNodes()) {
				INode nodeCopy = copy.createNode(node.getNodeFormalism().getName(), node.getId());
				copyNode(node, nodeCopy);
			}

			// copy arcs
			for (IArc arc : original.getArcs()) {
				IArc arcCopy = copy.createArc(arc.getArcFormalism().getName(),
											  copy.getNode(arc.getSource().getId()),
											  copy.getNode(arc.getTarget().getId()),
											  arc.getId());
				copyArc(arc, arcCopy);
			}

		} catch (ModelException e) {
			// Should never happen, since we are copying a graph:
			// Model exception is raised if the Formalism does not know the provided element
			// which cannot happen here.
			e.printStackTrace();
		}
		return copy;
	}

	/**
	 * Copy an arc, its attributes and graphical description elements.
	 * @param srcArc the source arc
	 * @param destArc the destination arc.
	 */
	private void copyArc(IArc srcArc, IArc destArc) {
		for (IAttribute att : srcArc.getAttributes()) {
			IAttribute attCopy = destArc.getAttribute(att.getName());
			copyAttribute(att, attCopy);
		}
		destArc.getGraphicInfo().setColor(srcArc.getGraphicInfo().getColor());
		destArc.getGraphicInfo().setCurve(srcArc.getGraphicInfo().getCurve());
		for (AbsoluteBendpoint inflex : srcArc.getInflexPoints()) {
			destArc.addInflexPoint(inflex);
		}
	}
	
	/**
	 * Copy a Node, its attributes and its graphical description.
	 * @param src the source node to copy from
	 * @param dest the destination node to copy into
	 */
	private void copyNode(INode src, INode dest) {
		for (IAttribute att : src.getAttributes()) {
			IAttribute attCopy = dest.getAttribute(att.getName());
			copyAttribute(att, attCopy);
		}
		INodeGraphicInfo gi = src.getGraphicInfo();
		INodeGraphicInfo giCopy = dest.getGraphicInfo();
		copyNodeGraphicInfo(gi, giCopy);
	}
	
	/** 
	 * Copy a node's graphic info
	 * @param srcGi the source
	 * @param destGi the destination
	 */
	private void copyNodeGraphicInfo(INodeGraphicInfo srcGi,
			INodeGraphicInfo destGi) {
		destGi.setBackground(srcGi.getBackground());
		destGi.setForeground(srcGi.getForeground());
		destGi.setLocation(srcGi.getLocation());
		destGi.setScale(srcGi.getScale());
	}

	/** 
	 * Copy an attribute and its graphical description.
	 * @param src the source attribute
	 * @param dest the destination attribute
	 */
	private void copyAttribute(IAttribute src, IAttribute dest) {
		dest.setValue(src.getValue());
		IAttributeGraphicInfo srcAgi = src.getGraphicInfo();
		IAttributeGraphicInfo destAgi = dest.getGraphicInfo();
		copyAttributeGraphicInfo(srcAgi, destAgi);
	}

	/**
	 * copy an attribute's graphical info.
	 * @param srcAgi the source
	 * @param destAgi the destination
	 */
	private void copyAttributeGraphicInfo(IAttributeGraphicInfo srcAgi,
			IAttributeGraphicInfo destAgi) {
		destAgi.setBackground(srcAgi.getBackground());
		destAgi.setForeground(srcAgi.getForeground());
		destAgi.setLocation(srcAgi.getLocation());
	}
}
