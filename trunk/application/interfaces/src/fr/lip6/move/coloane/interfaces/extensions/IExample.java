package fr.lip6.move.coloane.interfaces.extensions;

import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Models constructors must implement this (simple) interface.
 * @see ExampleExtension
 */
public interface IExample {

	/**
	 * Build a Coloane model
	 * @return The resulting model
	 * @throws PluginException Something went wrong
	 */
	IGraph buildModel(IFormalism formalism) throws PluginException;
}
