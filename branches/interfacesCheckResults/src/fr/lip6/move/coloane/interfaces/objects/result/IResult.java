package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;

import java.util.List;
import java.util.Map;

/**
 * This interface describes a <b>result</b> sent by a tool.
 * A result is basically composed of :
 * <ul>
 * 	<li>Sub-Results</li>
 * 	<li>Textual Results</li>
 * 	<li>Special Information: Tips</li>
 * 	<li>A new model</li>
 * 	<li>Some modifications to perform on the current model</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public interface IResult {
	
	/**
	 * Add a sub-result in the sub-result list. 
	 * @param subResult The sub-result added to the list.
	 */
	void addSubResult(ISubResult subResult);

	/**
	 * Add a special information to the existing list
	 * @param tip The special information to add to the list
	 */
	void addTip(ITip tip);
	
	/**
	 * Add a tip to the tip list.
	 * @param object the tip will belong to this object.
	 * @param name the tip name.
	 * @param value the tip value.
	 */
	void addTip(IElement object, String name, String value);
	
	/**
	 * Add a request to the existing list of delta requests.<br>
	 * These requests specify the transformation from the current graph to a new one.
	 * @param request The request to add to the list
	 */
	void addDeltaRequest(IRequest request);
	
	/**
	 * Set a new graph as result.<br>
	 * This new graph will be displayed in a new editor.
	 * @param newGraph The new graph
	 */
	void setNewGraph(IGraph newGraph);

	/**
	 * Add a set of requests that describe modification to perform on a new model
	 * @param requests A list of requests to apply on the new graph
	 */
	void addNewGraphDeltaRequest(List<IRequest> requests);

	/**
	 * Return the name of the result (name of the called tool is preferred).
	 * @return the result name.
	 */
	String getResultName();

	/**
	 * @return The list of sub-results contained in the result.
	 */
	List<ISubResult> getSubResults();
	
	/**
	 * @return The list of requests to apply on the current graph
	 */
	public List<IRequest> getDeltaRequestsList();

	/**
	 * @return The list of requests to apply on the new graph
	 */
	public List<IRequest> getNewComputedGraphDeltaRequestsList();

	/**
	 * @return The new graph attached to this result
	 */
	public IGraph getNewComputedGraph();

	/**
	 * @return The list of textual results contained in the result.
	 */
	List<List<String>> getTextualResults();

	/**
	 * @see ITip
	 * @return The list of special information associated to the result.
	 */
	Map<Integer, List<ITip>> getTips();
}
