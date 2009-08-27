package fr.lip6.move.coloane.interfaces.objects.result;

import java.util.List;
import java.util.Map;

/**
 * This interface describes a <b>sub-result</b>.
 * A sub-result is composed of the following elements:
 * <ul>
 * 	<li>Sub-results</li>
 * 	<li>Object identifiers that will be highlighted in the editor</li>
 * 	<li>Attribute that will be highlighted in the editor</li>
 * 	<li>Textual results</li>
 * 	<li>Special information</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public interface ISubResult {
	
	/**
	 * @return A name associated to the sub-result (often a category name).
	 */
	String getSubResultName();

	/**
	 * @return A description of the sub-result.
	 */
	String getInformation();
	
	/**
	 * @return A list of sub-results contained into the sub-result.
	 */
	List<ISubResult> getSubResults();

	/**
	 * @return The list of the objects identifier which will be able to be highlighted in the model and <b>will be added to the result view</b>.
	 */
	List<Integer> getObjectsOutline();

	/**
	 * @return Return the list of the objects identifier which will be able to be highlighted in the model but <b>won't be added to the result view</b>.
	 */
	List<Integer> getObjectsDesignation();

	/**
	 * @return The map of attributes which will be able to be highlighted in the model.
	 */
	Map<Integer, List<String>> getAttributesOutline();

	/**
	 * @return The list of textual results
	 */
	List<List<String>> getTextualResults();
	
	/**
	 * @see ITip
	 * @return The list of special information
	 */
	Map<Integer, List<ITip>> getTips();
}
