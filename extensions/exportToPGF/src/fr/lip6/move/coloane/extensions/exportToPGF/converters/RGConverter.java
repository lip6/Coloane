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
package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.extensions.exportToPGF.Exporter;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Converter for PT-Net formalism.
 */
public final class RGConverter implements Converter {

	/**
	 * Convert a PT-Net attribute to a textual representation.
	 * Only replace '\n' characters by '\\' as required in (La)Tex.
	 * @param attribute The attribute to convert.
	 * @return Its textual representation.
	 */
	public String convert(IAttribute attribute) {
		String value = attribute.getValue();
		// Replace new lines with LaTeX \\ only un multiline attributes:
		// FIXME : i'm not sure about this, i need a method to test if the attribute is multiline even for computed attributes.
		if (attribute.getAttributeFormalism() instanceof IAttributeFormalism
				&& ((IAttributeFormalism) attribute.getAttributeFormalism()).isMultiLine()) {
			// 8 '\' because StringTemplate or something else interprets them!
			value = value.replaceAll("\n", " \\\\\\\\ \n");
		}
		return value;
	}

	/**
	 * @param query The query.
	 * @param node The node
	 */
	public void setGraphicalDescription(StringTemplate query, INode node) {
		if (node.getNodeFormalism().getName().equals("state")) {
			query.setAttribute("stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		} else if (node.getNodeFormalism().getName().equals("initial_state")) {
			query.setAttribute("initial_stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		} else if (node.getNodeFormalism().getName().equals("terminal_state")) {
			query.setAttribute("terminal_stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		}
	}


	/** {@inheritDoc} */
	public String getName(IAttribute attribute) {
		if (attribute.getName().equals("author(s)")) {
			return "authors";
		}
		return attribute.getName();
	}
}
