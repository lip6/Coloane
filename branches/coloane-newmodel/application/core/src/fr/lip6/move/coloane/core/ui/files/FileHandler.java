package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.GraphModel;
import fr.lip6.move.coloane.core.ui.model.interfaces.IGraph;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FileHandler extends DefaultHandler {
	private IGraph graph;

	/**
	 * Lecture des balises ouvrantes du modele a la recherche de la balise <model>
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		// Recherche de la balise <model>
		if (baliseName.equals("model")) { //$NON-NLS-1$
			// Recuperation des positions
			graph = new GraphModel(attributes.getValue("formalism")); //$NON-NLS-1$
			Coloane.getLogger().fine("Formalisme du fichier en cours de lecture : " + graph.getFormalism().getName()); //$NON-NLS-1$
		}
	}


	/**
	 * Retourne le modele cree par le parcours du fichier xml
	 * @return Le squelette du modele generique
	 * @throws BuildException
	 */
	public final IGraph getGraph() {
		return graph;
	}
}
