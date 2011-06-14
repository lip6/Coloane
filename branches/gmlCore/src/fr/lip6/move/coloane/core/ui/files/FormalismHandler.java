/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.model.factory.FormalismManager;
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
