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
 */
public interface ILocatedElement {
	/**
	 * Fetch the current position of the object.
	 * @return ILocationInfo
	 */
	ILocationInfo getLocationInfo();

	/**
	 * @return the horizontal guide associated to this object
	 */
	EditorGuide getHorizontalGuide();

	/**
	 * @return the vertical guide associated to this object
	 */
	EditorGuide getVerticalGuide();

	/**
	 * Set a new horizontal guide to this object
	 * @param guide the new horizontal guide
	 */
	void setHorizontalGuide(EditorGuide guide);

	/**
	 * Set a new vertical guide to this object
	 * @param guide the new vertical guide
	 */
	void setVerticalGuide(EditorGuide guide);
}
