package fr.lip6.move.coloane.extensions.pnexamples;

import java.net.URISyntaxException;
import java.net.URL;

import fr.lip6.move.coloane.core.extensions.IExample;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class Philosophers implements IExample {
	
	public Philosophers() { }

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExample#buildModel()
	 */
	public IGraph buildModel() {
		URL xmlExample = Activator.class.getResource("/resources/Philo.model");
		IGraph model = null;
		try {
			model = ModelLoader.loadFromXML(xmlExample.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}
