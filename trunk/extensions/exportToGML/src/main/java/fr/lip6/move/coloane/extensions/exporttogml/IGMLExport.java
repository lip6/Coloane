package fr.lip6.move.coloane.extensions.exporttogml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.Writer;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Interface that the different exports to GML for the different Coloane formalism must match.
 * 
 * @author Maximilien Colange
 */
public interface IGMLExport {
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
