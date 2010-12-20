package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.GraphContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

/**
 * Copy Command
 * 
 * @author Clément Démoulins
 */
public class CopyCommand extends Command {
	private GraphContainer container;

	/**
	 * Constructor
	 * @param editor The current editor 
	 */
	public CopyCommand(ColoaneEditor editor) {
		container = new GraphContainer(editor.getGraph().getFormalism());
	}

	/**
	 * Add a node to the list of copied nodes
	 * @param node The node to add to the list
	 */
	public final void addNode(INode node) {
		container.addNode(node);
	}

	/**
	 * Add an arc to the list of copied arcs
	 * @param arc The arc to add to the list
	 */
	public final void addArc(IArc arc) {
		container.addArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return !container.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canUndo() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		// THE instruction !
		Clipboard.getDefault().setContents(container);
	}
}
