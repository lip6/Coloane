/**
 * 
 */
package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.ui.actions.OpenInterfaceAction;
import fr.lip6.move.coloane.core.ui.actions.OpenSourceLinkAction;
import fr.lip6.move.coloane.core.ui.actions.OpenTargetLinkAction;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * Action provider for the models navigator.
 * 
 * @author Clément Démoulins
 */
public class NavigatorActionProvider extends CommonActionProvider {

	private OpenInterfaceAction openInterfaceAction;
	private OpenSourceLinkAction openSourceLinkAction;
	private OpenTargetLinkAction openTargetLinkAction;

	/** {@inheritDoc} */
	@Override
	public final void init(ICommonActionExtensionSite site) {
		ICommonViewerSite viewSite = site.getViewSite();
		if (viewSite instanceof ICommonViewerWorkbenchSite) {
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
			openInterfaceAction = new OpenInterfaceAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
			openSourceLinkAction = new OpenSourceLinkAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
			openTargetLinkAction = new OpenTargetLinkAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void fillContextMenu(IMenuManager menu) {
		if (openInterfaceAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openInterfaceAction);
		}
		if (openSourceLinkAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN_WITH, openSourceLinkAction);
		}
		if (openTargetLinkAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openTargetLinkAction);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void fillActionBars(IActionBars actionBars) {
		if (openInterfaceAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openInterfaceAction);
		} else if (openTargetLinkAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openTargetLinkAction);
		}
	}

}
