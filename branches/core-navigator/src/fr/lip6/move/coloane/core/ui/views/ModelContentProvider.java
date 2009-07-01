package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.InterfacesHandler;
import fr.lip6.move.coloane.core.ui.files.InterfacesHandler.NodeInterface;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.NodeLinksHandler;
import fr.lip6.move.coloane.core.ui.files.NodeLinksHandler.NodeLink;

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
	private final ImageDescriptor nodeLinkDescriptor = ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/node_link.png"); //$NON-NLS-1$
	private final ImageDescriptor errorOverlayDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.navigator.resources", "$nl$/icons/full/ovr16/error_co.gif"); //$NON-NLS-1$ //$NON-NLS-2$
	private final Image nodeLinkImage = nodeLinkDescriptor.createImage();
	private final ImageDescriptor nodeLinkErrorDescriptor = new DecorationOverlayIcon(nodeLinkImage, errorOverlayDescriptor, IDecoration.TOP_RIGHT);

	private StructuredViewer viewer;

	/**
	 * Constructor
	 * Add a listener on the workspace
	 */
	public ModelContentProvider() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile && ((IFile) parentElement).getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
			IFile file = (IFile) parentElement;
			List<Tree> children = new ArrayList<Tree>();

			// Construct public nodes tree
			InterfacesHandler interfaces = ModelLoader.loadFromXML(file, new InterfacesHandler(file));
			if (interfaces.getInterfaces().size() > 0) {
				Tree interfaceTree = new Tree(Messages.ModelContentProvider_0);
				interfaceTree.setIcon(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/interfaces.png")); //$NON-NLS-1$
				for (NodeInterface nodeInterface : interfaces.getInterfaces()) {
					Tree nodeInterfaceLeaf = new Tree(nodeInterface.getName(), nodeInterface);
					nodeInterfaceLeaf.setIcon(nodeInterface.getIcon());
					interfaceTree.addChild(nodeInterfaceLeaf);
				}
				children.add(interfaceTree);
			}

			// Construct links tree
			NodeLinksHandler nodeLinks = ModelLoader.loadFromXML(file, new NodeLinksHandler(file));
			if (nodeLinks.getNodeLinks().size() > 0) {
				Tree nodeLinkTree = new Tree(Messages.ModelContentProvider_1);
				nodeLinkTree.setIcon(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/node_links.png")); //$NON-NLS-1$
				for (NodeLink nl : nodeLinks.getNodeLinks()) {
					String targetModel = nl.getPath().replaceAll("/.*/", ""); //$NON-NLS-1$ //$NON-NLS-2$
					IResource targetResource = file.getParent().findMember(targetModel);

					boolean linkIsValid = false;
					String targetInterface = "@" + nl.getTargetId(); //$NON-NLS-1$
					// Test if file pointed by the link exist
					if (targetResource != null && targetResource instanceof IFile) {
						IFile targetFile = (IFile) targetResource;

						// Look for the interface
						InterfacesHandler interfacesOfLink = ModelLoader.loadFromXML(targetFile, new InterfacesHandler(file));
						for (NodeInterface nodeInterface : interfacesOfLink.getInterfaces()) {
							if (nodeInterface.getId() == nl.getTargetId()) {
								linkIsValid = true;
								targetInterface = nodeInterface.getName();
								break;
							}
						}
					}

					Tree nodeLinkLeaf = new Tree(nl.getSourceName() + " > " + targetModel + "/" + targetInterface, nl); //$NON-NLS-1$ //$NON-NLS-2$
					if (linkIsValid) {
						nodeLinkLeaf.setIcon(nodeLinkDescriptor);
					} else {
						nodeLinkLeaf.setIcon(nodeLinkErrorDescriptor);
					}
					nodeLinkTree.addChild(nodeLinkLeaf);
				}
				children.add(nodeLinkTree);
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
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		nodeLinkImage.dispose();
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
						if (viewer != null && !viewer.getControl().isDisposed()) {
							viewer.refresh(file);
						}
						return Status.OK_STATUS;
					}
				} .schedule();
			}
			return false;

		default:
			return true;
		}
	}

}
