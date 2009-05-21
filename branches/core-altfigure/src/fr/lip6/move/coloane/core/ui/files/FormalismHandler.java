package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler qui permet de parser un modèle pour juste récupérer le formalisme.
 *
 * TODO : trouver un moyen de ne pas lire completement le fichier sachant que
 * le formalisme se trouve au début du fichier.
 */
public class FormalismHandler extends DefaultHandler {
	private IFormalism formalism;

	/**
	 * @return le formalisme
	 */
	public final IFormalism getFormalism() {
		return formalism;
	}

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if ("model".equals(name)) { //$NON-NLS-1$
			// Récupération du nom du formalisme
			String formalismName = attributes.getValue("formalism"); //$NON-NLS-1$
			formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		}
	}

}
