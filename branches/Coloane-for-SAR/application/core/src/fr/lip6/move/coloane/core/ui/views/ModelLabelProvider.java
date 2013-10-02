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
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.IDescriptionProvider;

/**
 * Provide formalism icons for models and give a short description.
 * This label provider is use by the models navigator.
 *
 * @author Clément Démoulins
 */
public final class ModelLabelProvider extends LabelProvider implements ILabelProvider, IDescriptionProvider {
	private final LocalResourceManager localResourceManager;
	private final Image unknown;

	/**
	 * Initialize some default images and decorations
	 */
	public ModelLabelProvider() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources());
		unknown = localResourceManager.createImage(ImageDescriptor.createFromFile(Coloane.class, "/resources/formalisms/unknown.png")); //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof IFile) {
			IFile f = (IFile) element;
			if (f.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				IFormalism formalism = ModelLoader.loadFormalismFromXML(f);
				if (formalism != null) {
					Image image;
					String imagePath = formalism.getImageName();

					if ("/null".equals(imagePath)) { //$NON-NLS-1$
						image = unknown;
					} else {
						image = localResourceManager.createImage(ImageDescriptor.createFromFile(Coloane.class, imagePath));
					}
					Image decoratedImage = PlatformUI.getWorkbench().getDecoratorManager().decorateImage(image, element);
					if (decoratedImage != null) {
						return decoratedImage;
					}
					return image;
				}
			}
		}
		return super.getImage(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		super.dispose();
		localResourceManager.dispose();
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription(Object element) {
		if (element instanceof IFile) {
			IFile f = (IFile) element;
			if (f.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				IFormalism formalism = ModelLoader.loadFormalismFromXML(f);
				if (formalism != null) {
					return f.getFullPath() + "  —  " + formalism.getName(); //$NON-NLS-1$
				}
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String getText(Object element) {
		if (element instanceof IResource) {
			IResource resource = (IResource) element;
			return resource.getName();
		}
		return super.getText(element);
	}
}
