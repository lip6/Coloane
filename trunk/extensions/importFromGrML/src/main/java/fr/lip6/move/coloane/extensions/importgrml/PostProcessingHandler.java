/**
 * 
 */
package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Interface for post-processing handlers.
 * 
 * A post-processing handler performs checks on the Coloane model after everything has been converted.
 */
public interface PostProcessingHandler {
	
	/**
	 *  Perform checks on the Coloane model after everything has been converted.
	 *  This method is even allowed to transform the model.
	 * @param model The Coloane model to check
	 * @throws ExtensionException if something goes wrong
	 */
	void execute(IGraph model) throws ExtensionException;

}
