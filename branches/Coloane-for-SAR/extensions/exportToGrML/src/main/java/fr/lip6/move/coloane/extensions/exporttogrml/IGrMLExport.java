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
package fr.lip6.move.coloane.extensions.exporttogrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Interface that the different exports to GrML for the different Coloane formalism must match.
 * 
 * @author Maximilien Colange
 */
public interface IGrMLExport {
	/**
	 * The function that makes the export
	 * 
	 * @param graph the Coloane model to export
	 * @param writer the destination of the export
	 * @param formalismURL the URL of the target FML
	 * @param monitor to monitor the export
	 * @throws ExtensionException if the export fails
	 */
	void export(IGraph graph, Writer writer, String formalismURL, IProgressMonitor monitor) throws ExtensionException;
}
