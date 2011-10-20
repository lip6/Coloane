package fr.lip6.move.coloane.extensions.exporttogml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.Writer;
import java.util.List;

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
	 * @param monitor to monitor the export
	 * @throws ExtensionException if the export fails
	 */
	void export(IGraph graph, Writer writer, IProgressMonitor monitor) throws ExtensionException;
	
	/**
	 * To get the Coloane formalisms handled by the export
	 * @return the list of formalisms handled by the export
	 */
	List<String> getFormalisms();
}
