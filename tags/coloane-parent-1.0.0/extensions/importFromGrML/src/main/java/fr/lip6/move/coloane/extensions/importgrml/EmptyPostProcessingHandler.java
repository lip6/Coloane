package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Default post-processing handler that does nothing.
 */
public final class EmptyPostProcessingHandler implements PostProcessingHandler {

	@Override
	public void execute(IGraph model) throws ExtensionException {
	}

}
