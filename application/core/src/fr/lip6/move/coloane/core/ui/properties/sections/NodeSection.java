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

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Section that displays node attributes
 */
public class NodeSection extends AbstractElementSection<INode> {

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			INode element = getElements().get(0);
			refreshControls(
					element.getNodeFormalism().getName(),
					element.getNodeFormalism().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
