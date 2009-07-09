package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;

/**
 * Reset all attribute positions of an IElement to their default positions
 * (i.e. relative to the node or arc they describe)
 *
 * @author Yann Thierry-Mieg, based on Jean-Baptiste Voron
 */
public class ResetAttributesPositionCommand implements ICommand {
	/** ID of the attribute owner */
	private int referenceId;

	private Map<String, Point> oldPos;
	
	/**
	 * Constructor
	 * @param refId Node / Arc where to which the attribute is attached
	 */
	public ResetAttributesPositionCommand(int refId) {
		this.referenceId = refId;
		oldPos = new HashMap<String, Point>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		IElement elt = graph.getObject(referenceId);
		Point refPos;
		if (elt instanceof IArc) {
			IArc arc = (IArc) elt;
			refPos =  arc.getGraphicInfo().findMiddlePoint();
		} else {
			// Should be a node ...
			INode node = (INode) elt;
			refPos = node.getGraphicInfo().getLocation();
			// GAP = 20
			refPos.translate(20, 20);
		}
		for (IAttribute att : elt.getDrawableAttributes()) {
			// Store old position for undo
			oldPos.put(att.getName(), att.getGraphicInfo().getLocation());
			// Compute new attribute position
			// TODO : take the code from createFigure to avoid attribute overlap
			// TODO : export code from createFigure to avoid code duplication
			att.getGraphicInfo().setLocation(refPos);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void redo(IGraph graph) throws ModelException {
		this.execute(graph);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void undo(IGraph graph) throws ModelException {
		IElement elt = graph.getObject(referenceId);
		for (IAttribute att : elt.getDrawableAttributes()) {
			// Store old position for undo
			Point oldPoint = oldPos.get(att.getName());
			att.getGraphicInfo().setLocation(oldPoint);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Reset Attribute Positions of object of ID: " + referenceId;
	}
}
