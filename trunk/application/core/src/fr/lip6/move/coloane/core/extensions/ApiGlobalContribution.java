package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.actions.LocalAction;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.actions.CompoundContributionItem;

public class ApiGlobalContribution extends CompoundContributionItem {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	@Override
	protected IContributionItem[] getContributionItems() {
		ISessionManager sessionManager = SessionManager.getInstance();
		LOGGER.fine("Browsing available Global APIs");  //$NON-NLS-1$
				
		List<ApiDescription> availableApis = sessionManager.getAvailableGlobalApis();
		IContributionItem[] toReturn = new IContributionItem[availableApis.size()];
		for (int i = 0; i < availableApis.size(); i++) {
			toReturn[i] = availableApis.get(i).getRootMenu();
		}
		
		if (toReturn.length == 0) {
			LOGGER.finer("No Global API detected");  //$NON-NLS-1$
			toReturn = new IContributionItem[1];
			IAction localAction = new LocalAction("Waiting for Global Services Platforms...", "", null, null);  //$NON-NLS-1$//$NON-NLS-2$
			localAction.setEnabled(false);
			toReturn[0] = new ActionContributionItem(localAction);
		}

		return toReturn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDynamic() {
		return true;
	}
	
	@Override
	public boolean isDirty() {
		return true;
	}
}
