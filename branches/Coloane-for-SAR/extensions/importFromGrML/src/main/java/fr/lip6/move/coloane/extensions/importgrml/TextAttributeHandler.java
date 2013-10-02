package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;

import java.util.logging.Logger;

import org.cosyverif.model.Attribute;

/**
 * Default attribute handler usable for text-only GrML attributes.
 * This handler copies the text without modification.
 */
public final class TextAttributeHandler implements AttributeHandler {

	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.importgrml"); //$NON-NLS-1$

	@Override
	public String importFrom(Attribute attribute) throws ExtensionException {
		for (Object content: attribute.getContent()) {
			LOGGER.fine("Content: '" + content.toString().trim() + "'.");
		}
		return attribute.getContent().get(0).toString().trim();
	}

}
