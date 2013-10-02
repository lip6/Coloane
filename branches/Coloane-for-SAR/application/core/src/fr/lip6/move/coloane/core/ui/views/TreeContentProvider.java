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
package fr.lip6.move.coloane.core.ui.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for {@link Tree}
 *
 * @author Clément Démoulins
 */
public class TreeContentProvider implements ITreeContentProvider {

	/** {@inheritDoc} */
	@Override
	public final Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Tree) {
			return ((Tree) parentElement).getChildren().toArray();
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final Object getParent(Object element) {
		if (element instanceof Tree) {
			return ((Tree) element).getParent();
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean hasChildren(Object element) {
		Object[] childs = getChildren(element);
		return childs != null && childs.length > 0;
	}

	/** {@inheritDoc} */
	@Override
	public final Object[] getElements(Object inputElement) {
		if (inputElement instanceof Tree) {
			return new Object[] {((Tree) inputElement).getElement()};
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() { }

	/** {@inheritDoc} */
	@Override
	public final void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

}
