package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * TODO: A documenter
 * @author jbvoron
 *
 */
public final class LabelProvider implements ILabelProvider {

	/**
	 * {@inheritDoc}
	 */
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof IFile) {
			IFile f = (IFile) element;
			IExtensionPoint[] iep =  Platform.getExtensionRegistry().getExtensionPoints();
			System.err.println("$$$$ " + iep); //$NON-NLS-1$
			if ("model".equals(f.getFileExtension())) {
				IFormalism formalism = ModelLoader.loadFormalismFromXml(f);
				if (formalism != null) {
					return ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage();
				} else {
					return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/obj16/error_tsk.gif").createImage();
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
	}
}
