package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.ArcContainer;
import fr.lip6.move.coloane.core.copypast.container.ModelContainer;
import fr.lip6.move.coloane.core.copypast.container.NodeContainer;
import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class PasteCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ModelContainer modelContainer;
	private HashMap<NodeContainer, INodeImpl> nodes = new HashMap<NodeContainer, INodeImpl>();
	private HashMap<ArcContainer, IArcImpl> arcs = new HashMap<ArcContainer, IArcImpl>();

	private IModelImpl model;

	public PasteCommand(ColoaneEditor editor) {
		model = editor.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@SuppressWarnings("unchecked")
	public final boolean canExecute() {
		modelContainer = (ModelContainer) Clipboard.getDefault().getContents();
		if (modelContainer == null || modelContainer.isEmpty()
				|| !modelContainer.getFormalism().equals(model.getFormalism())) {
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
		for (NodeContainer nc : modelContainer.getNodes()) {
			nodes.put(nc, nc.copy(model));
		}
		for (ArcContainer ac : modelContainer.getArcs()) {
			INodeImpl source = nodes.get(modelContainer.getNode(ac.getIdSource()));
			INodeImpl target = nodes.get(modelContainer.getNode(ac.getIdTarget()));
			if (source != null && target != null) {
				arcs.put(ac, ac.copy(model, source, target));
			}
		}
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		for (INodeImpl node : nodes.values()) {
			try {
				model.addNode(node);
			} catch (BuildException e) {
				log.warning("Impossible d'ajouter le noeud"); //$NON-NLS-1$
			}
		}
		for (IArcImpl arc : arcs.values()) {
			try {
				model.addArc(arc);
			} catch (BuildException e) {
				log.warning("Impossible d'ajouter l'arc"); //$NON-NLS-1$
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public final boolean canUndo() {
		return modelContainer != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		for (IArcImpl arc : arcs.values()) {
			try {
				model.removeArc(arc);
			} catch (BuildException e) {
				log.warning("Impossible d'enlever l'arc"); //$NON-NLS-1$
			}
		}
		for (INodeImpl node : nodes.values()) {
			try {
				model.removeNode(node);
			} catch (BuildException e) {
				log.warning("Impossible d'enlever le noeud"); //$NON-NLS-1$
			}
		}
	}
}
