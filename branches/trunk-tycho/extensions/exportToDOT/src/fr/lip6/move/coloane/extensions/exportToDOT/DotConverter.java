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
package fr.lip6.move.coloane.extensions.exportToDOT;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Convert Coloane model to DOT formatted file
 *
 * @author Jean-Baptiste Voron
 */
public final class DotConverter {

	/**
	 * Hidden constructor
	 */
	private DotConverter() { }

	/**
	 * Translate a model into a (long) string DOT formatted
	 * @param model The model to convert
	 * @param monitor The monitor to follow the operation progress
	 * @return The model converted into a DOT string
	 */
	public static String translateModel(IGraph model, IProgressMonitor monitor) {
		StringBuffer res = new StringBuffer();

		// Headers
		res.append("/*                                         */\n");
		res.append("/* This file has been generated by Coloane */\n");
		res.append("/* http://coloane.lip6.fr/                 */\n");
		res.append("/*                                         */\n");

		res.append("digraph G {\n");

		// Nodes
		res.append(translateNodes(model, monitor));

		// Arcs
		res.append(translateArcs(model, monitor));

		// Footer
		res.append("}");
		return res.toString();
	}

	/**
	 * Translate all model nodes
	 * @param model The model that contains nodes to translate
	 * @param monitor The monitor to follow the operation progress
	 * @return A string DOT formatted describing the model nodes
	 */
	private static String translateNodes(IGraph model, IProgressMonitor monitor) {
		StringBuffer nodes = new StringBuffer();

		// Browse all nodes
		monitor.subTask("Export nodes");
		for (INode node : model.getNodes()) {

			// Place
			if ("place".equals(node.getNodeFormalism().getName())) {
				nodes.append("\t " + node.getId() + " [label=\"\", shape=ellipse];\n");

			// Transition
			} else if ("transition".equals(node.getNodeFormalism().getName())) {
				nodes.append("\t " + node.getId() + " [label=\"\", shape=rectangle];\n");

			// Otherwise... nothing is done
			} else {
				continue;
			}

			// Attributes
			nodes.append(translateAttributes(node));
			monitor.worked(1);
		}
		return nodes.toString();
	}

	/**
	 * Translate all model arcs
	 * @param model The model that contains arcs to translate
	 * @param monitor The monitor to follow the operation progress
	 * @return A string DOT formatted describing the model arcs
	 */
	private static String translateArcs(IGraph model, IProgressMonitor monitor) {
		StringBuffer arcs = new StringBuffer();

		// Browse all arcs
		monitor.subTask("Export arcs");
		for (IArc arc : model.getArcs()) {
			// Simple arc
			if ("arc".equals(arc.getArcFormalism().getName())) {
				arcs.append("\t " + arc.getSource().getId() + " -> " + arc.getTarget().getId() + "");
				arcs.append(" [label=\"" + arc.getAttribute("valuation").getValue() + "\"];\n");

			// Otherwise
			} else {
				continue;
			}

			monitor.worked(1);
		}
		return arcs.toString();
	}

	/**
	 * Translate all attributes of an element
	 * @param element The element which attributes must be translated
	 * @return A string DOT formatted describing the element attributes
	 */
	private static String translateAttributes(IElement element) {
		StringBuffer attributes = new StringBuffer();
		attributes.append("\t\t /* Les attributs de " + element.getId() + "*/\n");
		for (IAttribute attribut : element.getAttributes()) {
			if (attribut.getValue().equals(attribut.getAttributeFormalism().getDefaultValue())) {
				continue;
			}
			attributes.append("\t\t \"" + element.getId() + "-" + attribut.getName() + "\"");
			attributes.append(" [label=\"" + attribut.getValue() + "\", shape=plaintext, fontcolor=blue];\n");
			attributes.append("\t\t \"" + element.getId() + "-" + attribut.getName() + "\" -> \"" + element.getId() + "\"");
			attributes.append(" [color=blue, style=dotted];\n");
		}
		return attributes.toString();
	}
}
