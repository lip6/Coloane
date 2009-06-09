package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.main.Coloane;
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
 * TODO: A documenter
 * @author jbvoron
 *
 */
public final class LabelProvider implements ILabelProvider, IDescriptionProvider {
	private final Image errorImage = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/obj16/error_tsk.gif").createImage(); //$NON-NLS-1$ //$NON-NLS-2$
	private final Map<Object, Image> images = new HashMap<Object, Image>();

	/**
	 * {@inheritDoc}
	 */
	public Image getImage(Object element) {
		Image image = images.get(element);
		if (image == null && element instanceof IFile) {
			IFile f = (IFile) element;
			if ("model".equals(f.getFileExtension())) { //$NON-NLS-1$
				IFormalism formalism = ModelLoader.loadFormalismFromXml(f);
				if (formalism != null) {
					image = ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage();
					images.put(element, image);
					return image;
				} else {
					return errorImage;
				}
			}
		}
		return image;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getText(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		errorImage.dispose();
		for (Image img : images.values()) {
			img.dispose();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(ILabelProviderListener listener) {
	}

	/** {@inheritDoc} */
	public String getDescription(Object element) {
		if (element instanceof IFile) {
			IFile f = (IFile) element;
			if ("model".equals(f.getFileExtension())) { //$NON-NLS-1$
				IFormalism formalism = ModelLoader.loadFormalismFromXml(f);
				if (formalism != null) {
					return f.getFullPath() + "  —  " + formalism.getName(); //$NON-NLS-1$
				}
			}
		}
		return null;
	}
}
