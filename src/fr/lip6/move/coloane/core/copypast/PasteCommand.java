package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.ArcContainer;
import fr.lip6.move.coloane.core.copypast.container.GraphContainer;
import fr.lip6.move.coloane.core.copypast.container.NodeContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Paste command
 * 
 * @author Clément Démoulins
 */
public class PasteCommand extends Command {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$


	/** The graph container */
	private GraphContainer graphContainer;
	
	/** Map of all added nodes */
	private Map<NodeContainer, INode> nodes = new HashMap<NodeContainer, INode>();
	/** Map of all added arcs */
	private Map<ArcContainer, IArc> arcs = new HashMap<ArcContainer, IArc>();
	/** Current selection */
	private List<EditPart> selection = new ArrayList<EditPart>();

	/** The current graph */
	private IGraph graph;
	/** The viewer */
	private GraphicalViewer viewer;

	/**
	 * Constructor
	 * @param editor The current editor 
	 */
	public PasteCommand(ColoaneEditor editor) {
		graph = editor.getGraph();
		viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		graphContainer = (GraphContainer) Clipboard.getDefault().getContents();
		if (graphContainer == null || graphContainer.isEmpty()
				|| !graphContainer.getFormalism().equals(graph.getFormalism())) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}

		// Backup the selection
		for (Object obj : ((IStructuredSelection) viewer.getSelection()).toList()) {
			if (obj instanceof EditPart) {
				selection.add((EditPart) obj);
			}
		}
		viewer.deselectAll();

		// Create all nodes
		for (NodeContainer nc : graphContainer.getNodes()) {
			try {
				INode node = nc.copy(graph);
				// Add this node to the selection
				viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(node));
				nodes.put(nc, node);
			} catch (ModelException e) {
				LOGGER.warning("Something went wrong during the (node) paste operation : " + e.getMessage()); //$NON-NLS-1$
				e.printStackTrace();
			}
		}

		// Création des arcs
		for (ArcContainer ac : graphContainer.getArcs()) {
			// Fetch source and target is the new graph
			INode source = nodes.get(graphContainer.getNode(ac.getIdSource()));
			INode target = nodes.get(graphContainer.getNode(ac.getIdTarget()));
			if (source != null && target != null) {
				try {
					arcs.put(ac, ac.copy(graph, source, target));
				} catch (ModelException e) {
					LOGGER.warning("Something went wrong during the (arc) paste operation : " + e.getMessage()); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		viewer.deselectAll();
		for (INode node : nodes.values()) {
			graph.addNode(node);
			viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(node));
		}
		for (IArc arc : arcs.values()) {
			graph.addArc(arc);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canUndo() {
		return graphContainer != null;
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		viewer.deselectAll();
		for (IArc arc : arcs.values()) {
			graph.deleteArc(arc);
		}
		for (INode node : nodes.values()) {
			graph.deleteNode(node);
		}
		for (EditPart editPart : selection) {
			viewer.appendSelection(editPart);
		}
	}
}
