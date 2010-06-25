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
