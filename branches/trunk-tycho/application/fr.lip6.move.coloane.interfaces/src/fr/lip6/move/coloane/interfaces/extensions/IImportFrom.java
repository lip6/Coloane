package fr.lip6.move.coloane.interfaces.extensions;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Import extensions must implement this interface.<br>
 * In fact, they must provide a way to import data from a file and to transform it into a {@link IGraph}.
 */
public interface IImportFrom {

	/**
	 * Transform a file into a {@link IGraph}
	 * @param filePath Path of the file to import
	 * @param formalism The formalism that will be used to build the model
	 * @param monitor Progress monitor
	 * @return an {@link IGraph} using an appropriate formalism
	 * @throws ExtensionException If something went wring during the import process
	 */
	IGraph importFrom(String filePath, IFormalism formalism, IProgressMonitor monitor) throws ExtensionException;
}
