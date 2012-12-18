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

import org.cosyverif.alligator.service.parameter.FileParameter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

/**
 * Create a wizard page to allow the user to choose the models to send to Alligator
 * 
 * @author Clément Démoulins
 */
public final class SelectFilePage extends SelectResourcePage {
	
	private final class ModelFilter implements IResourceFilter {

		@Override
		public boolean isFiltered(IResource resource) {
			return !(resource instanceof IFile);
		}

	}
	
	public SelectFilePage(FileParameter parameter) {
		super("SelectFile", "Select file for parameter '" + parameter.getName() +"'", parameter);
		this.filter = new ModelFilter();
	}

	
}
