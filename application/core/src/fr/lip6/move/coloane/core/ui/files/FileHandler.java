package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FileHandler extends DefaultHandler {
	private IModel model = new Model(new CamiTranslator());

	/**
	 * Lecture des balises ouvrantes du modele a la recherche de la balise <model>
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		// Recherche de la balise <model>
		if (baliseName.equals("model")) { //$NON-NLS-1$
			// Recuperation des positions
			this.model.setFormalism(attributes.getValue("formalism")); //$NON-NLS-1$
			Coloane.getLogger().fine("Formalisme du fichier en cours de lecture : " + this.model.getFormalism()); //$NON-NLS-1$
		}
	}


	/**
	 * Retourne le modele cree par le parcours du fichier xml
	 * @return Le squelette du modele generique
	 * @throws BuildException
	 */
	public final IModelImpl getModel() {
		return new ModelImplAdapter(model);
	}
}
