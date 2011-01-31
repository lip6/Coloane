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
package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import fr.lip6.move.coloane.core.ui.files.ModelHandler;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.File;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Dedicated job for exporting models
 * 
 * @author Jean-Baptiste Voron
 */
public class ExportJob extends Job {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The export worker coming from the extension */
	private IExportTo worker;

	/** The target directory */
	private String targetDirectory;

	/** The extension for the new file */
	private String fileExtension;

	/** The model file */
	private IFile file;

	/**
	 * Constructor
	 * @param name Job name
	 * @param file File to export
	 * @param worker The class that is able to export the model
	 * @param targetDirectory The target directory
	 * @param fileExtension The file extension for the new file
	 */
	public ExportJob(String name, IFile file, IExportTo worker, String targetDirectory, String fileExtension) {
		super(name);
		if (worker == null) {
			throw new NullPointerException(Messages.ExportJob_0);
		}
		this.file = file;
		this.worker = worker;
		this.targetDirectory = targetDirectory;
		this.fileExtension = fileExtension;
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus run(IProgressMonitor monitor) {
		LOGGER.fine("File to export: " + file.getName() + " to" + targetDirectory); //$NON-NLS-1$ //$NON-NLS-2$

		IGraph model = ModelLoader.loadFromXML(file, new ModelHandler()).getGraph();

		// Strip the old extension and use the new one
		String newName = file.getName().substring(0, file.getName().lastIndexOf('.') + 1) + fileExtension;
		File outputFile = new File(targetDirectory + "/" + newName); //$NON-NLS-1$

		// If the target file already exists, use a version number
		for (int version = 1; outputFile.exists(); version++) {
			LOGGER.finer("File:" + outputFile + "already exists... Build a new one"); //$NON-NLS-1$ //$NON-NLS-2$
			newName = file.getName().substring(0, file.getName().lastIndexOf('.') + 1) + version + "." + fileExtension; //$NON-NLS-1$
			outputFile = new File(targetDirectory + "/" + newName); //$NON-NLS-1$
		}
		LOGGER.fine("Final name: " + newName); //$NON-NLS-1$

		try {
			LOGGER.finer("Exporting..."); //$NON-NLS-1$
			worker.export(model, targetDirectory + "/" + newName, monitor); //$NON-NLS-1$
		} catch (ExtensionException e) {
			return new Status(IStatus.ERROR, "coloane", "export " + file + " to " + targetDirectory + "/" + newName + "failed", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		LOGGER.finer("Done..."); //$NON-NLS-1$
		return Status.OK_STATUS;
	}
}
