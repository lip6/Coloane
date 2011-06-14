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
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

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

	/**
	 * Constructor
	 * @param name Job name
	 * @param worker import extension that does the job
	 * @param formalism The formalism use by the new model
	 * @param path the source path
	 * @param newFile The file that will handle the new model data
	 */
	public ImportJob(String name, IImportFrom worker, IFormalism formalism, String path, IFile newFile) {
		super(name);
		this.worker = worker;
		this.formalism = formalism;
		this.path = path;
		this.newFile = newFile;
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus run(IProgressMonitor monitor) {
		try {
			int totalWork = new Long(new File(path).length()).intValue();
			monitor.beginTask("Import " + path, totalWork + 100); //$NON-NLS-1$

			monitor.subTask("Create graph"); //$NON-NLS-1$
			IGraph model = worker.importFrom(path, formalism, monitor);

			// Translate model to XML
			monitor.subTask("Convert to XML"); //$NON-NLS-1$
			String xmlString = ModelWriter.translateToXML(model);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$

			// Write result into a file
			newFile.setContents(inputS, true, false, null);
			monitor.done();
		} catch (CoreException e) {
			LOGGER.warning("Fail during the file creation"); //$NON-NLS-1$
			return e.getStatus();
		} catch (ExtensionException e) {
			LOGGER.warning("Fail during the import process: " + e.getMessage()); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "coloane", "Fail during the import process: " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (UnsupportedEncodingException e) {
			LOGGER.warning("Fail during the XML writing (invalid charset)"); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "coloane", "Fail during the XML writing (invalid charset)", e); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return Status.OK_STATUS;
	}

}
