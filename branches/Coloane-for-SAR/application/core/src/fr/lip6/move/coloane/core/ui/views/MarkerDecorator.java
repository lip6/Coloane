package fr.lip6.move.coloane.core.ui.views;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author Clément Démoulins
 *
 */
public class MarkerDecorator implements ILabelDecorator {

	private static final ImageDescriptor WARNING_IMG = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/ovr16/warning_ovr.gif"); //$NON-NLS-1$ //$NON-NLS-2$
	private static final ImageDescriptor ERROR_IMG = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/ovr16/error_ovr.gif"); //$NON-NLS-1$ //$NON-NLS-2$
	private LocalResourceManager localResourceManager;

	/**
	 * Initialize the resource manager
	 */
	public MarkerDecorator() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources());
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		localResourceManager.dispose();
	}

	/** {@inheritDoc} */
	@Override
	public final Image decorateImage(Image image, Object element) {
		if (element instanceof IResource && ((IResource) element).isAccessible()) {
			IResource resource = (IResource) element;
			if (resource.isAccessible()) {
				try {
					ImageDescriptor decoratedImageDescriptor = null;
					for (IMarker marker : resource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO)) {
						Integer severity = (Integer) marker.getAttribute(IMarker.SEVERITY);
						if (severity != null) {
							if (severity.intValue() == IMarker.SEVERITY_ERROR) {
								decoratedImageDescriptor = new DecorationOverlayIcon(image, ERROR_IMG, IDecoration.BOTTOM_LEFT);
								break;
							} else if (severity.intValue() == IMarker.SEVERITY_WARNING) {
								decoratedImageDescriptor = new DecorationOverlayIcon(image, WARNING_IMG, IDecoration.BOTTOM_LEFT);
							}
						}
					}
					if (decoratedImageDescriptor != null) {
						return localResourceManager.createImage(decoratedImageDescriptor);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final String decorateText(String text, Object element) {
		return null;
	}
	
	/** {@inheritDoc} */
	@Override
	public final boolean isLabelProperty(Object element, String property) {
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public void addListener(ILabelProviderListener listener) {
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

}
