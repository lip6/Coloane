package fr.lip6.move.coloane.interfaces.objects.result;

/**
 * Description of a special information associated to a results (or sub-result).<br>
 * This kind of information will be displayed in a special way in the editor.
 *
 * @author Jean-Baptiste Voron
 */
public interface ITip {

	/**
	 * @return The identifier of the object concerned by this information
	 */
	int getIdObject();

	/**
	 * @return The name of the information
	 */
	String getName();

	/**
	 * @return The value of the information
	 */
	String getValue();
}
