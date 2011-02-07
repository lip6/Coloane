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
package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * Wizard page for new project creation
 *
 * @see NewProjectWizard
 * @author Jean-Baptiste Voron
 */
public class ProjectCreationPage extends WizardNewProjectCreationPage {

	/**
	 * Constructor
	 * @param pageName The page name
	 * @param selection The current selection <i>(not used here)</i>
	 */
	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle(Messages.ProjectCreationPage_0);
		setDescription(Messages.ProjectCreationPage_1);
	}

	/**
	 * Can finish ?
	 * @return <code>true</code>
	 */
	public final boolean finish() {
		// Always return true.
		// Default values will be used if necessary
		return true;
	}
}
