package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.ui.actions.AlternateAction;
import fr.lip6.move.coloane.core.ui.actions.CurveAction;
import fr.lip6.move.coloane.core.ui.actions.RemoveInflexAction;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.AbstractAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;

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

		System.err.println("avant");
		MenuManager linksModelMenu = new MenuManager("links to");
		for (final IViewReference o : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences()) {
			linksModelMenu.add(new Action() {

				@Override
				public String getText() {
					return "" + o.getId();
				}

			});
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, linksModelMenu);
		}
		linksModelMenu.add(new Action() {

			@Override
			public String getText() {
				return "" + ((FileEditorInput) ((ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart()).getEditorInput()).getStorage();
			}

		});
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, linksModelMenu);
		System.err.println("après");

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
