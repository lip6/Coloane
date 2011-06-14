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
package fr.lip6.move.coloane.core.ui.panels;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Provide a label for each tree column
 */
public class ResultColumnLabelProvider extends ColumnLabelProvider {
	private final int column;

	/**
	 * @param column The interesting column number
	 */
	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}

	/** {@inheritDoc} */
	@Override
	public final String getText(Object element) {
		IResultTree node = (IResultTree) element;
		if (node.getElement().size() > column) {
			return (String) node.getElement().get(column);
		} else {
			return ""; //$NON-NLS-1$
		}
	}
}
