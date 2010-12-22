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

import fr.lip6.move.coloane.core.ui.actions.LocalAction;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * This class browses all tools contributions plugged to Coloane.
 * 
 * @author Jean-Baptiste Voron
 */
public class ToolsContribution extends CompoundContributionItem {
	
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String ICON = "icon"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String ACTION = "action"; //$NON-NLS-1$
	
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Extension Point ID */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.tools"; //$NON-NLS-1$
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isDynamic() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final IContributionItem[] getContributionItems() {
		List<IContributionItem> allContribs = new ArrayList<IContributionItem>();

		// Browse all local tools
		IConfigurationElement[] tools = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < tools.length; i++) {

			String name = tools[i].getAttribute(NAME);
			String description = tools[i].getAttribute(DESCRIPTION);
			String icon = "/" + tools[i].getAttribute(ICON); //$NON-NLS-1$

			// Create the associated action
			try {
				IService action = (IService) tools[i].createExecutableExtension(ACTION);
				IAction localAction = new LocalAction(name, description, ImageDescriptor.createFromFile(action.getClass(), icon), action);
				allContribs.add(new ActionContributionItem(localAction));
			} catch (CoreException e) {
				LOGGER.warning("Extension [" + name + "] was unable to be instanciated"); //$NON-NLS-1$ //$NON-NLS-2$
				IStatus warningStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Extension " + name + " was unable to be instanciated");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				StatusManager.getManager().handle(warningStatus);
			} catch (ClassCastException ce) {
				LOGGER.warning("Extension [" + name + "] was unable to be instanciated (invalid tool)"); //$NON-NLS-1$ //$NON-NLS-2$
				IStatus warningStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Extension " + name + " was unable to be instanciated");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				StatusManager.getManager().handle(warningStatus);
			}
		}

		// Thanks to strange getContributionsItems prototype, the array list must be translated into an array
		IContributionItem[] toReturn = new IContributionItem[allContribs.size()];
		for (int i = 0; i < allContribs.size(); i++) {
			toReturn[i] = allContribs.get(i);
		}

		if (toReturn.length == 0) {
			toReturn = new IContributionItem[1];
			IAction localAction = new LocalAction("No tool currently available", "", null, null);  //$NON-NLS-1$//$NON-NLS-2$
			localAction.setEnabled(false);
			toReturn[0] = new ActionContributionItem(localAction);
		}

		return toReturn;
	}

}
