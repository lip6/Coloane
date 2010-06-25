package fr.lip6.move.coloane.core.ui.menus;

import org.eclipse.jface.action.MenuManager;

public class ColoaneMenuManager extends MenuManager implements IStatedElement {
	
	private boolean state = false;

	public ColoaneMenuManager(String text, String id, boolean state) {
		super(text, id);
		this.state = state;
	}

	/** {@inheritDoc} */
	public boolean getState() {
		return state;
	}

	/** {@inheritDoc} */
	public void setState(boolean state) {
		this.state = state;
	}

}
