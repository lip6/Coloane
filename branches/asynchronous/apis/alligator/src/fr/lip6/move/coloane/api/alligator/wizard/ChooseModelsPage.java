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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * Create a wizard page to allow the user to choose the models to send to Alligator
 * 
 * @author Clément Démoulins
 */
public class ChooseModelsPage extends FilteredResourcesPage implements IWizardPage {

	/**
	 * @param pageName Name of this wizard page used as a title
	 * @param selection initial selection
	 */
	protected ChooseModelsPage(String pageName, IStructuredSelection selection) {
		super(pageName, new ModelFilter(), selection);
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.dialogs.WizardDataTransferPage#validateSourceGroup()
	 */
	@Override
	protected final boolean validate() {
		return !getSelectedResources().isEmpty();
	}

}
