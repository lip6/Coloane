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
package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.concurrent.CancellationException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.ide.IDE;

/**
 * Dedicated Job for importing models
 *
 * @author Jean-Baptiste Voron
 */
public class ImportJob extends Job {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The import worker */
	private IImportFrom worker;

	/** The formalism used by the new model */
	private IFormalism formalism;

	/** The source path */
	private String path;

	/** The new file (will be created to handle the new model) */
	private IFile newFile;
	
	private IWorkbench workbench;

	/**
	 * Constructor
	 * @param name Job name
	 * @param worker import extension that does the job
	 * @param workbench The workbench
	 * @param formalism The formalism use by the new model
	 * @param path the source path
	 * @param newFile The file that will handle the new model data
	 */
	public ImportJob(String name, IImportFrom worker, IWorkbench workbench, IFormalism formalism, String path, IFile newFile) {
		super(name);
		this.worker = worker;
		this.workbench = workbench;
		this.formalism = formalism;
		this.path = path;
		this.newFile = newFile;
		this.setUser(true);
		this.setPriority(Job.LONG);
		this.setUser(true);
		this.setRule(newFile);
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus run(IProgressMonitor monitor) {
		try {
			monitor.beginTask("Importing " + path, 100); //$NON-NLS-1$
			{
				// Create model:
				IProgressMonitor workerMonitor = new SubProgressMonitor(monitor, 97, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
				workerMonitor.setTaskName("Importing model"); //$NON-NLS-1$
				IGraph model = worker.importFrom(path, formalism, workerMonitor);
				// Translate model to XML:
				IProgressMonitor xmlMonitor = new SubProgressMonitor(monitor, 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
				xmlMonitor.beginTask("Writing XML file", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				Writer writer = new BufferedWriter(new FileWriter(newFile.getRawLocation().toFile()));
				ModelWriter.translateToXML(model, writer);
				writer.close();
				newFile.touch(null);
				xmlMonitor.done();
			} // Allow to clean memory
			// Open editor:
			if (!monitor.isCanceled()) {
				IProgressMonitor editorMonitor = new SubProgressMonitor(monitor, 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
				editorMonitor.beginTask("Opening editor", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						try {
							IDE.openEditor(page, newFile, true);
						} catch (CoreException ce) {
							LOGGER.warning(ce.getMessage());
						}
					}
				});
				editorMonitor.done();
			}
			newFile.touch(null);
			monitor.done();
		} catch (CancellationException e) {
			return new Status(IStatus.CANCEL, "coloane", "Import canceled."); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("Import failed: " + e.getMessage()); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "coloane", "Import failed."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return Status.OK_STATUS;
	}
	
	@Override
	protected final void canceling() {
		try {
			this.join();
			newFile.delete(true, null);
		} catch (CoreException e) {
			LOGGER.warning("Could not delete file: " + e.getMessage()); //$NON-NLS-1$
		} catch (InterruptedException e) {
		}
	}

}
