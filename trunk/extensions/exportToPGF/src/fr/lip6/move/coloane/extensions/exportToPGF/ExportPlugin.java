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
package fr.lip6.move.coloane.extensions.exportToPGF;

import fr.lip6.move.coloane.extensions.exportToPGF.converters.UnknownFormalismException;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Main class for the TikZ/PGF export plugin.
 */
public final class ExportPlugin implements IExportTo {

	/**
	 * @param graph The graph to export to TikZ.
	 * @param filePath The file where to put TikZ generated code.
	 * @param monitor A progress monitor.
	 * @throws ExtensionException An exception thrown when a problem is encountered.
	 */
	public void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ExtensionException {
		Logger logger = Logger.getLogger("fr.lip6.move.coloane.core");
		Exporter exporter = new Exporter(monitor);
		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to PGF", totalWork);
		try {
			logger.finer("Opening file " + filePath);
			BufferedWriter outputBuffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
			logger.finer("Writing to file");
			outputBuffer.write(exporter.export(graph).toString());
			outputBuffer.close();
		} catch (FileNotFoundException fe) {
			logger.warning("Invalid file name");
			throw new ExtensionException("Invalid filename !");
		} catch (IOException ioe) {
			logger.warning("Cannot write to file");
			throw new ExtensionException("Write error :" + ioe.getMessage());
		} catch (UnknownFormalismException e) {
			logger.warning("No converter for formalism " + graph.getFormalism().getId());
			throw new ExtensionException("Write error :" + e.getMessage());
		}
		monitor.done();
	}

}
