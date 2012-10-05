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

import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;

/**
 * Provide a label for each tree column
 */
public class ResultColumnLabelProvider extends OwnerDrawLabelProvider {
	private final int column;

	/**
	 * @param column The interesting column number
	 */
	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}

	/**
	 * @param element current element
	 * @return text into the element corresponding with the column set
	 */
	private String getText(Object element) {
		IResultTree node = (IResultTree) element;
		if (node.getElement().size() > column) {
			return (String) node.getElement().get(column);
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void measure(Event event, Object element) {
		String text = getText(element);
		Point size = event.gc.textExtent(text);
		event.width = size.x;
		event.height = Math.max(event.height, size.y);
	}

	/** {@inheritDoc} */
	@Override
	protected final void paint(Event event, Object element) {
		String text = getText(element);
		event.gc.drawText(text, event.x, event.y, true);
	}
}
