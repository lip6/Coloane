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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for {@link Tree} objects.
 *
 * @author Clément Démoulins
 */
public final class TreeLabelProvider implements ILabelProvider {

	private final List<Image> images = new ArrayList<Image>();

	/** {@inheritDoc} */
	public Image getImage(Object element) {
		if (element instanceof Tree) {
			ImageDescriptor descriptor = ((Tree) element).getIcon();
			if (descriptor != null) {
				Image icon = descriptor.createImage();
				images.add(icon);
				return icon;
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	public String getText(Object element) {
		if (element instanceof Tree) {
			return ((Tree) element).getName();
		}
		return null;
	}

	/** {@inheritDoc} */
	public void addListener(ILabelProviderListener listener) { }

	/** {@inheritDoc} */
	public void dispose() {
		for (Image img : images) {
			img.dispose();
		}
	}

	/** {@inheritDoc} */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/** {@inheritDoc} */
	public void removeListener(ILabelProviderListener listener) { }

}
