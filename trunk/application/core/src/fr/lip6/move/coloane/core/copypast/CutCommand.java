package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.GraphContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class CutCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ColoaneEditor editor;
	private GraphContainer container;

	private ArrayList<INode> nodes = new ArrayList<INode>();
	private HashSet<IArc> arcs = new HashSet<IArc>();

	public CutCommand(ColoaneEditor editor) {
		this.editor = editor;
		if (editor.getGraph() != null) {
			container = new GraphContainer(editor.getGraph().getFormalism());
		}
	}

	/**
	 * @param node Ajout d'un noeud à couper
	 */
	public final void addNode(INode node) {
		nodes.add(node);
		arcs.addAll(node.getOutcomingArcs());
		arcs.addAll(node.getIncomingArcs());
		container.addNode(node);
	}

	/**
	 * @param arc Ajout d'un arc à couper
	 */
	public final void addArc(IArc arc) {
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
		for (IArc arc : arcs) {
			editor.getGraph().deleteArc(arc);
		}
		for (INode node : nodes) {
			editor.getGraph().deleteNode(node);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		for (INode node : nodes) {
			editor.getGraph().addNode(node);
		}
		for (IArc arc : arcs) {
			editor.getGraph().addArc(arc);
		}
	}

}
