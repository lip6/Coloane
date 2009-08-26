package fr.lip6.move.coloane.interfaces.objects.result;

import java.util.List;
import java.util.Map;

/**
 * This interface describe a sub-result.
 *
 * @author Jean-Baptiste Voron
 */
public interface ISubResult {
	
	/**
	 * Return the name of the sub-result.
	 * 
	 * @return the sub-result name.
	 */
	String getSubResultName();

	/**
	 * Return an information about the sub-result.
	 * 
	 * @return the information.
	 */
	String getInformation();
	
	/**
	 * Return the sub-result list contained in the sub-result.
	 * 
	 * @return the sub-result list.
	 */
	List<ISubResult> getSubResults();

	/**
	 * Return the list of the objects ID which will be able to be highlighted in the model and will be added to the menu.
	 * 
	 * @return the list of objects IDs.
	 */
	List<Integer> getObjectsOutline();

	/**
	 * Return the list of the objects ID which will be able to be highlighted in the model but won't be added to the menu.
	 * 
	 * @return the list of objects IDs.
	 */
	List<Integer> getObjectsDesignation();

	/**
	 * Return the map of attributes which will be able to be highlighted in the model.
	 * 
	 * @return the map of objects IDs and attribute.
	 */
	Map<Integer, List<String>> getAttributesOutline();

	/**
	 * Return the list of results in the form of text.
	 * 
	 * @return the list of textuals results.
	 */
	List<List<String>> getTextualResults();
	
	/**
	 * @return la liste d'informations associées au résultat
	 */
	Map<Integer, List<ITip>> getTips();
}
