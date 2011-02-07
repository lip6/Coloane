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
package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.session.SessionManager;

import java.util.logging.Logger;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

/**
 * Listen for events coming from tabs (open, switch, close)
 *
 * @author Jean-Baptiste Voron
 */
public class TabListener implements IPartListener2 {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void partActivated(IWorkbenchPartReference partRef) {
		LOGGER.finer("Tab activated : " + partRef.getPartProperty("session.id")); //$NON-NLS-1$ //$NON-NLS-2$
		SessionManager.getInstance().resumeSession(partRef.getPartProperty("session.id")); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final void partClosed(IWorkbenchPartReference partRef) {
		LOGGER.finer("Tab closed : " + partRef.getPartProperty("session.id")); //$NON-NLS-1$ //$NON-NLS-2$
		SessionManager.getInstance().destroySession(partRef.getPartProperty("session.id")); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final void partDeactivated(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partOpened(IWorkbenchPartReference partRef) {	}

	/** {@inheritDoc} */
	public final void partBroughtToTop(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partHidden(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partInputChanged(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partVisible(IWorkbenchPartReference partRef) { }
}
