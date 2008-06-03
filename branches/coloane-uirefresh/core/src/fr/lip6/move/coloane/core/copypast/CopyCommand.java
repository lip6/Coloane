package fr.lip6.move.coloane.core.copypast;

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

	public final void addNode(INodeImpl node) {
		container.addNode(node);
	}

	public final void addArc(IArcImpl arc) {
		container.addArc(arc);
	}

	@Override
	public final boolean canExecute() {
		return !container.isEmpty();
	}

	@Override
	public final boolean canUndo() {
		return false;
	}

	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		log.fine("Copie de la sélection");
		Clipboard.getDefault().setContents(container);
	}

}
