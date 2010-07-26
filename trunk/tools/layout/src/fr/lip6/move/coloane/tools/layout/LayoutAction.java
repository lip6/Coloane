package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;

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
			//List<IRequest> requests = GraphLayout.layout(model, monitor);
			List<IRequest> requests = new ArrayList<IRequest>();
			List<IResult> results = new ArrayList<IResult>();

			IResult result = new Result("Dot Layout");
			result.addDeltaRequests(requests);
			
			ISubResult subresult = new SubResult("Statistics about the execution");
			subresult.addTextualResult("Number of nodes", String.valueOf(model.getNodes().size()));
			subresult.addTextualResult("Number of arcs", String.valueOf(model.getArcs().size()));
			result.addSubResult(subresult);
			
			results.add(result);

			return results;
		}
		return null;
	}
}
