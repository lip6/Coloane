package fr.lip6.move.coloane.core.ui.files;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

public class FileHandler extends DefaultHandler {
	private IModel model = new Model(new CamiTranslator());

	/**
	 * Lecture des balises ouvrantes du modele a la recherche de la balise <model>
	 */
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		// Recherche de la balise <model>
		if (baliseName.equals("model")) { //$NON-NLS-1$
			// Recuperation des positions
			this.model.setFormalism(attributes.getValue("formalism")); //$NON-NLS-1$
			Coloane.getLogger().fine("Formalisme du fichier en cours de lecture : "+this.model.getFormalism());
		}
	}
	

	/**
	 * Retourne le modele cree par le parcours du fichier xml
	 * @return Le squelette du modele generique
	 */
	public final IModel getModel() {
		return model;
	}
}
