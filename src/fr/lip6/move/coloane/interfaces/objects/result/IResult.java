package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

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
	 * Return the name of the result (name of the called tool is preferred).
	 * @return the result name.
	 */
	String getResultName();

	/**
	 * @return the name of the root menu from which the service has been invoked
	 * @deprecated Use {@link IResult#getResultName()} instead.
	 */
	String getRootName();

	/**
	 * @return the invoked service name 
	 * @deprecated Use {@link IResult#getResultName()} instead.
	 */
	String getServiceName();
	
	/**
	 * @return The list of sub-results contained in the result.
	 */
	List<ISubResult> getSubResults();

	/**
	 * @return The list of textual results contained in the result.
	 */
	List<List<String>> getTextualResults();

	/**
	 * @see ITip
	 * @return The list of special information associated to the result.
	 */
	Map<Integer, List<ITip>> getTips();

	/**
	 * @return A new graph provided by the tool (or <code>null</code> if there is no new graph to display)
	 */
	IGraph getNewGraph();

	/**
	 * @return A list of modification to perform on the current model
	 */
	List<ICommand> getModificationsOnCurrentGraph();
}
