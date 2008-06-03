package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.copypast.container.ModelContainer;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class CopyCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core");

	private ModelContainer container;

	public CopyCommand(ColoaneEditor editor) {
		container = new ModelContainer(editor.getModel().getFormalism());
	}

	/**
	 * Ajout d'un noeud à copier
	 * @param node
	 */
	public final void addNode(INodeImpl node) {
		container.addNode(node);
	}

	/**
	 * Ajout d'un arc à copier
	 * @param arc
	 */
	public final void addArc(IArcImpl arc) {
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
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public final boolean canUndo() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		log.fine("Copie de la sélection");
		Clipboard.getDefault().setContents(container);
	}

}
