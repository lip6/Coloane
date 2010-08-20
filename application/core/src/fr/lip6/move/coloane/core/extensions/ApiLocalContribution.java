package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.actions.LocalAction;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.actions.CompoundContributionItem;

public class ApiLocalContribution extends CompoundContributionItem {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	@Override
	protected IContributionItem[] getContributionItems() {
		ISession currentSession = SessionManager.getInstance().getCurrentSession();
		LOGGER.fine("Browsing available Local APIs");  //$NON-NLS-1$
		
		if (currentSession == null) {
			LOGGER.warning("There is no current session... Please open a model first"); //$NON-NLS-1$
			IContributionItem[] toReturn = new IContributionItem[1];
			IAction localAction = new LocalAction("Open a model to use dedicated Services Platforms", "", null, null);  //$NON-NLS-1$//$NON-NLS-2$
			localAction.setEnabled(false);
			toReturn[0] = new ActionContributionItem(localAction);
			return toReturn;
		}
				
		List<ApiDescription> availableApis = currentSession.getAvailableApis();
		IContributionItem[] toReturn = new IContributionItem[availableApis.size()];
		for (int i = 0; i < availableApis.size(); i++) {
			toReturn[i] = availableApis.get(i).getRootMenu();
		}
		
		if (toReturn.length == 0) {
			toReturn = new IContributionItem[1];
			IAction localAction = new LocalAction("No Model Services Platform available yet", "", null, null);  //$NON-NLS-1$//$NON-NLS-2$
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
