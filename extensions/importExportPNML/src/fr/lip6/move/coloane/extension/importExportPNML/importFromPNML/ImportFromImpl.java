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
package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.pnml.framework.general.PnmlImport;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Import PNML files thanks to the PNML Framework
 *
 * @author Lom Messan Hillah
 */
public class ImportFromImpl implements IImportFrom {

	/**
	 * Constructeur
	 */
	public ImportFromImpl() { }

	/** {@inheritDoc} */
	public final IGraph importFrom(String filePath, IFormalism formalism,
			IProgressMonitor monitor) throws ExtensionException {
		IGraph graph = null;

		// Create the document workspace
		// Need to be clean before exiting
		try {
			ModelRepository.getInstance().createDocumentWorkspace(filePath);
		} catch (final InvalidIDException e) {
			throw new ExtensionException(e.getMessage());
		}

		PnmlImport pnmlImport = new PnmlImport();
		pnmlImport.setFallUse(true); // Fallback activated !
		HLAPIRootClass imported = null;

		try {
			imported = (HLAPIRootClass) pnmlImport.importFile(filePath);
			final Processor proc = MainProcessor.getProcessor(imported);
			graph = proc.process(imported, formalism);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ExtensionException(e.getMessage());

		// The workspace must be cleaned before exiting !
		} finally {
			try {
				ModelRepository.getInstance().destroyCurrentWorkspace();
			} catch (final VoidRepositoryException e) {
				e.printStackTrace();
			}
		}
		return graph;
	}
}
