package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/** A new graph associated to this result. It is built thanks to execution of special requests stored into {@link #newComputedGraphRequestsList} */
	private IGraph newComputedGraph;

	/** 
	 * Delta requests list describing the changes to apply on the current graph<br>
	 * Requests are used in order to produce commands that will be used to modify the model in a clean way
	 */
	private List<IRequest> deltaRequestsList;

	/** 
	 * Delta requests list describing a new graph !
	 * Requests are used in order to produce commands that will be used to modify the model in a clean way
	 */
	private List<IRequest> newComputedGraphRequestsList;

	/** List of textual results */
	private List<List<String>> textualResults;
	
	/** Should the result be displayed to the user */
	private boolean display;

	/**
	 * Constructor of an empty result.
	 * @param resultName the name of the result (name of the called tool is preferred).
	 */
	public Result(String resultName) {
		this.resultName = resultName;
		this.newComputedGraph = null;
		this.subResults = new ArrayList<ISubResult>();
		this.tips = new HashMap<Integer, List<ITip>>();
		this.deltaRequestsList = new ArrayList<IRequest>();
		this.newComputedGraphRequestsList = new ArrayList<IRequest>();
		this.textualResults = new ArrayList<List<String>>();
		this.display = true;
	}
	
	/** 
	 * Change the display status of the result
	 * @param display <code>true</code> is the result should be display to the user
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean shouldBeDisplayed() {
		return display;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public final void addTip(IElement object, String name, String value) {
		if (object != null) {
			this.addTip(new Tip(object.getId(), name, value));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void addDeltaRequest(IRequest request) {
		this.deltaRequestsList.add(request);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void addDeltaRequests(List<IRequest> requests) {
		this.deltaRequestsList.addAll(requests);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public final void setNewGraph(IGraph newGraph) {
		this.newComputedGraph = newGraph;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addNewGraphDeltaRequest(List<IRequest> requests) {
		this.newComputedGraphRequestsList.addAll(requests);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getResultName() {
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
	public List<IRequest> getDeltaRequestsList() {
		return deltaRequestsList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<IRequest> getNewComputedGraphDeltaRequestsList() {
		return newComputedGraphRequestsList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public IGraph getNewComputedGraph() {
		return newComputedGraph;
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
}
