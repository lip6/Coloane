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

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.FormalismHandler;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Provide formalism icons for models and give a short description.
 * This label provider is use by the models navigator.
 *
 * @author Clément Démoulins
 */
public final class ModelLabelProvider implements ILabelProvider, IDescriptionProvider {
	private final Image errorImage = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/obj16/error_tsk.gif").createImage(); //$NON-NLS-1$ //$NON-NLS-2$
	private final Map<Object, Image> images = new HashMap<Object, Image>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage(Object element) {
		Image image = images.get(element);
		if (image == null && element instanceof IFile) {
			IFile f = (IFile) element;
			if (f.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				FormalismHandler formalismHandler = ModelLoader.loadFromXML(f, new FormalismHandler());
				if (formalismHandler != null) {
					IFormalism formalism = formalismHandler.getFormalism();
					if (formalism != null) {
						image = ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage();
						images.put(element, image);
						return image;
					}
				}
				return errorImage;
			}
		}
		return image;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		errorImage.dispose();
		for (Image img : images.values()) {
			img.dispose();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription(Object element) {
		if (element instanceof IFile) {
			IFile f = (IFile) element;
			if (f.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				FormalismHandler formalismHandler = ModelLoader.loadFromXML(f, new FormalismHandler());
				if (formalismHandler != null) {
					IFormalism formalism = formalismHandler.getFormalism();
					if (formalism != null) {
						return f.getFullPath() + "  —  " + formalism.getName(); //$NON-NLS-1$
					}
				}
			}
		}
		return null;
	}
}
