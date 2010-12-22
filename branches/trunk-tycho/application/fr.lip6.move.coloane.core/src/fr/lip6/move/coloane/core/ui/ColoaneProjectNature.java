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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

/**
 * Modeling Project Type
 * 
 * @author Jean-Baptiste Voron
 */
public final class ColoaneProjectNature implements IProjectNature {

	/** {@inheritDoc} */
	public void configure() throws CoreException {
		// TODO Auto-generated method stub
	}

	/** {@inheritDoc} */
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub
	}

	/** {@inheritDoc} */
	public IProject getProject() {
		return null;
	}

	/** {@inheritDoc} */
	public void setProject(IProject project) {
		// TODO Auto-generated method stub
	}
}
