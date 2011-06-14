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

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.SaveAsDialog;

/**
 * Deal with the backup of a model received from an external source (such as an API)
 *
 * @author Jean-Baptiste Voron
 */
public class SaveReceivedModel implements Runnable {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The new graph */
	private IGraph graph;

	/** The name of the new graph */
	private String newName;

	/** The workbench window we will work on */
	private IWorkbenchWindow window;

	/**
	 * Constructor
	 * @param graph The new graph to backup
	 * @param window The workbench window
	 */
	public SaveReceivedModel(IGraph graph, String newName, IWorkbenchWindow window) {
		this.graph = graph;
		this.newName = newName;
		this.window = window;
	}

	/**
	 * Display a dialog box to warn the user about the new model to backup.<br>
	 * Compute a new name for the file.<br>
	 * Fill the new file, and ask the workspace to update.
	 */
	public final void run() {
		// Build a dialog box
		SaveAsDialog dialog = new SaveAsDialog(this.window.getShell());

		// Compute a new name if no one has been provided by the API
		if ((this.newName == null) || ("".equals(this.newName))) { //$NON-NLS-1$
			this.newName = Coloane.getParam("API_FILENAME_BASE"); //$NON-NLS-1$
		}
		// Add the extension part to the name
		this.newName = this.newName + "." + Coloane.getParam("MODEL_EXTENSION"); //$NON-NLS-1$ //$NON-NLS-2$
		LOGGER.fine("New model " + this.newName); //$NON-NLS-1$

		dialog.setOriginalName(this.newName);
        dialog.open();

        IPath path = dialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				try {
					LOGGER.fine("Saving the incoming model"); //$NON-NLS-1$
					new ProgressMonitorDialog(window.getShell()).run(false,	false, new ModifyWorkspace(this.window, file, this.graph));
				} catch (InvocationTargetException e) {
					LOGGER.warning("Something went wrong while saving the new incoming file"); //$NON-NLS-1$
					e.printStackTrace();
				} catch (InterruptedException e) {
					LOGGER.warning("Something went wrong while saving the new incoming file"); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}
	}
}
