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
package fr.lip6.move.coloane.api.alligator.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

/**
 * Create a wizard page to allow the user to choose the models to send to Alligator
 * 
 * @author Clément Démoulins
 */
public class ChooseModelsPage extends WizardExportResourcesPage implements IWizardPage {

	/**
	 * @param pageName Name of this wizard page used as a title
	 * @param selection initial selection
	 */
	protected ChooseModelsPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle(pageName);
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.dialogs.WizardDataTransferPage#createOptionsGroup(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createOptionsGroup(Composite parent) {
		// There's no options
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.dialogs.WizardDataTransferPage#validateSourceGroup()
	 */
	@Override
	protected final boolean validateSourceGroup() {
		return !getSelectedResources().isEmpty();
	}

	/** {@inheritDoc}
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public final void handleEvent(Event event) {
		System.err.println("handleEvent " + event);
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.dialogs.WizardExportResourcesPage#createDestinationGroup(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createDestinationGroup(Composite parent) {
		// There's no need for this
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.dialogs.WizardExportResourcesPage#getSelectedResources()
	 */
	@SuppressWarnings("unchecked")
	public final List<IResource> getSelectedResources() {
		List<IResource> selectedResources = super.getSelectedResources();
		return selectedResources;
	}

}
