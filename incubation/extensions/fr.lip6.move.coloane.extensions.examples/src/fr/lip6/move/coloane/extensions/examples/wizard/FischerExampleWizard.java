/**
 * <copyright>
 *
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */

package fr.lip6.move.coloane.extensions.examples.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import fr.lip6.move.coloane.extensions.examples.plugin.ColoaneExamplesPlugin;


public class FischerExampleWizard
	extends AbstractExampleWizard {
	
	@Override
	protected Collection<ProjectDescriptor> getProjectDescriptors() {
		// We need the adapter example to be unzipped along with the
		// EMF library example model, edit and editor examples
		List<ProjectDescriptor> projects = new ArrayList<ProjectDescriptor>();
		projects.add(new ProjectDescriptor("fr.lip6.move.coloane.extensions.examples", "zips/fischer.zip", "fr.lip6.move.coloane.examples.tpn.fischer"));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		// can add more projects if so desired
		
		return projects;
	}
	
	@Override
	protected void log(Exception e) {
		if (e instanceof CoreException) {
			ColoaneExamplesPlugin.getDefault().getLog().log(((CoreException)e).getStatus());
		} else {
			ColoaneExamplesPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ColoaneExamplesPlugin.getDefault().getBundle().getSymbolicName(),IStatus.ERROR, e.getMessage(),e));
		}
	}
}
