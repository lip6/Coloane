package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.core.model.AttributeModel;
import fr.lip6.move.coloane.core.model.NodeModel;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

/**
 * Interface that describes the way to manipulate objects located on the editor.<br>
 * These objects must propose :
 * <ul>
 * 	<li>A way to fetch their location (coordinates) {@link ILocationInfo}</li>
 * 	<li>A way to handle vertical and horizontal guides {@link EditorGuide}</li>
 * </ul>
 * @see {@link NodeModel}, {@link AttributeModel}
 * 
 * @author Jean-Baptiste Voron
 */
public interface ILocatedElement {
	/**
	 * Fetch the current position of the object.
	 * @return ILocationInfo
	 */
	ILocationInfo getLocationInfo();

	/**
	 * @return the guide to which this object is attached
	 * @see EditorGuide.#HORIZONTAL_ORIENTATION
	 * @see EditorGuide.#VERTICAL_ORIENTATION
	 */
	EditorGuide getGuide(int orientation);

	/**
	 * Set a new guide (horizontal or vertical) to this object
	 * @param guide The new guide
	 */
	void setGuide(EditorGuide guide);
	
	/**
	 * Remove (detach) a guide from this object
	 * @param guide The guide to remove
	 */
	void removeGuide(int orientation);
}
