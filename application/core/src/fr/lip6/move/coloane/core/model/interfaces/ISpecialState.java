package fr.lip6.move.coloane.core.model.interfaces;

/**
 * The elements that implement this interface can be highlighted in a special state.<br>
 * This can be done if and only if the associated edit is able to catch and handle the event {@link #SPECIAL_STATE_CHANGE}
 * 
 * @author Jean-Baptiste Voron
 */
public interface ISpecialState {
	/** Event raised when the element has to be highlighted in a special state */
	String SPECIAL_STATE_CHANGE = "Element.SpecialStateChange"; //$NON-NLS-1$

	/**
	 * Set the special state
	 * @param state the state
	 */
	void setSpecialState(boolean state);
}
