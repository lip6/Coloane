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
 * Display for models additional informations : <ol>
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
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile && ((IFile) parentElement).getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
			IFile file = (IFile) parentElement;
			if (!file.exists()) {
				return null;
			}
			List<Tree> children = new ArrayList<Tree>();

			// Construct public nodes tree
			InterfacesHandler interfaces = ModelLoader.loadFromXML(file, new InterfacesHandler(file));
			if (interfaces != null && interfaces.getInterfaces().size() > 0) {
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
			if (nodeLinks != null && nodeLinks.getNodeLinks().size() > 0) {
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
						if (interfacesOfLink != null) {
							for (NodeInterface nodeInterface : interfacesOfLink.getInterfaces()) {
								if (nodeInterface.getId() == nl.getTargetId()) {
									linkIsValid = true;
									targetInterface = nodeInterface.getName();
									break;
								}
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
//		} else if (parentElement instanceof IContainer) {
//			IContainer container = (IContainer) parentElement;
//			try {
//				return filterResources(container.members(), ".*/\\..*"); //$NON-NLS-1$
//			} catch (CoreException e) {
//				return null;
//			}
		}
		return null;
	}

	/**
	 * @param resources resource to filter
	 * @param regex regex @see {@link Pattern}
	 * @return resources without the resources matching the regex
	 */
//	private static IResource[] filterResources(IResource[] resources, String regex) {
//		List<IResource> filteredList = new ArrayList<IResource>();
//		for (IResource resource : resources) {
//			if (!resource.getFullPath().toString().matches(regex)) {
//				filteredList.add(resource);
//			}
//		}
//		return filteredList.toArray(new IResource[0]);
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasChildren(Object element) {
		Object[] childs = getChildren(element);
		return childs != null && childs.length > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		nodeLinkImage.dispose();
	}

	/** {@inheritDoc} */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (StructuredViewer) viewer;
	}

	/** {@inheritDoc} */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(this);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource source = delta.getResource();

		if (source.getType() == IResource.FILE) {
			final IFile file = (IFile) source;

			if ((delta.getFlags() & IResourceDelta.CONTENT) == IResourceDelta.CONTENT && file.getFileExtension().equals(Coloane.getParam("MODEL_EXTENSION"))) { //$NON-NLS-1$
				new UIJob("Update Models navigator") {  //$NON-NLS-1$
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						if (viewer != null && !viewer.getControl().isDisposed()) {
							viewer.refresh(file);
						}
						return Status.OK_STATUS;
					}
				} .schedule();
				return false;
			}
		}
		return true;
	}

}
