package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The action of laying out a graph.
 */
public class LayoutAction implements IColoaneAction {
	/**
	 * {@inheritDoc}
	 */
	public final List<IResult> run(IGraph model, IProgressMonitor monitor) {
		if (model != null) {
			List<IRequest> commands = GraphLayout.layout(model, monitor);
			IResult result = new Result("Dot Layout", model);
			List<IResult> results = new ArrayList<IResult>();
			results.add(result);
			return results;
		}
		return null;
	}
}
