package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.ui.actions.AlternateAction;
import fr.lip6.move.coloane.core.ui.actions.CurveAction;
import fr.lip6.move.coloane.core.ui.actions.RemoveInflexAction;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Provides context menu actions for the ShapesEditor.
 */
class ColoaneContextMenuProvider extends ContextMenuProvider {

	/** The editor's action registry. */
	private ActionRegistry actionRegistry;

	/**
	 * Instantiate a new menu context provider for the specified EditPartViewer and ActionRegistry.
	 * @param viewer	the editor's graphical viewer
	 * @param registry	the editor's action registry
	 * @throws NullPointerException if registry is <tt>null</tt>.
	 */
	public ColoaneContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) throws NullPointerException {
		super(viewer);
		if (registry == null) {
			throw new NullPointerException();
		}
		actionRegistry = registry;
	}

	/** {@inheritDoc} */
	@Override
	public void buildContextMenu(final IMenuManager menu) {
		// Add standard action groups to the menu
		GEFActionConstants.addStandardActionGroups(menu);

		menu.prependToGroup(GEFActionConstants.GROUP_UNDO, getAction(ActionFactory.UNDO.getId())); // action to add
		menu.prependToGroup(GEFActionConstants.GROUP_UNDO, getAction(ActionFactory.REDO.getId()));

		menu.prependToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.DELETE.getId()));
        menu.prependToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.PASTE.getId()));
        menu.prependToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.COPY.getId()));
        menu.prependToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.CUT.getId()));
		menu.prependToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.SELECT_ALL.getId()));

		// Curve or Straight arcs
		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, getAction(CurveAction.ID));
		// Remove Bendpoints
		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, getAction(RemoveInflexAction.ID));
		// Switch Figure
		menu.appendToGroup(GEFActionConstants.GROUP_VIEW, getAction(AlternateAction.ID));
	}

	/**
	 * @param actionId id de l'action à récupérer
	 * @return action correspondante à l'id ou <code>null</code>
	 */
	private IAction getAction(String actionId) {
		return actionRegistry.getAction(actionId);
	}

}
