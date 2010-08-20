package fr.lip6.move.coloane.extensions.pnexamples;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.extensions.IExample;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * Create the PHILOSOPHER classical net from a resource file
 *
 * @author Jean-Baptiste Voron
 */
public class Philosophers implements IExample {

	/**
	 * Default constructor
	 */
	public Philosophers() { }

	/**
	 * {@inheritDoc}
	 */
	public final IGraph buildModel(IFormalism formalism) throws PluginException {
		URL xmlExample = Activator.class.getResource("/resources/Philo.model");
		IGraph model = null;
		try {
			model = ModelLoader.loadGraphFromXML(xmlExample.toURI());
		} catch (URISyntaxException e) {
			throw new PluginException(Activator.PLUGIN_ID, "Bad URI: " + e.getMessage());
		}
		return model;
	}
}
