package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

/**
 * The action of laying out a graph.
 */
public class LayoutEditorActionDelegate implements IColoaneAction {
	/**
	 * {@inheritDoc}
	 */
	public final List<ICommand> run(IGraph model) {
		if (model != null) {
			return GraphLayout.layout(model);
		}
		return null;
	}
}
