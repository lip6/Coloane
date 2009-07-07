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
 * Commande COLLER
 */
public class PasteCommand extends Command {
	private Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private GraphContainer graphContainer;
	private Map<NodeContainer, INode> nodes = new HashMap<NodeContainer, INode>();
	private Map<ArcContainer, IArc> arcs = new HashMap<ArcContainer, IArc>();
	private List<EditPart> selection = new ArrayList<EditPart>();

	private IGraph graph;
	private GraphicalViewer viewer;

	/**
	 * Création de la commande de collage
	 * @param editor L'éditeur de modèle concerné par la commande
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
		logger.fine("Collage de la sélection"); //$NON-NLS-1$

		// Sauvegarde de la sélection
		for (Object obj : ((IStructuredSelection) viewer.getSelection()).toList()) {
			if (obj instanceof EditPart) {
				selection.add((EditPart) obj);
			}
		}
		viewer.deselectAll();

		// Création des noeuds
		for (NodeContainer nc : graphContainer.getNodes()) {
			try {
				INode node = nc.copy(graph);
				// On sélectionne les nouveaux noeuds
				viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(node));
				nodes.put(nc, node);
			} catch (ModelException e) {
				logger.warning(e.getMessage());
				e.printStackTrace();
			}
		}

		// Création des arcs
		for (ArcContainer ac : graphContainer.getArcs()) {
			INode source = nodes.get(graphContainer.getNode(ac.getIdSource()));
			INode target = nodes.get(graphContainer.getNode(ac.getIdTarget()));
			if (source != null && target != null) {
				try {
					arcs.put(ac, ac.copy(graph, source, target));
				} catch (ModelException e) {
					logger.warning(e.getMessage());
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
