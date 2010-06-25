package fr.lip6.move.coloane.core.ui.menus;

/**
 * Defined an element with a state.
 * 
 * @author Clément Démoulins
 */
public interface IStatedElement {

	/**
	 * @return state of this element
	 */
	boolean getState();
	
	/**
	 * @param state change the state of this element
	 */
	void setState(boolean state);
}
