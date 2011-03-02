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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.ui.forms;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fr.lip6.move.coloane.projects.its.TypeDeclaration;

/**
 * A utility class that opens the editor.
 * @author Yann
 *
 */
public abstract class OpenEditorAction {
	
	/**
	 * open or focus the editor on the file proposed
	 * @param td current slected type
	 */
	public static void openEditor(TypeDeclaration td) {
		if (td != null) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				if (td != null) {
					IDE.openEditor(page, td.getTypeFile());
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
}
