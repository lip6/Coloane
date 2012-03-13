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
import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The main class for the export
 * 
 * @author Maximilien Colange
 */
public class ExportToGML implements IExportTo {
	
	/**
	 * The constructor
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
			Writer writer = new FileWriter(filePath);
			export(graph, writer, monitor);
			writer.close();
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}
	
	/**
	 * Performs the export
	 * 
	 * @param graph the graph to be exported
	 * @param writer the writer to export to
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	public final void export(IGraph graph, Writer writer, IProgressMonitor monitor) throws ExtensionException {
		Exporter exporter = createExporterInstance(graph.getFormalism().getId());
		exporter.getInstance().export(graph, writer, exporter.getFormalism(), monitor);
	}
	
	/**
	 * Returns an instance of the appropriate implementation of IGMLExport
	 * 
	 * @param formalism the Coloane formalism of the model to be exported
	 * @return an appropriate exporter for the formalism
	 * @throws ExtensionException if no appropiate exporter can be found
	 */
	private static Exporter createExporterInstance(String formalism) throws ExtensionException {
		Exporter result = Activator.getDefault().getMap().get(formalism);
		if (result == null) {
			throw new ExtensionException("No appropriate exporter has been found for " + formalism);
		}
		return result;
	}
}
