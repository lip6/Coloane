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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provide contents for the CheckBoxTreeViewer.<br>
 * Results are extracted from a IResultTree
 */
public class ResultContentProvider implements ITreeContentProvider {

	/** {@inheritDoc} */
	@Override
	public final Object[] getChildren(Object parentElement) {
		return ((IResultTree) parentElement).getChildren().toArray();
	}

	/** {@inheritDoc} */
	@Override
	public final Object getParent(Object element) {
		return ((IResultTree) element).getParent();
	}

	/** {@inheritDoc} */
	@Override
	public  final boolean hasChildren(Object element) {
		return ((IResultTree) element).getChildren().size() > 0;
	}

	/** {@inheritDoc} */
	@Override
	public final Object[] getElements(Object inputElement) {
		return ((IResultTree) inputElement).getElement().toArray();
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
	}

	/** {@inheritDoc} */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
