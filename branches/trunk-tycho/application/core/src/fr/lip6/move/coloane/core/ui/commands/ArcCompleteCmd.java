package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;

/**
 * Second step of the creation of an arc between two nodes.
 * This command is created when the user chooses the target of the arc.
 *
 * @see ArcCreateCmd
 * @author Jean-Baptiste Voron
 */
public class ArcCompleteCmd extends CheckableCmd {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The graph on which the arc will be created */
	private IGraph graph;

	/** Source node */
	private final INode source;

	/** Target node */
	private final INode target;

	/** The formalism that describes the arc */
	private final IArcFormalism arcFormalism;

	/** The created arc */
	private IArc arc;

	/**
	 * Create the command to build up the arc
	 * @param source Source node
	 * @param target Target node
	 * @param arcFormalism The formalism that describes the arc (its properties)
	 */
	public ArcCompleteCmd(INode source, INode target, IArcFormalism arcFormalism) {
		// Fetch the parent model from the source node... Source and target parent must be the same  
		assert(source.getParent().equals(target.getParent()));
		this.graph = (IGraph) source.getParent();
		this.source = source;
		this.target = target;
		this.arcFormalism = arcFormalism;
		
		// This new arc must be locally checked
		addCheckableElement(arc);
		// Its source and its target must be checked too
		addCheckableElement(source);
		addCheckableElement(target);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		// Is the connection authorized by the formalism ?
		if (!arcFormalism.getFormalism().isLinkAllowed(this.source, this.target, this.arcFormalism)) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Build up the arc
		try {
			this.arc = this.graph.createArc(arcFormalism, source, target);
			
			// Handle the special case where the source and the target node of the arc are the same
			if (source.equals(target)) {
				// In that case, two inflex points are created
				int x = source.getGraphicInfo().getLocation().x + (source.getGraphicInfo().getSize().width)/2;
				int y = source.getGraphicInfo().getLocation().y;
				arc.addInflexPoint(new Point(x-20,y-20));
				arc.addInflexPoint(new Point(x+20,y-20));
			}
		} catch (ModelException e) {
			LOGGER.warning("Unable to build the arc: " + e.toString()); //$NON-NLS-1$
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.addArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.deleteArc(arc);
	}
}
