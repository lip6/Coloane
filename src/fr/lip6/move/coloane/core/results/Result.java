package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

/**
 * Allow a tool to send a result which will be displayed in an Eclipse view.<br><br>
 * The result has a tree structure : it can contain {@link SubResult} which will also be able to contain others SubResult.<br>
 * A result can also carries some request to modify the current model or to create a new one.<br>
 * To modify the current model, contribute the <code>deltaRequestsList</code> through the method {@link #addDeltaRequest(IRequest)}.<br>
 * To describe a new model, contribute to the <code>newModelRequestList</code> through the method 
 * 
 * @author Florian David
 * @author Jean-Baptiste Voron
 */
public class Result implements IResult {
	/** Result name */
	private String resultName;

	/** Sub-results included in this result. */
	private List<ISubResult> subResults;

	/** Tips list sorted by model object ID*/
	private Map<Integer, List<ITip>> tips;

	/** The current graph. It may be modified by requests ({@link #deltaRequestsList})... */
	private IGraph currentGraph;
	
	/** A new graph associated to this result. It is built thanks to execution of special requests stored into {@link #newComputedGraphRequestsList} */
	private IGraph newComputedGraph;

	/** 
	 * Delta Requests List<br>
	 * Requests are used in order to produce commands that will be used to modify the model in a clean way
	 */
	private List<IRequest> deltaRequestsList;

	/** 
	 * Delta commands list
	 * Commands are used in order to modify the new model in a clean way
	 */
	private List<IRequest> newComputedGraphRequestsList;

	/** List of textual results */
	private List<List<String>> textualResults;

	/**
	 * Constructor of an empty result.
	 * @param resultName the name of the result (name of the called tool is preferred).
	 * @param currentGraph The current graph... Could be modified through commands
	 */
	public Result(String resultName, IGraph currentGraph) {
		this.resultName = resultName;
		this.currentGraph = currentGraph;
		this.newComputedGraph = null;
		this.subResults = new ArrayList<ISubResult>();
		this.tips = new HashMap<Integer, List<ITip>>();
		this.deltaRequestsList = new ArrayList<IRequest>();
		this.newComputedGraphRequestsList = new ArrayList<IRequest>();
		this.textualResults = new ArrayList<List<String>>();
	}

	/**
	 * Add a sub-result in the sub-result list. 
	 * @param subResult The sub-result added to the list.
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}

	/**
	 * Add a special information to the existing list
	 * @param tip The special information to add to the list
	 */
	public final void addTip(ITip tip) {
		// If some tips are already associated to the model object
		if (this.tips.containsKey(tip.getIdObject())) {
			(this.tips.get(tip.getIdObject())).add(tip);
		// If this is the first tip to be associated with this object
		} else {
			List<ITip> list = new ArrayList<ITip>();
			list.add(tip);
			this.tips.put(tip.getIdObject(), list);
		}
	}

	/**
	 * Add a tip to the tip list.
	 * @param object the tip will belong to this object.
	 * @param name the tip name.
	 * @param value the tip value.
	 */
	public final void addTip(IElement object, String name, String value) {
		if (object != null) {
			this.addTip(new Tip(object.getId(), name, value));
		}
	}
	
	/**
	 * Add a request to the existing list of delta requests.<br>
	 * These requests specify the transformation from the current graph to a new one.
	 * @param request The request to add to the list
	 */
	public final void addDeltaRequest(IRequest request) {
		this.deltaRequestsList.add(request);
	}
	
	public final void setNewGraph(IGraph newGraph) {
		this.newComputedGraph = newGraph;
	}

	/**
	 * Add a set of requests that describe a new model
	 * @param commands A list of commands
	 */
	public final void addNewGraphRequest(List<IRequest> commands) {
		this.newComputedGraphRequestsList.addAll(commands);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getResultName() {
		return this.resultName;
	}

	/**
	 * {@inheritDoc}
	 * @deprecated
	 */
	public final String getRootName() {
		return this.resultName;
	}

	/**
	 * {@inheritDoc}
	 * @deprecated
	 */
	public final String getServiceName() {
		return this.resultName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return this.subResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<ITip>> getTips() {
		return this.tips;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<List<String>> getTextualResults() {
		return textualResults;
	}
	
	/**
	 * Add a textual result to the existing list
	 * @param result the textual result to be added to the list.<br>
	 * This one is stored in an array for being displayed in the columns of the view.
	 * TODO: Duplicated code {@link SubResult}
	 */
	public final void addTextualResult(String... result) {
		// Boolean variable 'emptyList' tells us whether the constructed array is constituted by empty string
		boolean emptyList = true;
		List<String> array = new ArrayList<String>(result.length);
		for (int i = 0; i < result.length; i++) {
			array.add(result[i]);
			emptyList = emptyList && ("".equals(result[i])); //$NON-NLS-1$
		}

		if (emptyList) {
			// If all strings are empty, we return instead an array with "No result"
			// TODO: Not sure that it is the good way to do.
			array.clear();
			array.add("No result"); //$NON-NLS-1$
			this.textualResults.add(array);
		} else {
			this.textualResults.add(array);
		}
	}
	
	/**
	 * Transform the current model thanks to the list of deltaRequests
	 * @return The current model modified by the execution of deltaRequests
	 */
	public final Command updateCurrentGraph() {
		// If the command list is null...
		if (this.deltaRequestsList.size() <= 0) { return null; }
		
		// Otherwise, we transform the original model
		CompoundCommand deltaCommands = new CompoundCommand();
		for (IRequest request : this.deltaRequestsList) {
			if (request == null) { continue; }
			// Transform the request into a command...
			deltaCommands.add(CommandFactory.createCommand(request, this.currentGraph));
		}
		return deltaCommands;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Command getNewGraph() {
		if (this.newComputedGraphRequestsList.size() <= 0) { return null; }

		// If the list of requests to be applied on the new graph is not null
		// We transform the new computed model
		CompoundCommand deltaCommands = new CompoundCommand();
		for (IRequest request : this.newComputedGraphRequestsList) {
			if (request == null) { continue; }
			// Transform the request into a command...
			deltaCommands.add(CommandFactory.createCommand(request, this.newComputedGraph));
		}
		return deltaCommands;
	}
}
