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
package fr.lip6.move.coloane.extensions.exporttogml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The main class for the export
 * 
 * @author Maximilien Colange
 */
public class ExportToGML implements IExportTo {
	
	/**
	 * Initialize the formalismMap
	 */
	public ExportToGML() {
	}

	/**
	 * Performs the export
	 * 
	 * @param graph the graph to be exported
	 * @param filePath the export file name
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ExtensionException {
		try {
			IGMLExport exporter = createExporterInstance(graph.getFormalism().getName());
			exporter.export(graph, new FileWriter(filePath), monitor);
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}
	
	/**
	 * Returns an instance of the appropriate implementation of IGMLExport
	 * 
	 * @param formalism the Coloane formalism of the model to be exported
	 * @return an appropriate exporter for the formalism
	 */
	private static IGMLExport createExporterInstance(String formalism) {
		/// TODO
		IGMLExport result = null;
		if (true) {
			try {
				result =  new SNBExport(formalism);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
