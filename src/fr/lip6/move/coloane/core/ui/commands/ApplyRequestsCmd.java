package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.results.CommandFactory;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

/**
 * Command that handles requests coming from a tool result
 * 
 * @author Jean-Baptiste Voron
 * 
 * @see IRequest
 * @see IResult
 */

public class ApplyRequestsCmd extends CompoundCommand {
	/** List of request to apply on the graph */
	List<IRequest> requests;
	
	/** The graph on which requests will be applied */
	IGraph graph;
	
	/**
	 * Constructor
	 * @param requests The list of requests to be applied on the graph
	 * @param graph The graph on which requests will be applied
	 */
	public ApplyRequestsCmd(List<IRequest> requests, IGraph graph) {
		this.requests = requests;
		this.graph = graph;

		for (IRequest request : this.requests) {
			if (request == null) { continue; }
			// Transform the request into a command...
			add(CommandFactory.createCommand(request, this.graph));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		return (this.requests.size() > 0);
	}
}
