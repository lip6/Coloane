package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.ArcContainer;
import fr.lip6.move.coloane.core.copypast.container.GraphContainer;
import fr.lip6.move.coloane.core.copypast.container.NodeContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IGraph;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class PasteCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private GraphContainer graphContainer;
	private HashMap<NodeContainer, INode> nodes = new HashMap<NodeContainer, INode>();
	private HashMap<ArcContainer, IArc> arcs = new HashMap<ArcContainer, IArc>();

	private IGraph graph;

	public PasteCommand(ColoaneEditor editor) {
		graph = editor.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@SuppressWarnings("unchecked")
	public final boolean canExecute() {
		graphContainer = (GraphContainer) Clipboard.getDefault().getContents();
		if (graphContainer == null || graphContainer.isEmpty()
				|| !graphContainer.getFormalism().equals(graph.getFormalism())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		log.fine("Collage de la sélection"); //$NON-NLS-1$
		for (NodeContainer nc : graphContainer.getNodes()) {
			nodes.put(nc, nc.copy(graph));
		}
		for (ArcContainer ac : graphContainer.getArcs()) {
			INode source = nodes.get(graphContainer.getNode(ac.getIdSource()));
			INode target = nodes.get(graphContainer.getNode(ac.getIdTarget()));
			if (source != null && target != null) {
				arcs.put(ac, ac.copy(graph, source, target));
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		for (INode node : nodes.values()) {
			graph.addNode(node);
		}
		for (IArc arc : arcs.values()) {
			graph.addArc(arc);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public final boolean canUndo() {
		return graphContainer != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		for (IArc arc : arcs.values()) {
			graph.deleteArc(arc);
		}
		for (INode node : nodes.values()) {
			graph.deleteNode(node);
		}
	}
}
