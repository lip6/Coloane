package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.ModelContainer;
import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class CutCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ColoaneEditor editor;
	private ModelContainer container;

	private ArrayList<INodeImpl> nodes = new ArrayList<INodeImpl>();
	private HashSet<IArcImpl> arcs = new HashSet<IArcImpl>();

	public CutCommand(ColoaneEditor editor) {
		this.editor = editor;
		if (editor.getGraph() != null) {
			container = new ModelContainer(editor.getGraph().getFormalism());
		}
	}

	/**
	 * @param node Ajout d'un noeud à couper
	 */
	public final void addNode(INodeImpl node) {
		nodes.add(node);
		arcs.addAll(node.getSourceArcs());
		arcs.addAll(node.getTargetArcs());
		container.addNode(node);
	}

	/**
	 * @param arc Ajout d'un arc à couper
	 */
	public final void addArc(IArcImpl arc) {
		arcs.add(arc);
		container.addArc(arc);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {
		return !container.isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		log.fine("Coupage de la sélection"); //$NON-NLS-1$
		Clipboard.getDefault().setContents(container);

		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public final boolean canUndo() {
		return container != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		for (IArcImpl arc : arcs) {
			try {
				editor.getGraph().removeArc(arc);
			} catch (BuildException e) {
				log.warning(e.getMessage());
			}
		}
		for (INodeImpl node : nodes) {
			try {
				editor.getGraph().removeNode(node);
			} catch (BuildException e) {
				log.warning(e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		for (INodeImpl node : nodes) {
			try {
				editor.getGraph().addNode(node);
			} catch (BuildException e) {
				log.warning(e.getMessage());
			}
		}
		for (IArcImpl arc : arcs) {
			try {
				editor.getGraph().addArc(arc);
			} catch (BuildException e) {
				log.warning(e.getMessage());
			}
		}
	}

}
