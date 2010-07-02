package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Second step for the link creation between two nodes !<br>
 * This command is created when the user has designated the <b>target object</b>.
 * The special case source = target must be handled here !
 * @see ArcCreateCmd
 */
public class ArcCompleteCmd extends Command {
	/** Log factory */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The current graph */
	private IGraph graph;

	/** Arc Source */
	private final INode source;

	/** Arc Target */
	private final INode target;

	/** Arc description (formalism) */
	private final IArcFormalism arcFormalism;

	/** The arc */
	private IArc arc;

	/**
	 * Connect the arc
	 * @param source Source
	 * @param target Target
	 * @param formalism Formalism
	 */
	public ArcCompleteCmd(INode source, INode target, IArcFormalism formalism) {
		this.graph = (IGraph) source.getParent();
		this.source = source;
		this.target = target;
		this.arcFormalism = formalism;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		// Is the connection correct according to the formalism ?
		return arcFormalism.getFormalism().isLinkAllowed(this.source, this.target, this.arcFormalism);
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Build the arc
		try {
			// The arc has to be created
			arc = graph.createArc(arcFormalism.getName(), source, target);
			
			// Handle the special case where the source and the target node of the arc are the same
			if (source.equals(target)) {
				// In that case, two inflex points are created
				int x = source.getGraphicInfo().getLocation().x + (source.getGraphicInfo().getSize().width)/2;
				int y = source.getGraphicInfo().getLocation().y;
				arc.addInflexPoint(new Point(x-20,y-20));
				arc.addInflexPoint(new Point(x+20,y-20));
			}
		} catch (ModelException e) {
			LOGGER.warning("Impossible de construire l'arc: " + e.toString()); //$NON-NLS-1$
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
