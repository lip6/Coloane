/**
 * Copyright (c) 2006-2012 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
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
package fr.lip6.move.coloane.core.ui.figures;

/**
 * A composite node can intercept double-clicks to potentially open another editor window.
 * @author Yann TM
 *
 */
public interface ICompositeNodeFigure extends INodeFigure {
	
	/** Handle action on double-click. */
	void handleDoubleClick();
}
