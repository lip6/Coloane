/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
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

/**
 * Retrieve contribution items of all locals apis filtered on the current session.
 */
public class ApiLocalContribution extends CompoundContributionItem {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	@Override
	protected final IContributionItem[] getContributionItems() {
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
	public final boolean isDynamic() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isDirty() {
		return true;
	}
}
