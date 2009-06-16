package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler.NodeLink;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler.PublicNode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provider for model file of the models navigator.
 * Display for models additionnals informations : <ol>
 * <li>public nodes
 * <li>links to another models
 * </ol>
 * 
 * @author Clément Démoulins
 */
public final class ModelContentProvider implements ITreeContentProvider {

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile && ((IFile) parentElement).getName().endsWith("model")) { //$NON-NLS-1$
			IFile file = (IFile) parentElement;
			List<Tree<String>> children = new ArrayList<Tree<String>>();

			// Construct public nodes tree
			PublicNodeHandler pnh = ModelLoader.loadFromXML(file, new PublicNodeHandler(file));
			if (pnh.getPublicNodes().size() > 0) {
				Tree<String> publicNodes = new Tree<String>(Messages.ModelContentProvider_0);
				for (PublicNode pn : pnh.getPublicNodes()) {
					publicNodes.addChild(new Tree<String>(pn.getName()));
				}
				children.add(publicNodes);
			}

			// Construct links tree
			NodeLinkHandler nlh = ModelLoader.loadFromXML(file, new NodeLinkHandler());
			if (nlh.getNodeLinks().size() > 0) {
				Tree<String> links = new Tree<String>(Messages.ModelContentProvider_1);
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
					links.addChild(new Tree<String>(filenameLink + "/" + name + " [" + linkIsValid + "]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	public void dispose() { }

	/** {@inheritDoc} */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

}
