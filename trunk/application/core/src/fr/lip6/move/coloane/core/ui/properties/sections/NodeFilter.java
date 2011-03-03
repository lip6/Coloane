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
package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.editpart.AttributeEditPart;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.jface.viewers.IFilter;

/**
 * A filter that selects which objects trigger the display of Node properties section.
 * @author Yann
 *
 */
public class NodeFilter implements IFilter {

	/**
	 * Selects NodeEditPart and AttributeEditPart whose parent is a Node.
	 * @param toTest the currently selected object in the WorkBench
	 * @return true if it corresponds to a node or a node attribute
	 */
	public boolean select(Object toTest) {
		if (toTest instanceof NodeEditPart) {			
			return true;
		} else if (toTest instanceof AttributeEditPart) {
			IAttribute att = (IAttribute) ((AttributeEditPart)toTest).getModel();
			if (att.getReference() instanceof INode) {
				return true;
			}
		}
		return false;
	}

}
