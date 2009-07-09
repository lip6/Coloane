package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.ArrayList;
import java.util.List;

/**
 * The action of laying out a graph.
 */
public class LayoutAction implements IColoaneAction {
	/**
	 * {@inheritDoc}
	 */
	public final List<IResult> run(IGraph model) {
		if (model != null) {
			List<ICommand> commands = GraphLayout.layout(model);
			List<IResult> result = new ArrayList<IResult>();
			result.add(new ResultCommand(commands));
			return result;
		}
		return null;
	}
}
