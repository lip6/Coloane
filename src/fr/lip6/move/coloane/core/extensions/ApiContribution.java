package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.actions.CompoundContributionItem;

public class ApiContribution extends CompoundContributionItem {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	@Override
	protected IContributionItem[] getContributionItems() {
		ISession currentSession = SessionManager.getInstance().getCurrentSession();
		if (currentSession == null) {
			LOGGER.warning("There is no current session... Please open a model first"); //$NON-NLS-1$
			IContributionItem[] toReturn = new IContributionItem[0];
			return toReturn;
		}
				
		List<ApiDescription> availableApis = currentSession.getAvailableApis();
		IContributionItem[] toReturn = new IContributionItem[availableApis.size()];
		for (int i = 0; i < availableApis.size(); i++) {
			toReturn[i] = availableApis.get(i).getRootMenu();
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
