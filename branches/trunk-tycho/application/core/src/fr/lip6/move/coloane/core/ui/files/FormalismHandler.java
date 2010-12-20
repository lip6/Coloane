package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Simple file handler that fetches the formalism from an XML file
 * 
 * @author Clément Démoulins
 */
public class FormalismHandler extends DefaultHandler {
	/** The formalism */
	private IFormalism formalism;

	/** @return the formalisme */
	public final IFormalism getFormalism() {
		return this.formalism;
	}

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if (IModelHandler.MODEL_MARKUP.equals(name)) {
			// Fetch the formalism value
			String formalismName = attributes.getValue(IModelHandler.MODEL_FORMALISM_MARKUP);
			try {
				formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
			} catch (IllegalArgumentException e) {
				throw new SAXException(e);
			}
		}
	}

}
