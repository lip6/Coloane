package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler.NodeLink;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler.PublicNode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;

/**
 * Provider for model file of the models navigator.
 * Display for models additionnals informations : <ol>
 * <li>public nodes
 * <li>links to another models
 * </ol>
 * 
 * @author Clément Démoulins
 */
public final class ModelContentProvider implements ITreeContentProvider, IResourceChangeListener, IResourceDeltaVisitor {
	private final ImageDescriptor NODE_LINK_DESCRIPTOR = ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/node_link.png"); //$NON-NLS-1$
	private final ImageDescriptor ERROR_OVELAY_DESCRIPTOR = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.navigator.resources", "$nl$/icons/full/ovr16/error_co.gif"); //$NON-NLS-1$ //$NON-NLS-2$
	private final Image NODE_LINK_IMAGE = NODE_LINK_DESCRIPTOR.createImage();
	private final ImageDescriptor NODE_LINK_ERROR_DESCRIPTOR = new DecorationOverlayIcon(NODE_LINK_IMAGE, ERROR_OVELAY_DESCRIPTOR, IDecoration.TOP_RIGHT);
	private StructuredViewer viewer;

	public ModelContentProvider() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile && ((IFile) parentElement).getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
			IFile file = (IFile) parentElement;
			List<Tree<String>> children = new ArrayList<Tree<String>>();

			// Construct public nodes tree
			PublicNodeHandler pnh = ModelLoader.loadFromXML(file, new PublicNodeHandler(file));
			if (pnh.getPublicNodes().size() > 0) {
				Tree<String> publicNodes = new Tree<String>(Messages.ModelContentProvider_0);
				publicNodes.setIcon(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/public_nodes.png")); //$NON-NLS-1$
				for (PublicNode pn : pnh.getPublicNodes()) {
					Tree<String> pnLeaf = new Tree<String>(pn.getName());
					pnLeaf.setIcon(pn.getIcon());
					publicNodes.addChild(pnLeaf);
				}
				children.add(publicNodes);
			}

			// Construct links tree
			NodeLinkHandler nlh = ModelLoader.loadFromXML(file, new NodeLinkHandler());
			if (nlh.getNodeLinks().size() > 0) {
				Tree<String> links = new Tree<String>(Messages.ModelContentProvider_1);
				links.setIcon(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/node_links.png")); //$NON-NLS-1$
				for (NodeLink nl : nlh.getNodeLinks()) {
					String filenameLink = nl.getPath().replaceAll("/.*/", ""); //$NON-NLS-1$ //$NON-NLS-2$
					IResource resLink = file.getParent().findMember(filenameLink);

					// File pointed by the link exist
					boolean linkIsValid = false;
					String name = "@" + nl.getId(); //$NON-NLS-1$
					if (resLink != null && resLink instanceof IFile) {
						IFile fileLink = (IFile) resLink;
						PublicNodeHandler pnhLink = ModelLoader.loadFromXML(fileLink, new PublicNodeHandler(file));
						for (PublicNode pn : pnhLink.getPublicNodes()) {
							if (pn.getId() == nl.getId()) {
								linkIsValid = true;
								name = pn.getName();
								break;
							}
						}
					}

					Tree<String> nlLeaf = new Tree<String>(filenameLink + "/" + name); //$NON-NLS-1$
					if (linkIsValid) {
						nlLeaf.setIcon(NODE_LINK_DESCRIPTOR);
					} else {
						nlLeaf.setIcon(NODE_LINK_ERROR_DESCRIPTOR);
					}
					links.addChild(nlLeaf);
				}
				children.add(links);
			}

			return children.toArray();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object element) {
		Object[] childs = getChildren(element);
		return childs != null && childs.length > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object inputElement) {
		return null;
	}

	/** {@inheritDoc} */
	public void dispose() {
		NODE_LINK_IMAGE.dispose();
	}

	/** {@inheritDoc} */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (StructuredViewer) viewer;
	}

	/** {@inheritDoc} */
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(this);
		} catch (CoreException e) { 
			e.printStackTrace();
		} 
	}

	/** {@inheritDoc} */
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource source = delta.getResource();
		switch (source.getType()) {
		case IResource.FILE:
			final IFile file = (IFile) source;
			if (file.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				new UIJob("Update Models navigator") {  //$NON-NLS-1$
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						if (viewer != null && !viewer.getControl().isDisposed())
							viewer.refresh(file);
						return Status.OK_STATUS;						
					}
				}.schedule();
			}
			return false;
		}
		return true;
	}

}
