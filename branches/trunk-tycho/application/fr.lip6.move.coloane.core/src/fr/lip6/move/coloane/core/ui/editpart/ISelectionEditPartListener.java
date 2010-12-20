package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.gef.EditPartListener;

/**
 * Classes that implement this interface want to be able to change their aspect according to the selection state.<br>
 * In that case, they should provide a SelectionEditPArtListener.
 */
public interface ISelectionEditPartListener {

	/**
	 * Some constants
	 */
	int HIGHLIGHT = 10;
	int HIGHLIGHT_NONE = 11;

	/**
	 * @return The listener used to deal with the selection state
	 */
	EditPartListener getSelectionEditPartListener();
}
