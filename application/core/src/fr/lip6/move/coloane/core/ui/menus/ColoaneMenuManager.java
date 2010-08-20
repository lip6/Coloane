package fr.lip6.move.coloane.core.ui.menus;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

public class ColoaneMenuManager extends MenuManager {
	
	/**
	 * Constructor
	 * @param text
	 * @param id
	 * @param state
	 */
	public ColoaneMenuManager(String text, String id, boolean state) {
		super(text, id);
	}

	/**
	 * Constructor
	 * @param text
	 * @param id
	 * @param state
	 */
	public ColoaneMenuManager(String text, String id, boolean state, ImageDescriptor icon) {
		super(text, icon, id);
	}

}
