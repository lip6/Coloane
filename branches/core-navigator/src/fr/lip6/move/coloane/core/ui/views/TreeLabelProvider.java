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
	@SuppressWarnings("unchecked")
	public Image getImage(Object element) {
		if (element instanceof Tree) {
			ImageDescriptor descriptor = ((Tree<Object>) element).getIcon();
			if (descriptor != null) {
				Image icon = descriptor.createImage();
				images.add(icon);
				return icon;
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public String getText(Object element) {
		if (element instanceof Tree) {
			return ((Tree<Object>) element).getElement().toString();
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
