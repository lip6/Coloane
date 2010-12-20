package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IElement;

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
	 * Add a special information the existing list
	 * @param tip The special information to add to the model
	 */
	void addTip(ITip tip);
	
	/**
	 * Add a new tip in the subResult.<br>
	 * This is a convenience way to add a tip without explicitly create it.
	 * @param object the model element in which the tip will be linked.
	 * @param name the tip name.
	 * @param value the tip value.
	 */
	void addTip(IElement object, String name, String value);
	
	/**
	 * Add a textual result in the list.
	 * @param result the textual result to add to the list.<br>
	 * It's stored in an array for being displayed in the columns of the view.
	 */
	void addTextualResult(String... result);
	
	
	/**
	 * Add an attribute (associated with a model object) to the list of attributes to be highlighted.
	 * @param objectId The object ID to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
	 */
	void addAttributeOutline(Integer objectId, String attributeName);
	
	/**
	 * Add an attribute (associated with a model object) to the list of attributes to be highlighted.<br>
	 * This method uses directly an IElement instead of ElementID.
	 * @param object The object to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
	 */
	void addAttributeOutline(IElement object, String attributeName);

	/**
	 * Add an object which to the list of potential highlighted but non-displayed element.
	 * @param objectId The object ID to be added to the list.
	 */
	void addObjectDesignation(Integer objectId);

	/**
	 * Add an object which to the list of potential highlighted but non-displayed element.
	 * @param object The object to be added to the list.
	 */
	void addObjectDesignation(IElement object);
	
	/**
	 * Add an object which to the list of potential highlighted and displayed element.
	 * @param objectId The object ID to be added to the list.
	 */
	void addObjectOutline(Integer objectId);
	
	/**
	 * Add an object which to the list of potential highlighted and displayed element.
	 * @param object The object to be added to the list.
	 */
	void addObjectOutline(IElement object);
	
	/**
	 * Add a sub-result in the sub-result list.
	 * @param subResult The sub-result added to the list.
	 */
	void addSubResult(ISubResult subResult);
	
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
