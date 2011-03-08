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





public class FischerExampleWizard
	extends AbstractExampleWizard {
	
	
	@Override
	public String getProjectName() {
		return "fr.lip6.move.coloane.examples.tpn.fischer";
	}



	@Override
	public String getZipFile() {
		return "zips/fischer.zip";
	}
}
